package com.example.ordersapiappexample.modal.dao;
import com.example.ordersapiappexample.modal.dao.order.DbDaoOrder;
import com.example.ordersapiappexample.modal.dao.orderitem.DbDaoOrderItem;
import com.example.ordersapiappexample.modal.entity.Order;
import com.example.ordersapiappexample.modal.entity.OrderItems;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Requirement {//Требования
    public static String receipt(Integer id){
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        if (id < 0){//Вывод списка заказов {имя-номер}
            record OrdeM(int id, String descript){}
            List<OrdeM> y = new ArrayList<>();
            for (Order x: DbDaoOrder.xxx.findAll() )
                y.add(new OrdeM(x.getId(), x.getDescript()));
            try {
                json = objectMapper.writeValueAsString(y);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return json;
        }
        if ( DbDaoOrder.isEmpt(id)) { //НЕТ такого
            return "НЕТ такого заказа "+id;
        }
        record Ware(String itemName, Long itemArticle, Integer quantity, Float price, Float total){}

        float amount=0F;
        float locsum;
        List<Ware> elm = new ArrayList<>();
        for (OrderItems x: DbDaoOrderItem.xxx.findAll() ){
            if (x.getOrder().getId() != id) continue;
            locsum = x.getItem().getPrice() * x.getQuantity();
            amount += locsum;
            elm.add(new Ware(x.getItem().getItemName(), x.getItem().getItemArticle(),
                    x.getQuantity(), x.getItem().getPrice(), locsum ));
        }
        record Check(List<Ware> elm, Float amount){}
        try {
            json = objectMapper.writeValueAsString(new Check(elm,amount));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}//class Requirement
