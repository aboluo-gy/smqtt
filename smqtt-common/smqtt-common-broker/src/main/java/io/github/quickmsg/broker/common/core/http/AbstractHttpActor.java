package io.github.quickmsg.broker.common.core.http;

import io.github.quickmsg.broker.common.channel.MockMqttChannel;
import io.github.quickmsg.broker.common.core.DefaultTransport;
import io.github.quickmsg.broker.common.http.ClusterWrapper;
import io.github.quickmsg.broker.common.http.HttpActor;
import io.github.quickmsg.broker.common.protocol.ProtocolAdaptor;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

/**
 * @Author luxurong
 */
public abstract class AbstractHttpActor implements HttpActor {

    private final ProtocolAdaptor protocolAdaptor = new ClusterWrapper(DefaultTransport.receiveContext.getProtocolAdaptor());

    /**
     * 发送mqtt消息
     *
     * @param mqttPublishMessage publish消息
     */
    public void sendMqttMessage(MqttPublishMessage mqttPublishMessage) {
        getProtocolAdaptor().chooseProtocol(MockMqttChannel.DEFAULT_MOCK_CHANNEL, mqttPublishMessage, DefaultTransport.receiveContext);
    }

    @Override
    public ProtocolAdaptor getProtocolAdaptor() {
        return this.protocolAdaptor;
    }
}
