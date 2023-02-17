package com.test.websocket.server.controller;

import com.monolith.atom.scada.broker.packet.StationPacket;
import com.test.websocket.server.exception.BusinessException;
import com.test.websocket.server.hadnler.SpringWebSocketHandler;
import com.test.websocket.server.packet.PacketSerializer;
import com.test.websocket.server.packet.StationPacketExt;
import com.test.websocket.server.packet.StationPacketHeader;
import com.test.websocket.server.packet.StationPacketPayloadImpl;
import com.test.websocket.server.vo.DefinitionVo;
import com.test.websocket.server.vo.SetDefinition;
import com.test.websocket.server.util.GeneralResult;
import com.test.websocket.server.util.StationPacketUtils;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Create by lxp
 * Date: 2023/2/15 14:09
 * ClassName:
 * Description:
 * websocket controller
 *
 * @author lxp
 */
@Controller
@RequestMapping("/api/websocket")
public class WebSocketController {

    private final SpringWebSocketHandler springWebSocketHandler;
    private static final FastDateFormat TIMESTAMP_FORMAT = FastDateFormat.getInstance("YYYYMMddHHmmssSSS");

    public WebSocketController(SpringWebSocketHandler springWebSocketHandler) {
        this.springWebSocketHandler = springWebSocketHandler;
    }

    /**
     * 指定发送
     */
    @PostMapping("/send_user")
    public GeneralResult<String> sendDefinition(@RequestBody SetDefinition requestVo) throws BusinessException {
        DefinitionVo payload = new DefinitionVo();
        //设备定义数组
        payload.setDevices(requestVo.getDevices());
        //设备定义版本
        payload.setVersion(requestVo.getVersion());
        //将payload的内容存入,生成要发送的json
        String packet = sendAction(requestVo.getStationNo(), "device.set_definition", payload);
        TextWebSocketFrame frame = new TextWebSocketFrame(packet);
        springWebSocketHandler.sendMessageToUser(requestVo.getStationNo(), frame);
        System.out.println("发送至：" + requestVo.getStationNo());
        return GeneralResult.ok("success");
    }

    /**
     * 生成发送的json
     *
     * @param stationNo 设备id
     * @param action    设备具体执行动作
     * @param payload   发送的具体数据内容，该数据类型与具体业务相关
     * @return json
     * @throws BusinessException BusinessException
     */
    public String sendAction(String stationNo, String action, Object payload) throws BusinessException {
        StationPacket packet = new StationPacket();
        packet.setType(StationPacket.REQUEST);
        packet.setProtocolVer(StationPacket.PROTOCOL_VERSION_V1);
        StationPacketHeader header = new StationPacketHeader();
        packet.setHeader(header);
        header.setAction(action);
        header.setActionVer("V0.1");
        header.setTrace("T1234567890");
        header.setPriority(StationPacketHeader.DEFAULT_PRIORITY);
        header.setTimestamp(TIMESTAMP_FORMAT.format(new Date()));
        StationPacketExt ext = new StationPacketExt();
        packet.setExt(ext);
        ext.setStation(stationNo);
        packet.setPayload(new StationPacketPayloadImpl(payload));
        String sign = StationPacketUtils.sign(packet, "levent8421");
        header.setSign(sign);
        return PacketSerializer.INSTANCE.serialize(packet);
    }
}

