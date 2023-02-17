package com.test.websocket.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class DefinitionVo {
    private int version;
    private List<String> devices;
}
