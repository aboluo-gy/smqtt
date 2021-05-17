package io.github.quickmsg.broker.common.core.protocol;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.context.ReceiveContext;
import io.github.quickmsg.broker.common.message.MqttMessageBuilder;
import io.github.quickmsg.broker.common.protocol.Protocol;
import io.github.quickmsg.broker.common.topic.TopicRegistry;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author luxurong
 */
public class UnSubscribeProtocol implements Protocol<MqttUnsubscribeMessage> {

    private static List<MqttMessageType> MESSAGE_TYPE_LIST = new ArrayList<>();

    static {
        MESSAGE_TYPE_LIST.add(MqttMessageType.UNSUBSCRIBE);
    }


    @Override
    public Mono<Void> parseProtocol(MqttUnsubscribeMessage message, MqttChannel mqttChannel, ContextView contextView) {
        return Mono.fromRunnable(() -> {
            ReceiveContext<?> receiveContext = contextView.get(ReceiveContext.class);
            TopicRegistry topicRegistry = receiveContext.getTopicRegistry();
            topicRegistry.clear(new HashSet<>(message.payload().topics()), mqttChannel);
        }).then(mqttChannel.write(MqttMessageBuilder.buildUnsubAck(message.variableHeader().messageId()), false));
    }

    @Override
    public List<MqttMessageType> getMqttMessageTypes() {
        return MESSAGE_TYPE_LIST;
    }


}
