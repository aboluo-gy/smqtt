package io.github.quickmsg.broker.common.message;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.Builder;
import lombok.Data;

/**
 * @Author luxurong
 */
@Data
@Builder
public class SubscribeChannelContext {

    private MqttQoS mqttQoS;

    private MqttChannel mqttChannel;

    private String topic;

}
