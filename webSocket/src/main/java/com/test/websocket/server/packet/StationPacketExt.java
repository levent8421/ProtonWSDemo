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
    /**
     * 数据包类型，枚举值，取值范围:
     * "request": 请求
     * "response": 响应
     */
    private String appType;
    /**
     * 发起请求的站点的站点号
     * 该字段在站点主动发起请求时有效
     */
    private String station;
    /**
     * 发起请求的站点的设备定义版本号
     * 该字段在站点主动发起请求时有效
     */
    private Integer deviceVer;
    /**
     * 发起请求的站点的数据绑定版本号
     * 该字段在站点主动发起请求时有效
     */
    private Integer bindingVer;
    /**
     * 发起请求的站点的应用版本号
     * 该字段在站点主动发起请求时有效
     */
    private String appVer;
    /**
     * 响应码，参考本文响应码相关描述
     */
    private Integer resCode;
    /**
     * 响应消息，响应码状态为错误时，该消息为错误原因，否则固定为"OK"
     */
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
