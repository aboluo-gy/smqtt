package io.github.quickmsg.system.api.factory;

import com.smqtt.common.core.domain.R;
import feign.hystrix.FallbackFactory;
import io.github.quickmsg.system.api.RemoteUserService;
import io.github.quickmsg.system.api.domain.SysUser;
import io.github.quickmsg.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @Author smqtt
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<SysUser> findByAppId(String appId) {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }
        };
    }
}
