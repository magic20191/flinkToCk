package org.kin.utils.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

import com.google.gson.JsonObject;

public class kafkaProducer implements Runnable{
    private KafkaProducer<String,String> producer;
    private String topic;
    String brokers = "10.0.0.87:9092,10.0.0.145:9092,10.0.0.146:9092";

    public kafkaProducer(String topicName){
        Properties props = new Properties();
        props.put("bootstrap.servers",brokers);
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer",StringSerializer.class);
        props.put("ack","all");
        props.put("retries",0);
        props.put("batch.size",1);

        this.topic = topicName;
        this.producer = new KafkaProducer<String,String>(props);
    }

    @Override
    public void run() {
        int messageNum = 1;
        String name = "chqIdcode" ;
        int age = 0;
        String sex = "男" ;
        int grade = 1;
        float rate = 2.0f;

        long start = System.currentTimeMillis();
        try {
            for(;;){
                Random r = new Random();
                int i1 = r.nextInt(3);
                String name2 = name  + i1 ;
                age = r.nextInt(100);
                grade = r.nextInt(100);
                rate = r.nextFloat()*100;

//                Date date = new Date();
                //定义一下测试数据的格式
                //创建一个json对象，相当于一个容器，当然这个容器还可以套在另外一个容器里面，这个看业务需要
                JsonObject jsonCmessageStrontainer =new JsonObject();
                //为当前的json对象添加键值对
                jsonCmessageStrontainer.addProperty("name", name2);
                jsonCmessageStrontainer.addProperty("age", age);
                jsonCmessageStrontainer.addProperty("sex", sex);
                jsonCmessageStrontainer.addProperty("grade", grade);
                jsonCmessageStrontainer.addProperty("rate", rate);

                System.out.println(jsonCmessageStrontainer.toString());
                producer.send(new ProducerRecord<String,String>(topic,null,jsonCmessageStrontainer.toString()));
                //生产10000条就打印
                /*if(messageNum%10000==0){
                    System.out.println("发送的消息："+messageStr);
                }*/
                //生产100w条就退出
                if(messageNum% 100==0){
                    System.out.println("成功发送了"+messageNum+"条");
                    break;
                }
                messageNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            producer.close();
            long end = System.currentTimeMillis();
            System.out.println("代码执行时间：" + (end - start) + "ms");
        }
    }
    public static void main(String[] args){
        kafkaProducer test = new kafkaProducer("chqtest");
        Thread thread = new Thread(test);
        thread.start();
    }
}
