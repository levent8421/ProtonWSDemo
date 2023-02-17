package com.test.websocket.server.packet;

/**
 * Create by Levent, 2022/12/30 14:24
 * ClassName: StationPacketExt
 * Packet Ext
 *
 * @author levent
 */
public class StationPacketExt {
    public static final int CODE_ERROR = 500;
    public static final int CODE_OK = 200;
    public static final int CODE_PARAM_ERROR = 400;
    private String appType;
    private String station;
    private Integer deviceVer;
    private Integer bindingVer;
    private String appVer;
    private Integer resCode;
    private String resMsg;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Integer getDeviceVer() {
        return deviceVer;
    }

    public void setDeviceVer(Integer deviceVer) {
        this.deviceVer = deviceVer;
    }

    public Integer getBindingVer() {
        return bindingVer;
    }

    public void setBindingVer(Integer bindingVer) {
        this.bindingVer = bindingVer;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }
}
