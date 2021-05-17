package io.github.quickmsg.broker.common.core.http;


import io.github.quickmsg.broker.common.http.HttpActor;
import io.github.quickmsg.broker.common.http.annotation.Router;
import reactor.netty.http.server.HttpServerRoutes;

import java.util.function.Consumer;

/**
 * @Author luxurong
 */
public class HttpRouterAcceptor implements Consumer<HttpServerRoutes> {

    @Override
    public void accept(HttpServerRoutes httpServerRoutes) {
        HttpActor.INSTANCE.forEach(httpActor -> {
            Router router = httpActor.getClass().getAnnotation(Router.class);
            switch (router.type()) {
                case PUT:
                    httpServerRoutes
                            .put(router.value(), httpActor::doRequest);
                    break;
                case POST:
                    httpServerRoutes
                            .post(router.value(), httpActor::doRequest);
                    break;
                case DELETE:
                    httpServerRoutes
                            .delete(router.value(), httpActor::doRequest);
                    break;
                case GET:
                default:
                    httpServerRoutes
                            .get(router.value(), httpActor::doRequest);
                    break;
            }
        });
    }


}
