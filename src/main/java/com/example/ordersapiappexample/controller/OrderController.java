package com.example.ordersapiappexample.controller;
import com.example.ordersapiappexample.modal.dao.order.IdaoOrder;
import com.example.ordersapiappexample.modal.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order") //префикс
public class OrderController {
    @Autowired
    private IdaoOrder obj;
    @GetMapping("/all")
    public List<Order> all(){
        return  obj.findAll();
    }

    @GetMapping("/{id:\\d+}")
    public Optional<Order> findById(@PathVariable Integer id){
        return obj.findById(id);
    }

    @PostMapping("/new")
    public Order save(@RequestParam(defaultValue = "") String descript,
                      @RequestParam(required = false) Integer idClient){
        return  obj.update(new Order(-1,descript,null),idClient);
    }
    @PostMapping("/{id:\\d+}")
    public Order update(@PathVariable  Integer id,
                        @RequestParam(defaultValue = "") String descript,
                       @RequestParam(defaultValue = "-1") Integer idClient ){
        return  obj.update(new Order(id,descript,null),idClient);
    }

    @DeleteMapping("/{id:\\d+}")
    public Order delete(@PathVariable  Integer id){
        return obj.delete(id);
    }

    @GetMapping("/ping")
    public String ping(){
        return "Order pong";
    }
}
