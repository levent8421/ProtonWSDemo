package com.test.websocket.server.packet;

import com.alibaba.fastjson2.JSON;
import com.monolith.atom.scada.broker.packet.StationPacket;

/**
 * Create by Levent, 2022/12/30 14:57
 * ClassName: PacketSerializer
 * 数据包序列化组件
 *
 * @author levent
 */
public class PacketSerializer {

    public static final PacketSerializer INSTANCE = new PacketSerializer();

    static {
        JSON.register(StationPacketPayload.class, PayloadDeserializer.INSTANCE);
        JSON.register(StationPacketPayloadImpl.class, PayloadSerializer.INSTANCE);
    }

    public String serialize(StationPacket packet) {
        return JSON.toJSONString(packet);
    }

    public StationPacket deserialize(String packet) {
        return JSON.parseObject(packet, StationPacket.class);
    }
}
