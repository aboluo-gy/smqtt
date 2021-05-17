package io.github.quickmsg.system.api;

import com.smqtt.common.core.constant.ServiceNameConstants;
import com.smqtt.common.core.domain.R;
import io.github.quickmsg.system.api.domain.SysUser;
import io.github.quickmsg.system.api.factory.RemoteUserFallbackFactory;
import io.github.quickmsg.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @Author smqtt
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);

    /**
     * 通过appId查询用户信息
     *
     * @param appId 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/infoByAppId/{appId}")
    public R<SysUser> findByAppId(@PathVariable("appId") String appId);
}
