package io.github.quickmsg.gateway.service;



import io.github.quickmsg.common.core.exception.CaptchaException;
import io.github.quickmsg.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @Author smqtt
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCapcha(String key, String value) throws CaptchaException;
}
