package com.whl.blog.web.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whling on 2017/6/5.
 *
 */
public class PPSUtil {

    private Map<String, EValue> propertiesMap = new HashMap<>();

    public PPSUtil(String... resourcesName) {
        for (String resourceName: resourcesName) {
            PPUtil ppUtil = new PPUtil(resourceName);
            for (String key: ppUtil.getAllKeys()) {
                Assert.notEmpty(ppUtil.getValue(key), "必要参数 [" + key + "] 不能为空！");
                propertiesMap.put(key, new EValue(ppUtil.getValue(key)));
            }
        }
    }

    public EValue getEValue(String key) {
        return propertiesMap.get(key);
    }

    public class EValue {

        private String value;

        public EValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public boolean getBooleanValue() {
            return Boolean.parseBoolean(getValue());
        }

        public int getIntValue() {
            return Integer.parseInt(getValue());
        }

        public long getLongValue() {
            return Long.parseLong(getValue());
        }

        public String[] getArray(String separator) {
            return separator.split(separator);
        }

    }
}
