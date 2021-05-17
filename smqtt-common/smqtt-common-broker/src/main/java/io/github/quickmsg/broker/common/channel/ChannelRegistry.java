package io.github.quickmsg.broker.common.channel;

import io.github.quickmsg.broker.common.spi.DynamicLoader;

/**
 * @Author luxurong
 */
public interface ChannelRegistry {


    ChannelRegistry INSTANCE = DynamicLoader.findFirst(ChannelRegistry.class).orElse(null);


    /**
     * 关闭通道
     *
     * @param mqttChannel 通道关闭
     */
    void close(MqttChannel mqttChannel);

    /**
     * 注册通道
     *
     * @param clientIdentifier 客户端id
     * @param mqttChannel 通道关闭
     */
    void registry(String clientIdentifier, MqttChannel mqttChannel);

    /**
     * 判读通道是否存在
     *
     * @param clientIdentifier 客户端id
     * @return 布尔
     */
    boolean exists(String clientIdentifier);


    /**
     * 获取通道
     *
     * @param clientIdentifier 客户端id
     * @return MqttChannel
     */
    MqttChannel get(String clientIdentifier);


    /**
     * 获取通道计数
     *
     * @return 通道数
     */
    Integer counts();


}
