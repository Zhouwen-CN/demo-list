package com.yy;

import com.streamxhub.streamx.flink.core.TableContext;
import com.streamxhub.streamx.flink.core.TableEnvConfig;

/**
 * @Author: chen
 * @Date: 2022/6/12 16:44
 * @Desc:
 */
public class StreamxSqlDemo {
    public static void main(String[] args) {
        TableEnvConfig config = new TableEnvConfig(args, null);
        TableContext context = new TableContext(config);

        context.sql("first");

        context.start();
    }
}
