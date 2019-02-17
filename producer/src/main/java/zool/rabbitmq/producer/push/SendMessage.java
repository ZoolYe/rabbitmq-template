package zool.rabbitmq.producer.push;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
public class SendMessage {

    @Autowired
    RabbitTemplate rabbitTemplate;

    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("Ack: " + ack);
            if (!ack) {
                System.err.println("\n" + "消费端未手动 Ack ，请手动处理此消息" + "\n");
                System.err.println("CorrelationData ID : " + correlationData.getId());
            } else {
                System.err.println("消费端已成功处理消息：" + "\n");
            }
        }
    };

    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.err.println("\n" + "消息投递失败，请手动处理：");
            System.err.println("replyCode: " + replyCode);
            System.err.println("replyText: " + replyText);
            System.err.println("exchange: " + exchange);
            System.err.println("routingKey: " + routingKey);
            System.err.println("消息内容：" + message + "\n");
        }
    };

    public void sendOrder(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("order-exchange", "order.abc", order);
    }

}
