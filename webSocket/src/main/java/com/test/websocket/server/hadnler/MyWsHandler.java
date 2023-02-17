package com.test.websocket.server.hadnler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.test.websocket.server.exception.BusinessException;
import com.test.websocket.server.util.ByteUtils;
import com.test.websocket.server.util.UriUtils;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Create by lxp
 * Date: 2023/2/15 14:12
 * ClassName:
 * Description:
 * websocket handler
 *
 * @author lxp
 */
@Component
public class MyWsHandler extends SimpleChannelInboundHandler<Object> {
    static final Logger log = LoggerFactory.getLogger(MyWsHandler.class);
    private WebSocketServerHandshaker handshaker;
    private final SpringWebSocketHandler springWebSocketHandler;

    public MyWsHandler(SpringWebSocketHandler springWebSocketHandler) {
        this.springWebSocketHandler = springWebSocketHandler;
    }

    //读就绪事件
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            try {
                synchronized (MyWsHandler.class) {
                    handleHttpRequest(ctx, (FullHttpRequest) msg);
                }
            } catch (Exception e) {
                log.error("Error on resolve websocket http request", e);
                ctx.close().sync();
            }
        } else if (msg instanceof WebSocketFrame) {
            handleWebsocket(ctx, (WebSocketFrame) msg);
        } else {
            log.warn("Unexpected channel msg:[{}]", msg.getClass());
        }
    }

    //连接建立
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("web 客户端连接");
        //asLongText 返回的是唯一的
        System.out.println(ctx.channel().id().asLongText());
        springWebSocketHandler.initialAddCtx(ctx);

    }

    //连接断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("连接断开");
    }

    //异常处理事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常");
        ctx.close();
    }

    private void sendResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse resp) {
        HttpResponseStatus status = resp.status();
        if (status != HttpResponseStatus.OK) {
            ByteBufUtil.writeUtf8(resp.content(), status.toString());
            HttpUtil.setContentLength(req, resp.content().readableBytes());
        }
        boolean keepAlive = HttpUtil.isKeepAlive(req) && status == HttpResponseStatus.OK;
        HttpUtil.setKeepAlive(req, keepAlive);
        ChannelFuture future = ctx.write(resp);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws BusinessException {
        if (!request.decoderResult().isSuccess()) {
            sendResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.BAD_REQUEST, ctx.alloc().buffer()));
            return;
        }
        if (!HttpMethod.GET.equals(request.method())) {
            sendResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.METHOD_NOT_ALLOWED, ctx.alloc().buffer()));
            return;
        }
        String uri = request.uri();
        Map<String, String> queries;
        try {
            queries = UriUtils.getQueries(uri);
        } catch (Exception e) {
            throw new BusinessException(ExceptionUtil.getMessage(e), e);
        }
        String stationId = queries.get("SID");
        if (StringUtils.isBlank(stationId)) {
            throw new BusinessException("Can not find stationId in:" + uri);
        }
        springWebSocketHandler.addUsers(stationId, ctx);
        String wsFullPath = getWebSocketLocation(request);
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(wsFullPath, null, false);
        handshaker = handshakerFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    private String getWebSocketLocation(FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        String host = headers.get(HttpHeaderNames.HOST);
        return "ws://" + host;
    }

    private void handleWebsocket(ChannelHandlerContext ctx, WebSocketFrame frame) {

        if (frame instanceof PingWebSocketFrame) {
            PongWebSocketFrame resp = new PongWebSocketFrame(frame.content().retain());
            ctx.writeAndFlush(resp);
            return;
        }
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame reqFrame = (TextWebSocketFrame) frame;
            handleText(ctx, reqFrame);
            return;
        }
        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binFrame = (BinaryWebSocketFrame) frame;
            byte[] bin = binFrame.content().array();
            String hex = ByteUtils.byteHex(bin);
            log.warn("BIN:[{}]", hex);
        }
    }

    private void handleText(ChannelHandlerContext ctx, TextWebSocketFrame reqFrame) {
        String reqText = reqFrame.text();
        log.info(reqText);
    }
}


