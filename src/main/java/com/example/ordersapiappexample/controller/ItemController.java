package com.example.ordersapiappexample.controller;
import com.example.ordersapiappexample.modal.dao.item.IDaoItem;
import com.example.ordersapiappexample.modal.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/item") //префикс
public class ItemController {

    @Autowired
    private IDaoItem obj;
    //получение всех объектов
    @GetMapping("/all")
    public List<Item> all(){
        return  obj.findAll();
    }

    @GetMapping("/{id:\\d+}")
    public Optional<Item> findById(@PathVariable Integer id){
        return obj.findById(id);
    }
    @PostMapping ("/new")
    public Item update(
                       @RequestParam(required = false) String itemName,
                       @RequestParam(defaultValue = "0") Long itemArticle,
                       @RequestParam(defaultValue = "1") Float price ){
        return  obj.update(new Item(-1, itemName, itemArticle, price));
    }
    @PostMapping("/{id:\\d+}")
    public Item update(@PathVariable  Integer id,
                       @RequestParam(defaultValue = "") String itemName,
                       @RequestParam(defaultValue = "0") Long itemArticle,
                       @RequestParam(defaultValue = "0") Float price ){
        return  obj.update(new Item(id, itemName, itemArticle, price));
    }
    @DeleteMapping("/{id:\\d+}")
    public Item delete(@PathVariable Integer id){
        return obj.delete(id);
    }

    @GetMapping("/ping")
    public String ping(){
       return "item pong";
    }
}
