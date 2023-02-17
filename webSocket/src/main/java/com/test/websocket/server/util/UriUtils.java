package com.test.websocket.server.util;

import com.google.common.collect.Maps;
import com.test.websocket.server.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Create by Levent8421
 * Date: 2022/11/11 10:13
 * ClassName: UriUtils
 * Description:
 * URI工具类
 *
 * @author levent8421
 */
@Slf4j
public class UriUtils {
    public static Map<String, String> getQueries(String uri) throws BusinessException {
        int qStart = uri.indexOf('?');
        if (qStart < 0) {
            return Collections.emptyMap();
        }
        String queries = uri.substring(qStart + 1);
        if (StringUtils.isBlank(queries)) {
            return Collections.emptyMap();
        }
        String[] pairs = queries.split("&");
        Map<String, String> res = Maps.newHashMap();
        for (String pair : pairs) {
            if (StringUtils.isBlank(pair)) {
                continue;
            }
            int eStart = pair.indexOf('=');
            if (eStart < 0) {
                res.put(pair, null);
                continue;
            }
            String name = pair.substring(0, eStart);
            String value = pair.substring(eStart + 1);
            if (res.containsKey(name)) {
                String valueB = res.get(name);
                log.warn("duplicate query[{}] value=[{},{}]", name, value, valueB);
                res.put(name, valueB + "," + value);
            } else {
                res.put(name, value);
            }
        }
        return res;
    }
}
