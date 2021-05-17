package io.github.quickmsg.broker.common.core.http;


import io.github.quickmsg.broker.common.transport.Transport;
import io.github.quickmsg.broker.common.transport.TransportFactory;

/**
 * @Author luxurong
 */
public class HttpTransportFactory implements TransportFactory<HttpConfiguration> {


    @Override
    public Transport<HttpConfiguration> createTransport(HttpConfiguration httpConfiguration) {
        return new HttpTransport(httpConfiguration, new HttpReceiver());
    }
}
