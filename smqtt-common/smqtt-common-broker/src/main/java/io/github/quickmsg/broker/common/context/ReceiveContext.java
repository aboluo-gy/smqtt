package io.github.quickmsg.broker.common.context;


import io.github.quickmsg.broker.common.channel.ChannelRegistry;
import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.cluster.ClusterRegistry;
import io.github.quickmsg.broker.common.config.Configuration;
import io.github.quickmsg.broker.common.message.MessageRegistry;
import io.github.quickmsg.broker.common.protocol.ProtocolAdaptor;
import io.github.quickmsg.broker.common.topic.TopicRegistry;
import io.netty.handler.codec.mqtt.MqttMessage;

import java.util.function.BiConsumer;

/**
 * @Author luxurong
 */

public interface ReceiveContext<T extends Configuration> extends BiConsumer<MqttChannel, MqttMessage> {


    /**
     * topic注册中心
     *
     * @return TopicRegistry
     */
    TopicRegistry getTopicRegistry();

    /**
     * channel管理中心
     *
     * @return ChannelRegistry
     */
    ChannelRegistry getChannelRegistry();


    /**
     * 协议转换器
     *
     * @return ProtocolAdaptor
     */
    ProtocolAdaptor getProtocolAdaptor();


    /**
     * 持久化消息处理
     *
     * @return ProtocolAdaptor
     */
    MessageRegistry getMessageRegistry();


    /**
     * 集群注册器
     *
     * @return ProtocolAdaptor
     */
    ClusterRegistry getClusterRegistry();


    /**
     * 获取配置文件
     *
     * @return T
     */
    T getConfiguration();


}
