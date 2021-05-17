package io.github.quickmsg.broker.common.auth;


import io.github.quickmsg.broker.common.spi.DynamicLoader;

/**
 * @Author luxurong
 */
public interface PasswordAuthentication {

    PasswordAuthentication INSTANCE = DynamicLoader.findFirst(PasswordAuthentication.class).orElse(null);


    /**
     * 认证接口
     *
     * @param userName        用户名称
     * @param passwordInBytes 密钥
     * @return 布尔
     */
    boolean auth(String userName, byte[] passwordInBytes);

}
