package io.github.quickmsg.broker.common.core.protocol;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.protocol.Protocol;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttUnsubAckMessage;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author luxurong
 */
public class UnSubscribeAckProtocol implements Protocol<MqttUnsubAckMessage> {

    private static List<MqttMessageType> MESSAGE_TYPE_LIST = new ArrayList<>();

    static {
        MESSAGE_TYPE_LIST.add(MqttMessageType.UNSUBACK);
    }

    @Override
    public Mono<Void> parseProtocol(MqttUnsubAckMessage message, MqttChannel mqttChannel, ContextView contextView) {
        return mqttChannel.cancelRetry(MqttMessageType.UNSUBSCRIBE,message.variableHeader().messageId());
    }

    @Override
    public List<MqttMessageType> getMqttMessageTypes() {
        return MESSAGE_TYPE_LIST;
    }


}
