package io.github.quickmsg.broker.common.transport;


import io.github.quickmsg.broker.common.config.Configuration;
import io.github.quickmsg.broker.common.context.ReceiveContext;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * @Author luxurong
 */
public interface Transport<C extends Configuration> extends Disposable {


    /**
     * 开启连接
     *
     * @return Disposable 连接操作类
     */
    Mono<Transport> start();


    /**
     * 构建接受处理🥱
     *
     * @param c 启动参数
     * @return ReceiveContext
     */
    ReceiveContext<C> buildReceiveContext(C c);








}
