package com.test.websocket.server.vo;

import lombok.Data;

import java.util.List;

/**
 * Create by lxp
 * Date: 2023/2/17 10:42
 * ClassName:
 * Description:
 * 设备定义
 *
 * @author lxp
 */
@Data
public class DefinitionVo {
    private int version;
    private List<String> devices;
}
