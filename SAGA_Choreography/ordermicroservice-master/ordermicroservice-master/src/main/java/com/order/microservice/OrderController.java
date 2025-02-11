package com.order.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
//	https://fullstackdeveloper.guru/2023/05/11/how-to-implement-saga-design-pattern-in-spring-boot/

	@Autowired
	private OrderRepository repository;

	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@GetMapping("orderTemplate")
	public CustomerOrder orderTemplate() {
		return new CustomerOrder("tem1", 1, 23.23, "CASH", 123, "BVG");
	}

	@PostMapping("/orders")
	public void createOrder(@RequestBody CustomerOrder customerOrder) {
		System.out.println("called --> 01-order-service --> createOrder() --> /orders");
		OrderEntity order = new OrderEntity();
		try {
			// save order in database

			order.setAmount(customerOrder.getAmount());
			order.setItem(customerOrder.getItem());
			order.setQuantity(customerOrder.getQuantity());
			order.setStatus("CREATED");
			order = this.repository.save(order);

			customerOrder.setOrderId(order.getId());

			// publish order created event for payment microservice to consume.

			OrderEvent event = new OrderEvent();
			event.setOrder(customerOrder);
			event.setType("ORDER_CREATED");
			this.kafkaTemplate.send("new-orders", event);
		} catch (Exception e) {
			System.out.println("called --> 01-order-service --> catch() block --> /orders");
			order.setStatus("FAILED");
			this.repository.save(order);

		}

	}

}
