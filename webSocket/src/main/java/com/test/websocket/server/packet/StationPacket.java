package com.monolith.atom.scada.broker.packet;


import com.alibaba.fastjson2.annotation.JSONField;
import com.test.websocket.server.packet.PayloadSerializer;
import com.test.websocket.server.packet.StationPacketExt;
import com.test.websocket.server.packet.StationPacketHeader;
import com.test.websocket.server.packet.StationPacketPayload;

/**
 * Create by Levent, 2022/12/30 14:16
 * ClassName: StationPacket
 * Station Packet
 *
 * @author levent
 */
public class StationPacket {
    public static final String REQUEST = "request";
    public static final String RESPONSE = "response";
    public static final int PROTOCOL_VERSION_V1 = 1;

    private String type;
    private int protocolVer;
    private StationPacketHeader header;
    private StationPacketExt ext;
    @JSONField(serializeUsing = PayloadSerializer.class)
    private StationPacketPayload payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProtocolVer() {
        return protocolVer;
    }

    public void setProtocolVer(int protocolVer) {
        this.protocolVer = protocolVer;
    }

    public StationPacketHeader getHeader() {
        return header;
    }

    public void setHeader(StationPacketHeader header) {
        this.header = header;
    }

    public StationPacketExt getExt() {
        return ext;
    }

    public void setExt(StationPacketExt ext) {
        this.ext = ext;
    }

    public StationPacketPayload getPayload() {
        return payload;
    }

    public void setPayload(StationPacketPayload payload) {
        this.payload = payload;
    }
}
