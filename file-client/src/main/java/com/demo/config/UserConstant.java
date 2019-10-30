package com.demo.config;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO:Responsibility description of this class.
 *
 * @author cheqiang
 * @version 1.0
 * @since 2019/10/29 1:28
 */
public class UserConstant {

    public static final Map<String,String> userMap = new HashMap<>();

    static {
        userMap.put("admin","admin");
        userMap.put("user1","user1");
    }

}
