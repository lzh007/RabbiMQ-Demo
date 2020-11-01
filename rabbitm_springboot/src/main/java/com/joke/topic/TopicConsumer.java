package com.joke.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 主题模式消费者
 *
 * @author: Joker
 * @date: 2020/11/01
 **/
@Component
public class TopicConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(type = "topic", name = "topics"), //绑定交换机名称和类型
                    key = {"user.save", "user.*"}
            )
    })
    public void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(type = "topic", name = "topics"), //绑定交换机名称和类型
                    key = {"order.#", "produce.#","user.*"}
            )
    })
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
