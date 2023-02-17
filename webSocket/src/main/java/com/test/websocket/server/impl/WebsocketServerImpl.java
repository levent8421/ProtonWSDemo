package com.test.websocket.server.impl;

import com.test.websocket.server.WebsocketServer;
import com.test.websocket.server.exception.BusinessException;
import com.test.websocket.server.hadnler.MyWsHandler;
import com.test.websocket.server.hadnler.SpringWebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Create by lxp
 * Date: 2023/2/15 14:24
 * ClassName:
 * Description:
 * websocket server 启动demo
 *
 * @author lxp
 */
@Component
public class WebsocketServerImpl implements WebsocketServer {
    static final Logger log = LoggerFactory.getLogger(WebsocketServerImpl.class);
    private final SpringWebSocketHandler springWebSocketHandler;

    public WebsocketServerImpl(SpringWebSocketHandler springWebSocketHandler) {
        this.springWebSocketHandler = springWebSocketHandler;
    }

    @Override
    public void start() throws BusinessException {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new HttpServerCodec());
                    // 对大数据流的支持
                    pipeline.addLast(new ChunkedWriteHandler());
                    //post请求分三部分. request line / request header / message body
                    // HttpObjectAggregator将多个信息转化成单一的request或者response对象
                    pipeline.addLast(new HttpObjectAggregator(8000));
                    //自定义handler处理业务逻辑
                    pipeline.addLast(new MyWsHandler(springWebSocketHandler));
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8091);
            log.debug("{} binding...", channelFuture.channel());
            channelFuture.sync();
            log.debug("{} bound...", channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        }
    }

    @Override
    public void shutdown() throws BusinessException {

    }
}
