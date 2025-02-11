package com.inventory.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReverseInventory {
//	https://fullstackdeveloper.guru/2023/05/11/how-to-implement-saga-design-pattern-in-spring-boot/

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-inventory", groupId = "inventory-group")
    public void reverseInventory(String event) {

        try {
        	System.out.println(this.getClass().getName());
        	System.out.println("called --> 03-inventory-service --> reverseInventory() method -->'reversed-inventory' @KafkaListener");
            

            InventoryEvent inventoryEvent = new ObjectMapper().readValue(event, InventoryEvent.class);

            Iterable<Inventory> inv = this.repository.findByItem(inventoryEvent.getOrder().getItem());

            inv.forEach(i -> {

                i.setQuantity(i.getQuantity() + inventoryEvent.getOrder().getQuantity());

                this.repository.save(i);
            });

            System.out.println(this.getClass().getName());
        	System.out.println("called --> 03-inventory-service --> reverseInventory() method -->'reversed-payments' @KafkaListener");
           
            // reverse previous task
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(inventoryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            this.kafkaTemplate.send("reversed-payments", paymentEvent);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
