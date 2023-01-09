package com.example.ordersapiappexample.controller;
import com.example.ordersapiappexample.modal.dao.Requirement;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @GetMapping("/ping")
    public String toString(){
        return  "pong";
    }
    @GetMapping("/receipt/{id:\\d+}")
    public String receipt(@PathVariable Integer id){
        return Requirement.receipt(id);}

    @GetMapping("/receipt")
    public String receiptq(@RequestParam(defaultValue = "-1") Integer id){
        return Requirement.receipt(id);}
}//class MainController
