package com.example.ordersapiappexample.modal.repository;

import com.example.ordersapiappexample.modal.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository  extends CrudRepository<Order, Integer> {
}
