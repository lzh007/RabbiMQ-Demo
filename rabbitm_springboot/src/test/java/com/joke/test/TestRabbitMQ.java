package com.joke.test;

import com.joke.RabbitmqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * topic 动态路由  订阅模型
     */
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topics", "user.save", "user.save 的路由消息");
    }


    /**
     * route 路由模型
     */
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("directs", "error", "发送 info 的 key 的路由消息");
    }

    /**
     * 广播 模型
     */
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout 的模型消息");
    }

    /**
     * work 模型 轮询消费（公平）
     */
    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型"+ i);
        }
    }

    /**
     * hello 模型
     */
    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
