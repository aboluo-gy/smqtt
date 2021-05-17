package io.github.quickmsg.broker.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author luxurong
 */
@AllArgsConstructor
@Getter
public class SslContext {

    private String crt;

    private String key;


}
