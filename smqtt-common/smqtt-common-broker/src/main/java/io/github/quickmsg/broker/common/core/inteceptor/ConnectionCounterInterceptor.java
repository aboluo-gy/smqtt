package io.github.quickmsg.broker.common.core.inteceptor;


import io.github.quickmsg.broker.common.context.ReceiveContext;
import io.github.quickmsg.broker.common.interceptor.Interceptor;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author luxurong
 */
@Slf4j
public class ConnectionCounterInterceptor implements Interceptor {


    @Override
    public Object[] doInterceptor(Object[] args) {
        ReceiveContext<?> receiveContexts = (ReceiveContext<?>) args[2];
        log.info(" client registry connection size {}", receiveContexts.getChannelRegistry().counts());
        return args;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public MqttMessageType interceptorType() {
        return MqttMessageType.CONNECT;
    }
}
