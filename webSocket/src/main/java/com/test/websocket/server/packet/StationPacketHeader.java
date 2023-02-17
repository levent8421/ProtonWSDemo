package com.test.websocket.server.packet;

/**
 * Create by Levent, 2022/12/30 14:23
 * ClassName: StationPacketHeader
 * Packet Header
 *
 * @author levent
 */
public class StationPacketHeader {
    public static final int DEFAULT_PRIORITY = 3;
    /**
     * 请求发起方期望执行的动作，枚举值，请参考本文档后续描述
     */
    private String action;
    /**
     * 对执行动作的附加描述，各版本的动作内容可能不同，当前版本取值均为"V0.1"
     */
    private String actionVer;
    /**
     * 该数据包的唯一标识，要求响应数据包的trace值与请求数据包的trace值保持一致
     */
    private String trace;
    /**
     * 该数据包的处理优先级，当前协议取值均为"3"
     */
    private int priority;
    /**
     * 当前时间戳。
     * 注意：指令接收方在收到请求数据包时，会先检查该时间戳与本机时间戳的时间差，当时间差在10s以外时，将认为该请求是非法请求
     */
    private String timestamp;
    /**
     * 签名:
     * 1. 多层嵌套的字段名可取当前层的字段名，如header.action的最终字段名为action。
     * 2. 遇到字段值为null的字段时，拼接字段值可使用空字符串""代替。
     *
     */
    private String sign;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionVer() {
        return actionVer;
    }

    public void setActionVer(String actionVer) {
        this.actionVer = actionVer;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
