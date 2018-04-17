package com.whl.blog.common;

import java.util.*;

/**
 * Created by whling on 2017/6/4.
 *
 */
public class PPUtil {

    private ResourceBundle resourceBundle = null;

    public PPUtil(String resourceName){
        this.resourceBundle = ResourceBundle.
                getBundle(resourceName, Locale.getDefault());
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }

    public String[] getValues(String key){
        return resourceBundle.getStringArray(key);
    }

    public String[] getAllKeys(){
        List<String> keys = new ArrayList();
        Enumeration<?> enums = resourceBundle.getKeys();
        while(enums.hasMoreElements()){
            keys.add((String)enums.nextElement());
        }
        return keys.toArray(new String[keys.size()]);
    }

}
