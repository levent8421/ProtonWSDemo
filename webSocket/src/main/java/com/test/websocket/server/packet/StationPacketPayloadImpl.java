package com.test.websocket.server.packet;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.test.websocket.server.exception.BusinessException;

import java.util.List;

/**
 * Create by Levent, 2022/12/30 15:09
 * ClassName: StationPacketPayloadImpl
 * IMPL by fastjson2
 *
 * @author levent
 */
public class StationPacketPayloadImpl implements StationPacketPayload {
    private final Object readObject;

    public StationPacketPayloadImpl(Object readObject) {
        this.readObject = readObject;
    }

    @Override
    public Object getRealObj() {
        return readObject;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toJavaObj(Class<T> clazz) throws BusinessException {
        if (readObject == null) {
            return null;
        }
        if (clazz.isInstance(readObject)) {
            return (T) readObject;
        }
        if (!(readObject instanceof JSONObject)) {
            throw new BusinessException("Can not convert [" + readObject + "] to target type:" + clazz);
        }
        JSONObject jsonObj = (JSONObject) readObject;
        return jsonObj.toJavaObject(clazz);
    }

    @Override
    public <T> List<T> toJavaArray(Class<T> clazz) throws BusinessException {
        if (readObject == null) {
            return null;
        }
        if (!(readObject instanceof JSONArray)) {
            throw new BusinessException("Can not convert [" + readObject + "] to target ArrayType:" + clazz);
        }
        JSONArray jsonArray = (JSONArray) readObject;
        return jsonArray.toJavaList(clazz);
    }

    @Override
    public boolean isConvertSupported() {
        return readObject == null || readObject instanceof JSONObject || readObject instanceof JSONArray;
    }
}
