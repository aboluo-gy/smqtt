package io.github.quickmsg.broker.common.http;


import io.github.quickmsg.broker.common.channel.MqttChannel;
import io.github.quickmsg.broker.common.config.Configuration;
import io.github.quickmsg.broker.common.context.ReceiveContext;
import io.github.quickmsg.broker.common.protocol.ProtocolAdaptor;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

/**
 * @Author luxurong
 */
public class ClusterWrapper implements ProtocolAdaptor {

    private final ProtocolAdaptor protocolAdaptor;

    public ClusterWrapper(ProtocolAdaptor protocolAdaptor) {
        this.protocolAdaptor = protocolAdaptor;
    }


    @Override
    public <C extends Configuration> void chooseProtocol(MqttChannel mqttChannel, MqttMessage mqttMessage, ReceiveContext<C> receiveContext) {
        if (receiveContext.getConfiguration().getClusterConfig().getClustered() && mqttMessage instanceof MqttPublishMessage) {
            receiveContext.getClusterRegistry().spreadPublishMessage((MqttPublishMessage) mqttMessage).subscribe();
        }
        protocolAdaptor.chooseProtocol(mqttChannel, mqttMessage, receiveContext);
    }

}
