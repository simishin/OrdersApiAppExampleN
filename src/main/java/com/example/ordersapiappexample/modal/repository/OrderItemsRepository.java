package com.example.ordersapiappexample.modal.repository;

import com.example.ordersapiappexample.modal.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer> {
}
