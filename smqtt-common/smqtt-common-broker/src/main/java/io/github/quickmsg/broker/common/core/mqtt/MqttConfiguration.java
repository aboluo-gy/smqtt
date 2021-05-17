package io.github.quickmsg.broker.common.core.mqtt;


import io.github.quickmsg.broker.common.auth.PasswordAuthentication;
import io.github.quickmsg.broker.common.cluster.ClusterConfig;
import io.github.quickmsg.broker.common.config.AbstractConfiguration;
import io.github.quickmsg.broker.common.config.SslContext;
import io.github.quickmsg.broker.common.core.ssl.AbstractSslHandler;
import io.netty.channel.ChannelOption;
import lombok.Data;
import reactor.netty.tcp.TcpServerConfig;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @Author luxurong
 */
@Data
public class MqttConfiguration extends AbstractSslHandler implements AbstractConfiguration {

    private Integer port = 0;

    private Integer webSocketPort = 0;

    private Integer lowWaterMark;

    private Integer highWaterMark;

    private Boolean wiretap = false;

    private Boolean ssl = false;

    private SslContext sslContext;

    private PasswordAuthentication reactivePasswordAuth = (u, p) -> true;

    private Integer bossThreadSize = Runtime.getRuntime().availableProcessors();

    private Integer workThreadSize = Runtime.getRuntime().availableProcessors();

    private Consumer<Map<ChannelOption<?>, ?>> options;

    private Consumer<Map<ChannelOption<?>, ?>> childOptions;

    private ClusterConfig clusterConfig = ClusterConfig.defaultClusterConfig();

    @Override
    public Consumer<? super TcpServerConfig> getTcpServerConfig() {
        return tcpServerConfig -> {
            Optional.ofNullable(options).ifPresent(options -> options.accept(tcpServerConfig.options()));
            Optional.ofNullable(childOptions).ifPresent(options -> options.accept(tcpServerConfig.childOptions()));
        };
    }
}
