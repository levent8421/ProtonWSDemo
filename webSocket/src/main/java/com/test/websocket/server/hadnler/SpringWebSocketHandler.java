package com.test.websocket.server.hadnler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by lxp
 * Date: 2023/2/15 14:08
 * ClassName:
 * Description:
 *
 * @author lxp
 */
@Component
@Slf4j
public class SpringWebSocketHandler {
    /**
     * 存储用户stationNo和对应的连接
     */
    private static final Map<String, ChannelHandlerContext> users = new HashMap<>();

    /**
     * 初始key值
     */
    public static final String STATION_NO = "WEBSOCKET_USERID";

    /**
     * 初始连接存放
     *
     * @param ctx ctx
     */
    public void initialAddCtx(ChannelHandlerContext ctx) {
        users.put(STATION_NO, ctx);
    }

    /**
     * 添加连接
     *
     * @param usersId usersId
     * @param ctx     ctx
     */
    public void addUsers(String usersId, ChannelHandlerContext ctx) {
        System.out.println("成功建立websocket连接!");
        if (users.containsKey(STATION_NO)) {
            users.remove(STATION_NO);
        } else users.remove(usersId);
        users.put(usersId, ctx);
        System.out.println("当前线上用户数量:" + users.size());
        System.out.println("当前用户id:" + usersId);
    }

    /**
     * 获取所有连接
     *
     * @return users
     */
    public Map<String, ChannelHandlerContext> getUsers() {
        return users;
    }


    /**
     * 给某个设备发送消息
     */
    public void sendMessageToUser(String stationNo, TextWebSocketFrame message) {
        if (users.containsKey(stationNo)) {
            ChannelHandlerContext context = users.get(stationNo);
            users.get(stationNo).writeAndFlush(message);
        } else {
            log.info("当前设备未连接");
        }
    }
}

