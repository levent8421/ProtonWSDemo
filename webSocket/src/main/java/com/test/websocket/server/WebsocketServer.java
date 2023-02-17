package com.test.websocket.server;

import com.test.websocket.server.exception.BusinessException;

/**
 * Create by lxp
 * Date: 2023/2/15 14:20
 * ClassName:
 * Description:
 *
 * @author lxp
 */
public interface WebsocketServer {
    /**
     * 启动服务
     *
     * @throws BusinessException BusinessException
     */
    void start() throws BusinessException;
    /**
     * 停止服务
     *
     * @throws BusinessException BusinessException
     */
    void shutdown() throws BusinessException;
}
