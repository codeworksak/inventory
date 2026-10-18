package com.codeworks.inventory.rabbitmq;

import com.codeworks.inventory.models.CartItem;
import com.codeworks.inventory.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class InventoryRabbitListener {
    @Autowired
    private InventoryService inventoryService;
    @RabbitListener(queues = "${inventory.rabbitmq.queue-name}")
    @Transactional
    public void  decuctStock(List<CartItem> cartItems)
    {
        for(CartItem ci: cartItems)
        {
            try {
                inventoryService.deductStock(UUID.fromString(ci.getProductId()), ci.getQuantity());
            }catch (Exception e){
//                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
