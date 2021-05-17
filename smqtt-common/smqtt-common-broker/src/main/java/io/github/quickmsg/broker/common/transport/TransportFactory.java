package io.github.quickmsg.broker.common.transport;


import io.github.quickmsg.broker.common.config.Configuration;

/**
 * @Author luxurong
 */
public interface TransportFactory<C extends Configuration> {

    /**
     * 创建通道
     *
     * @param c 配置文件
     * @return Transport 通道类
     */
    Transport<C> createTransport(C c);


}
