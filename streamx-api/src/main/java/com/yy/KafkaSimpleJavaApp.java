package com.yy;

import com.entity.WaterSensor;
import com.streamxhub.streamx.flink.core.StreamEnvConfig;
import com.streamxhub.streamx.flink.core.java.function.SQLFromFunction;
import com.streamxhub.streamx.flink.core.java.sink.JdbcSink;
import com.streamxhub.streamx.flink.core.java.source.KafkaSource;
import com.streamxhub.streamx.flink.core.scala.StreamingContext;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

public class KafkaSimpleJavaApp {

    public static void main(String[] args) {
        StreamEnvConfig envConfig = new StreamEnvConfig(args, null);
        StreamingContext context = new StreamingContext(envConfig);
        SingleOutputStreamOperator<WaterSensor> ds = new KafkaSource<String>(context)
                .topic("t1")
                .getDataStream()
                .map(record -> {
                    String[] strings = record.value().split(",");
                    return new WaterSensor(strings[0], Long.parseLong(strings[1]), Integer.parseInt(strings[2]));
                });

        new JdbcSink<WaterSensor>(context)
                .sql(new SQLFromFunction<WaterSensor>() {
                    @Override
                    public String from(WaterSensor waterSensor) {
                        return String.format(
                                "insert into water_sensor(id,ts,vc) values('%s',%d,%d)"
                                , waterSensor.getId(), waterSensor.getTs(), waterSensor.getVc());
                    }
                })
                .sink(ds);

        context.start();
    }
}
