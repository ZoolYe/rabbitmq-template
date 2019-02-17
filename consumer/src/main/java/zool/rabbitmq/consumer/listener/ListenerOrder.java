package zool.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import zool.rabbitmq.common.entity.Order;

import java.io.IOException;
import java.util.Map;

/**
 * @author：zoolye
 * @date：2019-02-15：21:33
 * @description：
 */
@Component
public class ListenerOrder {

    @RabbitListener(bindings = @QueueBinding
            (value = @Queue(value = "order-queue", durable = "true"),
             exchange = @Exchange(value = "order-exchange", type = "topic"),
             key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.err.println("---------------- 收到Order队列消息 ----------------");
        System.err.println(order.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }

}
