package io.github.quickmsg.broker.common.cluster;

import lombok.Builder;
import lombok.Data;

/**
 * @Author luxurong
 */
@Data
@Builder
public class ClusterConfig {

    private Boolean clustered;

    private Integer port;

    private String nodeName;

    private String clusterUrl;

    public static ClusterConfig defaultClusterConfig() {
        return ClusterConfig.builder()
                .clustered(false)
                .port(0)
                .build();
    }
}
