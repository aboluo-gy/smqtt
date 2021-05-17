package io.github.quickmsg.broker.common.core.websocket;


import io.github.quickmsg.broker.common.core.DefaultTransport;
import io.github.quickmsg.broker.common.core.mqtt.MqttConfiguration;
import io.github.quickmsg.broker.common.transport.Transport;
import io.github.quickmsg.broker.common.transport.TransportFactory;

/**
 * @Author luxurong
 */
public class WebSocketMqttTransportFactory implements TransportFactory<MqttConfiguration> {

    @Override
    public Transport<MqttConfiguration> createTransport(MqttConfiguration config) {
        return new DefaultTransport(config, new WebSocketMqttReceiver());
    }


}
