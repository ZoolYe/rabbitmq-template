package zool.rabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zool.rabbitmq.common.entity.Order;
import zool.rabbitmq.producer.push.SendMessage;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

    @Autowired
    SendMessage sendMessage;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSendOrder(){

        Order order = Order.builder()
                .id("ZOOL:www.zoolye.com")
                .messageId("1")
                .money(BigDecimal.valueOf(1300))
                .name("工资")
                .numbers(13).build();
        sendMessage.sendOrder(order);
    }

}

