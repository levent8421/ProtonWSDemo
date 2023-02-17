package com.test.websocket.server.packet;

import com.test.websocket.server.exception.BusinessException;

import java.util.List;

/**
 * Create by Levent, 2022/12/30 14:25
 * ClassName: StationPacketPayload
 * $.Payload
 *
 * @author levent
 */
public interface StationPacketPayload {
    Object getRealObj();

    <T> T toJavaObj(Class<T> clazz) throws BusinessException;

    <T> List<T> toJavaArray(Class<T> clazz) throws BusinessException;

    boolean isConvertSupported();
}
