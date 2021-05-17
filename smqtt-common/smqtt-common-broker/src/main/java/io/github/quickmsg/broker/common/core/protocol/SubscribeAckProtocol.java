package io.github.quickmsg.broker.common.core.protocol;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.protocol.Protocol;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author luxurong
 */
public class SubscribeAckProtocol implements Protocol<MqttSubAckMessage> {

    private static List<MqttMessageType> MESSAGE_TYPE_LIST = new ArrayList<>();


    static {
        MESSAGE_TYPE_LIST.add(MqttMessageType.SUBACK);
    }

    @Override
    public Mono<Void> parseProtocol(MqttSubAckMessage message, MqttChannel mqttChannel, ContextView contextView) {
        return mqttChannel.cancelRetry(MqttMessageType.SUBSCRIBE,message.variableHeader().messageId());
    }

    @Override
    public List<MqttMessageType> getMqttMessageTypes() {
        return MESSAGE_TYPE_LIST;
    }


}
