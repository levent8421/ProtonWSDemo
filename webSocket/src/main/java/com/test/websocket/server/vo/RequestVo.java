package com.test.websocket.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class RequestVo {
    private String stationNo;//货架的id
    private int version;//设备定义版本
    private List<String> devices;//设备定义具体信息

    //json格式如下
    /**
     *
     */
}
