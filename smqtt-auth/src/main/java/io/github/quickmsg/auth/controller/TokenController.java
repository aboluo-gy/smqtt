package io.github.quickmsg.auth.controller;

import com.github.pagehelper.util.StringUtil;
import com.smqtt.common.core.domain.R;
import com.smqtt.common.core.utils.StringUtils;
import com.smqtt.common.core.web.domain.AjaxResult;
import com.smqtt.common.security.service.TokenService;
import com.smqtt.system.api.model.LoginUser;
import io.github.quickmsg.auth.form.LoginBody;
import io.github.quickmsg.auth.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token 控制
 *
 * @Author smqtt
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    /**
     * 创建token
     * @return
     * @Author: songjg
     * @datetime: 2021/3/15 18:07
     */
    @PostMapping("/token/create")
    public AjaxResult createToken(){
        String token = tokenService.createToken();
        if (StringUtil.isNotEmpty(token)) {
            return AjaxResult.success("生成token成功",token);
        }
        return AjaxResult.error("生成token失败！");
    }
}
