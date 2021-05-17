package io.github.quickmsg.broker.common.core;


import io.github.quickmsg.broker.common.interceptor.Interceptor;
import io.netty.handler.codec.mqtt.MqttMessageType;

/**
 * @Author luxurong
 */
public class MonitorInterceptor implements Interceptor {


    @Override
    public Object[] doInterceptor(Object[] args) {
        return args;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public MqttMessageType interceptorType() {
        return MqttMessageType.PUBLISH;
    }
}
