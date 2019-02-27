package com.caiw.kafkademo

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.api.scala._

object KafkaDemo {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(1000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)

    // configure Kafka consumer
    val kafkaProps = new Properties()
    kafkaProps.setProperty("bootstrap.servers", "caiw:9092")
    kafkaProps.setProperty("group.id", "cw_group_201903")
    kafkaProps.setProperty("enable.auto.commit", "false")

    val kafkaConsumer = new FlinkKafkaConsumer010[String]("cw_test001", new SimpleStringSchema(), kafkaProps)
    //topicd的名字是new，schema默认使用SimpleStringSchema()即可
    val transaction = env
      .addSource(
        kafkaConsumer
      )

    transaction.flatMap(_.split("\\s"))
      .map { w => WordWithCount(w, 1) }
      .keyBy("word")
      .sum("count")

    transaction.print()

    env.execute()

  }

  case class WordWithCount(word: String, count: Long)
}
