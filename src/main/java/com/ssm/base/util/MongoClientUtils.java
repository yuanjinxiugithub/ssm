package com.ssm.base.util;

import com.mongodb.MongoClientOptions;

public class MongoClientUtils {
    public static MongoClientOptions getMongoClientOptions() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.socketTimeout(10 * 1000);
        return builder.build();
    }
}
