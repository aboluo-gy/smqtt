package com.github.smqtt;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.DefaultEventExecutor;
import org.junit.Test;
import reactor.netty.ConnectionObserver;
import reactor.netty.DisposableServer;
import reactor.netty.FutureMono;
import reactor.netty.channel.ChannelMetricsRecorder;
import reactor.netty.tcp.TcpServer;

import java.net.SocketAddress;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
public class ServerTest {
    @Test
    public void testIssue688() throws Exception {

        CountDownLatch connected = new CountDownLatch(1);
        CountDownLatch configured = new CountDownLatch(1);
        CountDownLatch disconnected = new CountDownLatch(1);

        ChannelGroup group = new DefaultChannelGroup(new DefaultEventExecutor());
        DisposableServer server =
                TcpServer.create()
                        .port(8111)
                        .option(ChannelOption.ALLOW_HALF_CLOSURE,true)
                        .doOnConnection(connection -> {
                            connection.inbound().receive().asString().subscribe(s -> {
                                System.out.println(Thread.currentThread().toString()+":"+s);
                            });
                        })
                        .childObserve((connection, newState) -> {
                            System.out.println("********:"+newState);

                            connection.markPersistent(false);
                            if (newState == ConnectionObserver.State.CONNECTED) {
                                group.add(connection.channel());
                                connected.countDown();
                            }
                            else if (newState == ConnectionObserver.State.CONFIGURED) {
                                configured.countDown();
                            }
                            else if (newState == ConnectionObserver.State.DISCONNECTING) {
                                disconnected.countDown();
                            }
                            else if (newState == ConnectionObserver.State.ACQUIRED) {
                                disconnected.countDown();
                            }
                            else if (newState == ConnectionObserver.State.RELEASED) {
                                disconnected.countDown();
                            }
                        })
                        .wiretap(true)
                        .bindNow();
        for(;;){
            Thread.sleep(1000);
            System.out.println(group);
        }
//        Thread.sleep(300000);
//
//
//        FutureMono.from(group.close())
//                .block(Duration.ofSeconds(30));
//
//
//        server.disposeNow();

    }
}
