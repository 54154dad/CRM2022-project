package com.bjpowernode.crm.commons.utils;

import java.util.UUID;

/**
 * @Classname UUIDUtils
 * @Date 2023/1/2
 * @Created by YQ
 */
public class UUIDUtils {
    /**
     * 回去uuid的值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
