package com.test.websocket.server.vo;

import lombok.Data;

import java.util.List;

/**
 * Create by lxp
 * Date: 2023/2/17 10:42
 * ClassName:
 * Description:
 * http消息接受
 *
 * @author lxp
 */
@Data
public class SetDefinition {
    private String stationNo;//货架的id
    private int version;//设备定义版本
    private List<String> devices;//设备定义具体信息

    /**
     *
     * json格式如下
     *{
     *     "devices": [
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-1/101?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-1/1?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-2/102?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-2/2?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-3/103?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-3/3?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-4/104?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-4/4?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-5/105?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-5/5?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-6/106?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-6/6?device=weight_sensor",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-7/107?device=esl&esl_role=master",
     *         "ds3p:uart://%2Fdev%2FttyS3:115200/WT/L1-1-7/7?device=weight_sensor",
     *     ],
     *     "version": 1,
     *     "stationNo": "62VGJMJ584400"
     * }
     *
     *
     */
}
