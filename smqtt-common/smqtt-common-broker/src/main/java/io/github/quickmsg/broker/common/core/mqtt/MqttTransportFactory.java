package io.github.quickmsg.broker.common.core.mqtt;


import io.github.quickmsg.broker.common.core.DefaultTransport;
import io.github.quickmsg.broker.common.transport.Transport;
import io.github.quickmsg.broker.common.transport.TransportFactory;

/**
 * @Author luxurong
 */
public class MqttTransportFactory implements TransportFactory<MqttConfiguration> {

    @Override
    public Transport<MqttConfiguration> createTransport(MqttConfiguration config) {
        return new DefaultTransport(config ,new MqttReceiver());
    }
}
