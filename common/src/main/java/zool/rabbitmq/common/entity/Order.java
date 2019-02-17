package zool.rabbitmq.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author：zoolye
 * @date：2019-02-13：21:27
 * @description：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -1608683100654259011L;

    private String id;

    private String messageId;

    private String name;

    private BigDecimal money;

    private Integer numbers;
}
