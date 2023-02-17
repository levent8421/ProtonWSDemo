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
    private String action;
    private String actionVer;
    private String trace;
    private int priority;
    private String timestamp;
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
