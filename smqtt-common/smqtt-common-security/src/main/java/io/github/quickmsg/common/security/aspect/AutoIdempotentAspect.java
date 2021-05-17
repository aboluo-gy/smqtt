package io.github.quickmsg.common.security.aspect;

import io.github.quickmsg.common.core.exception.BaseException;
import io.github.quickmsg.common.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author songjg
 */
@Component
@Slf4j
@Aspect
public class AutoIdempotentAspect {

    @Resource
    private TokenService tokenService;

    @Before("@annotation(com.smqtt.common.security.annotation.AutoIdempotent)")
    public boolean doBefore(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        try {
            //幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            return tokenService.checkToken(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("幂等性验证失败！！！请勿重复提交");
        }
    }
}
