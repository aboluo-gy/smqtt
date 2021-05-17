package io.github.quickmsg.broker.common.core.http;


import io.github.quickmsg.broker.common.http.annotation.Router;
import io.github.quickmsg.broker.common.http.enums.HttpType;
import io.github.quickmsg.broker.common.message.HttpPublishMessage;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

/**
 * @Author luxurong
 */
@Router(value = "/smqtt/publish", type = HttpType.POST)
@Slf4j
public class PublishActor extends AbstractHttpActor {


    @Override
    public Publisher<Void> doRequest(HttpServerRequest request, HttpServerResponse response) {
        return request
                .receive()
                .asString()
                .map(this.toJson(HttpPublishMessage.class))
                .doOnNext(message -> {
                    this.sendMqttMessage(message.getPublishMessage());
                    log.info("http request url {} body {}", request.path(), message);
                }).then(response.sendString(Mono.just("success")).then());
    }

}
