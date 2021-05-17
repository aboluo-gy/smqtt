package io.github.quickmsg.broker.common.core.protocol;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.context.ReceiveContext;
import io.github.quickmsg.broker.common.enums.ChannelStatus;
import io.github.quickmsg.broker.common.message.MessageRegistry;
import io.github.quickmsg.broker.common.message.MqttMessageBuilder;
import io.github.quickmsg.broker.common.protocol.Protocol;
import io.github.quickmsg.broker.common.topic.TopicRegistry;
import io.github.quickmsg.broker.common.utils.MessageUtils;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author luxurong
 */
@Slf4j
public class PublishProtocol implements Protocol<MqttPublishMessage> {

    private static List<MqttMessageType> MESSAGE_TYPE_LIST = new ArrayList<>();

    static {
        MESSAGE_TYPE_LIST.add(MqttMessageType.PUBLISH);
    }

    @Override
    public List<MqttMessageType> getMqttMessageTypes() {
        return MESSAGE_TYPE_LIST;
    }


    @Override
    public Mono<Void> parseProtocol(MqttPublishMessage message, MqttChannel mqttChannel, ContextView contextView) {
        try {
            ReceiveContext<?> receiveContext = contextView.get(ReceiveContext.class);
            TopicRegistry topicRegistry = receiveContext.getTopicRegistry();
            MqttPublishVariableHeader variableHeader = message.variableHeader();
            MessageRegistry messageRegistry = receiveContext.getMessageRegistry();
            Set<MqttChannel> mqttChannels = topicRegistry.getChannelListByTopic(variableHeader.topicName());
            // http mock
            if (mqttChannel.getIsMock()) {
                return send(mqttChannels, message, messageRegistry, filterRetainMessage(message, messageRegistry));
            }
            switch (message.fixedHeader().qosLevel()) {
                case AT_MOST_ONCE:
                    return send(mqttChannels, message, messageRegistry, filterRetainMessage(message, messageRegistry));
                case AT_LEAST_ONCE:
                    return send(mqttChannels, message, messageRegistry,
                            mqttChannel.write(MqttMessageBuilder.buildPublishAck(variableHeader.packetId()), false)
                                    .then(filterRetainMessage(message, messageRegistry)));
                case EXACTLY_ONCE:
                    if (!mqttChannel.existQos2Msg(variableHeader.packetId())) {
                        return mqttChannel
                                .cacheQos2Msg(variableHeader.packetId(), MessageUtils.wrapPublishMessage(message, 0))
                                .then(mqttChannel.write(MqttMessageBuilder.buildPublishRec(variableHeader.packetId()), true));
                    }
                default:
                    return Mono.empty();
            }
        } catch (Exception e) {
            log.error("error ", e);
        }
        return Mono.empty();
    }


    /**
     * 通用发送消息
     *
     * @param mqttChannels    topic 匹配channels
     * @param message         消息体
     * @param messageRegistry 消息中心
     * @param other           其他操作
     * @return Mono
     */
    private Mono<Void> send(Set<MqttChannel> mqttChannels, MqttPublishMessage message, MessageRegistry messageRegistry, Mono<Void> other) {
        return Mono.when(
                mqttChannels.stream()
                        .filter(channel -> filterOfflineSession(channel, messageRegistry, message))
                        .map(channel ->
                                channel.write(MessageUtils.wrapPublishMessage(message,
                                        channel.generateMessageId()),
                                        message.fixedHeader().qosLevel().value() > 0)
                        )
                        .collect(Collectors.toList())).then(other);

    }


    /**
     * 过滤离线会话消息
     *
     * @param mqttChannel     topic匹配的channel
     * @param messageRegistry 消息注册中心
     * @param mqttMessage     消息
     * @return boolean
     */
    private boolean filterOfflineSession(MqttChannel mqttChannel, MessageRegistry messageRegistry, MqttPublishMessage mqttMessage) {
        if (mqttChannel.getStatus() == ChannelStatus.ONLINE) {
            return true;
        } else {
            messageRegistry
                    .sendSessionMessages(mqttChannel.getClientIdentifier(), mqttMessage.duplicate());
            return false;
        }
    }


    /**
     * 过滤保留消息
     *
     * @param message         消息体
     * @param messageRegistry 消息中心
     * @return Mono
     */
    private Mono<Void> filterRetainMessage(MqttPublishMessage message, MessageRegistry messageRegistry) {
        return Mono.fromRunnable(() -> {
            if (message.fixedHeader().isRetain()) {
                messageRegistry.saveRetainMessage(message.variableHeader().topicName(), message.duplicate());
            }
        });
    }


}
