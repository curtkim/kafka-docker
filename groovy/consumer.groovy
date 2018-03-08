@Grab('org.apache.kafka:kafka-clients:1.0.1')

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

Properties props = new Properties();
props.put("bootstrap.servers", '172.26.114.137:32768'); //""
props.put("group.id", "test");
props.put("enable.auto.commit", "true");
props.put("auto.commit.interval.ms", "1000");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


consumer.subscribe(Arrays.asList("topic"));

while (true) {
  ConsumerRecords<String, String> records = consumer.poll(10);
  for (ConsumerRecord<String, String> record : records)
    System.out.printf("offset = %d, key = %s, value = %s partition = %s, timestamp = %s %s %n", record.offset(), record.key(), record.value(), record.partition(), record.timestamp(), new Date(record.timestamp()));
}