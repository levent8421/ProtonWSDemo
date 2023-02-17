package com.test.websocket.server.packet;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * Create by Levent, 2022/12/30 14:26
 * ClassName: PayloadSerializer
 * Payload Serializer
 *
 * @author levent
 */
public class PayloadSerializer implements ObjectWriter<StationPacketPayload> {
    public static final PayloadSerializer INSTANCE = new PayloadSerializer();

    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (object == null) {
            jsonWriter.writeNull();
            return;
        }
        if (!(object instanceof StationPacketPayload)) {
            throw new IllegalArgumentException("Unsupported payload type:" + object.getClass());
        }
        StationPacketPayload payload = (StationPacketPayload) object;
        Object realObj = payload.getRealObj();
        if (realObj == null) {
            jsonWriter.writeNull();
            return;
        }
        jsonWriter.writeAny(realObj);
    }
}
