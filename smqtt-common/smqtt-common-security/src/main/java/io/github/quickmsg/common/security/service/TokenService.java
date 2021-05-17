package io.github.quickmsg.common.security.service;

import com.github.pagehelper.util.StringUtil;

import io.github.quickmsg.common.core.constant.CacheConstants;
import io.github.quickmsg.common.core.constant.Constants;
import io.github.quickmsg.common.core.constant.UserConstants;
import io.github.quickmsg.common.core.exception.BaseException;
import io.github.quickmsg.common.core.utils.IdUtils;
import io.github.quickmsg.common.core.utils.SecurityUtils;
import io.github.quickmsg.common.core.utils.ServletUtils;
import io.github.quickmsg.common.core.utils.StringUtils;
import io.github.quickmsg.common.core.utils.ip.IpUtils;
import io.github.quickmsg.common.redis.service.RedisService;
import io.github.quickmsg.system.api.domain.SysRole;
import io.github.quickmsg.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @Author smqtt
 */
@Component
public class TokenService
{
    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;

    private static final String IDEMPOTENT_TOKEN_PREFIX = "IDEMPOTENT:";

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginUser loginUser)
    {
        // 生成token
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        loginUser.setUserid(loginUser.getSysUser().getUserId());
        loginUser.setUsername(loginUser.getSysUser().getUserName());
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);

        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
        return map;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser()
    {
        return getLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            LoginUser user = redisService.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }


    /**
     * 创建token
     * @return
     * @Author: songjg
     * @datetime: 2021/3/16 11:43
     */
    public String createToken() {
        String random = UUID.randomUUID().toString().replace("-", "");
        StringBuilder token = new StringBuilder();
        try {
            token.append(IDEMPOTENT_TOKEN_PREFIX).append(random);
            //10分钟过期时间
            boolean setEx = redisService.setEx(token.toString(), token.toString(), 600000L);
            boolean notEmpty = StringUtil.isNotEmpty(token.toString());
            if (notEmpty && setEx) {
                return token.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证token
     * @param request
     * @return
     * @throws Exception
     * @Author: songjg
     * @datetime: 2021/3/16 11:43
     */
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Constants.IDEMPOTENT_TOKEN_NAME);
        boolean empty = StringUtil.isEmpty(token);
        if (empty) {
            token = request.getParameter(Constants.IDEMPOTENT_TOKEN_NAME);
            if (StringUtil.isEmpty(token)) {
                throw new BaseException("[request : token不存在！！！]");
            }
        }
        if (!redisService.exists(token)) {
            throw new BaseException("[redis : token不存在！！！]");
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new BaseException("[redis : 删除token失败！！！]");
        }
        return true;
    }

    /**
     * 验证是否有操作此数据的权限
     * @param userName  操作的数据所属账号
     * @return true=无权限，false=有权限
     */
    public boolean checkDataAuth(String userName) throws Exception {
        LoginUser loginUser = getLoginUser();
        for (SysRole role : loginUser.getSysUser().getRoles()) {
            if (role.isAdmin() || UserConstants.DATA_SCOPE_ALL.equals(role.getDataScope())) {
                return false;
            }
        }
        return !loginUser.getUserNames().contains(userName);
    }
}
