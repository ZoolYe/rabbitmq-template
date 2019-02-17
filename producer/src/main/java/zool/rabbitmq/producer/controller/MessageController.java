package zool.rabbitmq.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zool.rabbitmq.common.entity.Order;
import zool.rabbitmq.producer.push.SendMessage;

/**
 * @author：zoolye
 * @date：2019-02-17：16:54
 * @description：
 */
@RestController
@RequestMapping("/rabbit/message")
public class MessageController {

    @Autowired
    SendMessage sendMessage;

    @PostMapping("/send/order")
    public Boolean sendOrder(@RequestBody Order order) {
        sendMessage.sendOrder(order);
        return true;
    }

}
