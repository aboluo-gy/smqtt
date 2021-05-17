package io.github.quickmsg.broker.common.topic;



import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.message.SubscribeChannelContext;
import io.github.quickmsg.broker.common.spi.DynamicLoader;

import java.util.List;
import java.util.Set;

/**
 * @Author luxurong
 */
public interface TopicRegistry {


    TopicRegistry INSTANCE = DynamicLoader.findFirst(TopicRegistry.class).orElse(null);

    /**
     * 绑定主题跟channel关系
     *
     * @param topic       订阅主题
     * @param mqttChannel 通道信息
     */
    void registryTopicConnection(String topic, MqttChannel mqttChannel);


    /**
     * 清除订阅消息
     *
     * @param mqttChannel 通道信息
     */
    void clear(MqttChannel mqttChannel);


    /**
     * 清除订阅消息
     *
     * @param topics topics
     * @param mqttChannel 通道信息
     */
    void clear(Set<String> topics, MqttChannel mqttChannel);

    /**
     * 获取topic的channels
     *
     * @param topicName topic name
     * @return 通道
     */
    Set<MqttChannel> getChannelListByTopic(String topicName);


    /**
     * 绑定主题跟channel关系
     *
     * @param mqttTopicSubscriptions 通道信息/订阅主题
     */
    void registryTopicConnection(List<SubscribeChannelContext> mqttTopicSubscriptions);
}
