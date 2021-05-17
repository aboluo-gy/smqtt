package io.github.quickmsg.broker.common.message;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.netty.ReactorNetty;

/**
 * @Author luxurong
 */
@Getter
@AllArgsConstructor
public class RetainMessage {

    private MqttQoS mqttQoS;

    private ByteBuf byteBuf;

    public void release() {
        ReactorNetty.safeRelease(byteBuf);
    }


}
