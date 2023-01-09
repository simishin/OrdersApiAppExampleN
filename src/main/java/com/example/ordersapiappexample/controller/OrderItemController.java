package com.example.ordersapiappexample.controller;
import com.example.ordersapiappexample.modal.dao.orderitem.IdaoOrderItem;
import com.example.ordersapiappexample.modal.entity.OrderItems;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orderItems") //префикс
public class OrderItemController {
    private final IdaoOrderItem obj;

    public OrderItemController(IdaoOrderItem obj) {
        this.obj = obj;}

    @GetMapping("/all")
    public List<OrderItems> all(){ return  obj.findAll();     }
    @GetMapping("/{id:\\d+}")
    public Optional<OrderItems> findById(@PathVariable  Integer id){
        return obj.findById(id);
    }
    @PostMapping("/new")
    public OrderItems save(@RequestParam(defaultValue = "1") Integer quantity,
                           @RequestParam(required = false) Integer itemId,
                           @RequestParam(required = false) Integer orderId){
        return obj.update(new OrderItems(-1,quantity,null,null),orderId, itemId);
    }
    @PostMapping("/{id:\\d+}")
    public OrderItems update(@PathVariable  Integer id,
                             @RequestParam(defaultValue = "0") Integer quantity,
                             @RequestParam(defaultValue = "-1") Integer itemId,
                             @RequestParam(defaultValue = "-1") Integer orderId){
        return  obj.update(new OrderItems(id,quantity,null, null),orderId, itemId);
    }
    @DeleteMapping("/{id:\\d+}")
    public OrderItems delete(@PathVariable  Integer id){
        return obj.delete(id);
    }

    @DeleteMapping
    public OrderItems deleteQ(@RequestParam(required = false)  Integer id){
        return obj.delete(id);    }

    @GetMapping("/ping")
    public String ping(){
        return "OrderItems pong";
    }
}
