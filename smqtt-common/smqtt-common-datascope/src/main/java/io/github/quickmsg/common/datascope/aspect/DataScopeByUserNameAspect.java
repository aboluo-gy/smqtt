package io.github.quickmsg.common.datascope.aspect;


import io.github.quickmsg.common.core.utils.StringUtils;
import io.github.quickmsg.common.core.web.domain.BaseEntity;
import io.github.quickmsg.common.datascope.annotation.DataScopeByUserName;
import io.github.quickmsg.common.security.service.TokenService;
import io.github.quickmsg.system.api.domain.SysRole;
import io.github.quickmsg.system.api.domain.SysUser;
import io.github.quickmsg.system.api.model.LoginUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据过滤处理
 *
 * @author iot
 */
@Aspect
@Component
public class DataScopeByUserNameAspect
{
    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Autowired
    private TokenService tokenService;


    // 配置织入点
    @Pointcut("@annotation(io.github.quickmsg.common.datascope.annotation.DataScopeByUserName)")
    public void DataScopeByUserNamePointCut()
    {
    }

    @Before("DataScopeByUserNamePointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint)
    {
        // 获得注解
        DataScopeByUserName controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null)
        {
            return;
        }
        // 获取当前的用户
        LoginUser loginUser = tokenService.getLoginUser();
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getSysUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin())
            {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.fieldName());
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user 用户
     * @param fieldName 字段名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String fieldName)
    {
        StringBuilder sqlString = new StringBuilder();

        for (SysRole role : user.getRoles())
        {
            //查询用户名

            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope))
            {
                sqlString = new StringBuilder();
                break;
            }
            else if (DATA_SCOPE_CUSTOM.equals(dataScope))
            {
                sqlString.append(StringUtils.format(
                        " OR {} IN ( SELECT u.user_name FROM sys_user u LEFT JOIN sys_dept d ON u.dept_id = d.dept_id LEFT JOIN sys_role_dept rd ON rd.dept_id = d.dept_id WHERE role_id = {} ) ", fieldName,
                        role.getRoleId()));
            }
            else if (DATA_SCOPE_DEPT.equals(dataScope))
            {
                sqlString.append(StringUtils.format(" OR {} in (SELECT u.user_name FROM sys_user u LEFT JOIN sys_dept d ON u.dept_id = d.dept_id where u.dept_id = {}) ", fieldName, user.getDeptId()));
            }
            else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope))
            {
                sqlString.append(StringUtils.format(
                        " OR {} in (SELECT u.user_name FROM sys_user u LEFT JOIN sys_dept d ON u.dept_id = d.dept_id WHERE u.dept_id = {} or find_in_set( {} , d.ancestors ))",
                        fieldName, user.getDeptId(), user.getDeptId()));
            }
            else if (DATA_SCOPE_SELF.equals(dataScope))
            {
                if (StringUtils.isNotBlank(fieldName))
                {
                    sqlString.append(StringUtils.format(" OR {} = '{}' ", fieldName, user.getUserName()));
                }
                else
                {
                    // 数据权限为仅本人且没有fieldName不查询任何数据
                    sqlString.append(" OR 1=0 ");
                }
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
            {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScopeByUserName getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(DataScopeByUserName.class);
        }
        return null;
    }
}
