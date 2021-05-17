package io.github.quickmsg.broker.common;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;

/**
 * @Author luxurong
 */
public interface Receiver {

    /**
     * 绑定接口
     *
     * @return DisposableServer
     */
    Mono<DisposableServer> bind();


}
