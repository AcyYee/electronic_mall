package com.sunwuo.electronic_mall.util;

import java.util.UUID;

public class UUIDUtill {
    public static String getOrderTag(String storeKey) {
        UUID uuid = UUID.randomUUID();
        StringBuilder builder = new StringBuilder();
        builder.append(storeKey);
        builder.append(TimeUtil.getDateTime(5));
        String less = uuid.toString().replace("-","").substring(0,4);
        builder.append(less);
        return builder.toString();
    }

    public static String getUUID(int count) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","").substring(0,count);
    }

}
