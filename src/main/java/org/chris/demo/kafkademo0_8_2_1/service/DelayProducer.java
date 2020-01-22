package org.chris.demo.kafkademo0_8_2_1.service;

import kafka.consumer.SimpleConsumer;
import kafka.producer.ProducerConfig;
import kafka.producer.ProducerPool;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class DelayProducer {


    public static void main(String[] args) {
        //DelayedOperation
        Properties props = new Properties();
        props.put("client.id", "DemoProducer");
        props.put("bootstrap.servers", "127.0.0.1:9091,127.0.0.1:9092,127.0.0.1:9093");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(props);
        ProducerRecord producerRecord = new ProducerRecord("chris_test","Key","value");
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata);
                System.out.println(exception);
            }
        });
    }

}
