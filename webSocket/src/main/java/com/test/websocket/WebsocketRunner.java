package com.test.websocket;

import com.test.websocket.server.WebsocketServer;
import com.test.websocket.server.exception.BusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Create by lxp
 * Date: 2023/2/15 14:19
 * ClassName:
 * Description:
 *
 * @author lxp
 */
@Component
public class WebsocketRunner implements CommandLineRunner {
    private final WebsocketServer websocketServer;

    public WebsocketRunner(WebsocketServer websocketServer) {
        this.websocketServer = websocketServer;
    }

    @Override
    public void run(String... args) {
        try {
            websocketServer.start();
        } catch (BusinessException e) {
            throw new RuntimeException(ExceptionUtils.getMessage(e), e);
        }
    }
}
