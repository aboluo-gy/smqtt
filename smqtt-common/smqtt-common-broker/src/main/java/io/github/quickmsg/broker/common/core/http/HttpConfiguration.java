package io.github.quickmsg.broker.common.core.http;


import io.github.quickmsg.broker.common.cluster.ClusterConfig;
import io.github.quickmsg.broker.common.config.Configuration;
import io.github.quickmsg.broker.common.config.SslContext;
import lombok.Data;

/**
 * @Author luxurong
 */
@Data
public class HttpConfiguration implements Configuration {


    private Integer port = 0;

    private Boolean wiretap = false;

    private Boolean ssl = false;

    private SslContext sslContext;

    private Integer bossThreadSize = Runtime.getRuntime().availableProcessors();

    private Integer workThreadSize = Runtime.getRuntime().availableProcessors();

    private Boolean accessLog;


    @Override
    public Integer getLowWaterMark() {
        return 0;
    }

    @Override
    public Integer getHighWaterMark() {
        return 0;
    }

    @Override
    public ClusterConfig getClusterConfig() {
        throw new UnsupportedOperationException("getClusterConfig");
    }
}
