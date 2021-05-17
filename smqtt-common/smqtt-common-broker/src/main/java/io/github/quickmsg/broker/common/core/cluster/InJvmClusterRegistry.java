package io.github.quickmsg.broker.common.core.cluster;


import io.github.quickmsg.broker.common.cluster.ClusterConfig;
import io.github.quickmsg.broker.common.cluster.ClusterMessage;
import io.github.quickmsg.broker.common.cluster.ClusterNode;
import io.github.quickmsg.broker.common.cluster.ClusterRegistry;
import io.github.quickmsg.broker.common.enums.ClusterEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @Author luxurong
 */
public class InJvmClusterRegistry implements ClusterRegistry {


    @Override
    public void registry(ClusterConfig c) {

    }

    @Override
    public Flux<ClusterMessage> handlerClusterMessage() {
        return Flux.empty();
    }

    @Override
    public Flux<ClusterEvent> clusterEvent() {
        return Flux.empty();
    }

    @Override
    public List<ClusterNode> getClusterNode() {
        return Collections.emptyList();
    }

    @Override
    public Mono<Void> spreadMessage(ClusterMessage clusterMessage) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> shutdown() {
        return Mono.empty();
    }
}
