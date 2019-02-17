package zool.rabbitmq.producer.push;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zool.rabbitmq.common.entity.Order;

/**
 * @author：zoolye
 * @date：2019-02-15：21:26
 * @description：
 */
@Component
public class SendMsg {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order){
        rabbitTemplate.convertAndSend("order-exchange","order.abc",order);
    }

}
