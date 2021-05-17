package io.github.quickmsg.common.core.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * ApplicationContext 上下文
 * 用次上下文获取bean操作，必须在启动类调用setApplicationContext进行赋值
 * @Author: songjg
 * @datetime: 2021/3/31 9:55
 */

public class SpringContextUtil implements DisposableBean {

    private static ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    /**
     *  ApplicationContext 上下文赋值
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {

        if (SpringContextUtil.applicationContext != null) {
            logger.info("SpringContextUtil中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextUtil.applicationContext);
        }

        // NOSONAR
        SpringContextUtil.applicationContext = applicationContext;
    }
    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static String getRootRealPath(){
        String rootRealPath ="";
        try {
            rootRealPath=getApplicationContext().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            logger.warn("获取系统根目录失败");
        }
        return rootRealPath;
    }

    public static String getResourceRootRealPath(){
        String rootRealPath ="";
        try {
            rootRealPath=new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            logger.warn("获取资源根目录失败");
        }
        return rootRealPath;
    }


    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextUtil中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()){
            logger.debug("清除SpringContextUtil中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextUtil.clearHolder();
    }
    /**
     * 获取类型为requiredType的Map
     *
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> getBeanMap(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 获取 目标对象
     *
     * @param proxy 代理对象
     * @return 目标对象
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            // 不是代理对象,直接返回
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else {
            // cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        AopProxy aopProxy = (AopProxy) field.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        field.setAccessible(true);
        Object dynamicAdvisedInterceptor = field.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextUtil.");
    }

}
