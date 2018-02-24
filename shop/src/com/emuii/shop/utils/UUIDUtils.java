package com.emuii.shop.utils;

import java.util.UUID;

/**
 * 生成激活码
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
