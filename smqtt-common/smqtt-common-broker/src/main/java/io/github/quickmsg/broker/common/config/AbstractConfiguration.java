package io.github.quickmsg.broker.common.config;


import io.github.quickmsg.broker.common.auth.PasswordAuthentication;
import reactor.netty.tcp.TcpServerConfig;

import java.util.function.Consumer;

/**
 * @Author luxurong
 */
public interface AbstractConfiguration extends Configuration {


    Integer getWebSocketPort();


    Consumer<? super TcpServerConfig> getTcpServerConfig();


    PasswordAuthentication getReactivePasswordAuth();


}
