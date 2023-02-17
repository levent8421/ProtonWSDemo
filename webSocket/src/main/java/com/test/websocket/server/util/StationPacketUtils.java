package com.test.websocket.server.util;

import com.google.common.collect.Lists;

import com.monolith.atom.scada.broker.packet.StationPacket;

import com.test.websocket.server.packet.StationPacketExt;
import com.test.websocket.server.packet.StationPacketHeader;
import com.test.websocket.server.packet.StationPacketPayloadImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Create by Levent, 2022/12/30 17:06
 * ClassName: StationPacketUtils
 * Station Packet Utils
 *
 * @author levent
 */
public class StationPacketUtils {
    private static final FastDateFormat TIMESTAMP_FORMAT = FastDateFormat.getInstance("YYYYMMddHHmmssSSS");

    public static StationPacket response(StationPacket request, int code, String msg, Object payload) {
        StationPacketPayloadImpl payloadWrapper = new StationPacketPayloadImpl(payload);
        StationPacket resp = new StationPacket();
        resp.setPayload(payloadWrapper);
        resp.setType(StationPacket.RESPONSE);
        resp.setProtocolVer(StationPacket.PROTOCOL_VERSION_V1);

        StationPacketExt ext = new StationPacketExt();
        resp.setExt(ext);
        ext.setResCode(code);
        ext.setResMsg(msg);

        StationPacketHeader respHeader = new StationPacketHeader();
        StationPacketHeader reqHeader = null;
        if (request != null) {
            reqHeader = request.getHeader();
        }
        resp.setHeader(respHeader);
        if (reqHeader != null) {
            respHeader.setAction(reqHeader.getAction());
            respHeader.setActionVer(reqHeader.getActionVer());
            respHeader.setTrace(reqHeader.getTrace());
            respHeader.setPriority(reqHeader.getPriority());

        } else {
            respHeader.setAction("No_Action");
            respHeader.setActionVer("V0.1");
            respHeader.setTrace("No_Trace");
            respHeader.setPriority(3);
        }
        respHeader.setTimestamp(timestamp());
        return resp;
    }

    public static String sign(StationPacket packet, String signKey) {
        List<String> items = Lists.newArrayList();
        items.add("type=" + packet.getType());
        items.add("protocolVer=" + packet.getProtocolVer());
        StationPacketHeader header = packet.getHeader();
        items.add("action=" + header.getAction());
        items.add("actionVer=" + header.getActionVer());
        items.add("trace=" + header.getTrace());
        items.add("timestamp=" + header.getTimestamp());
        items.add("priority=" + header.getPriority());
        Collections.sort(items);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String item : items) {
            if (first) {
                first = false;
            } else {
                sb.append('&');
            }
            sb.append(item);
        }
        sb.append(signKey);
        String signText = sb.toString();
        return DigestUtils.md5Hex(signText).toUpperCase(Locale.ROOT);
    }

    public static String timestamp() {
        return TIMESTAMP_FORMAT.format(new Date());
    }

    public static long parseTimestamp(String timestamp) throws ParseException {
        return TIMESTAMP_FORMAT.parse(timestamp).getTime();
    }
}
