package io.github.quickmsg.broker.common.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author luxurong
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClusterMessage {

    private String topic;

    private int qos;

    private boolean retain;

    private byte[] message;
}
