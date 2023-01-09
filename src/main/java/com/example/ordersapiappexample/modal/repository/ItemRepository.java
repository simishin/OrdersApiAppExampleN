package com.example.ordersapiappexample.modal.repository;

import com.example.ordersapiappexample.modal.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
