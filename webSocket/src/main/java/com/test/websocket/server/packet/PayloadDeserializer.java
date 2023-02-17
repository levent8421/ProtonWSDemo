package com.test.websocket.server.packet;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * Create by Levent, 2022/12/30 14:27
 * ClassName: PayloadDeserializer
 * Packet Payload Deserializer
 *
 * @author levent
 */
public class PayloadDeserializer implements ObjectReader<StationPacketPayload> {
    public static final PayloadDeserializer INSTANCE = new PayloadDeserializer();

    @Override
    public StationPacketPayload readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        Object real = jsonReader.readAny();
        return new StationPacketPayloadImpl(real);
    }
}
