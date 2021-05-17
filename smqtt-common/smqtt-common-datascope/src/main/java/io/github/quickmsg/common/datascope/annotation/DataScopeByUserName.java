package io.github.quickmsg.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * 根据username获取数据权限过滤注解
 *
 * @author iot
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScopeByUserName
{
    /**
     * 需要查询的字段名
     */
    public String fieldName() default "";

}
