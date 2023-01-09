package com.example.ordersapiappexample.modal.dao.order;
import com.example.ordersapiappexample.modal.dao.client.DbDaoClient;
import com.example.ordersapiappexample.modal.entity.Client;
import com.example.ordersapiappexample.modal.entity.Order;
import com.example.ordersapiappexample.modal.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbDaoOrder implements IdaoOrder {
    private final OrderRepository repository;
    public static OrderRepository xxx;
    public DbDaoOrder(OrderRepository repository) {
        this.repository = repository;
        xxx = repository;
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }
    public static boolean isEmpt(Integer id){
        return xxx.findById(id).isEmpty();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return repository.findById(id);
    }
    @Override
    public Order update(Order item, Integer idClient) {
        System.out.println("update "+item+" **** "+idClient);
        if (repository.findById(item.getId()).isPresent()){ //есть такой элемент
            if (item.getDescript().isBlank()) // пустое знчение - переписываю
                item.setDescript(repository.findById(item.getId()).get().getDescript());
            boolean b = true;
            if (idClient > -1){
                Optional<Client> cx = DbDaoClient.xxx.findById(idClient);
                if (cx.isPresent()) {
                    item.setClient(cx.get());
                    b=false;
                }
            }
            if (b) item.setClient(repository.findById(item.getId()).get().getClient());
            return repository.save(item);
        } else {
            Optional<Client> cx = DbDaoClient.xxx.findById(idClient);
            if (cx.isPresent()) { //есть такой
                item.setClient(cx.get());
                return repository.save(item);
            }
        }
        return null;
    } //update
    @Override
    public Order delete(Integer id) {
        Optional<Order> elm = repository.findById(id);
        if (elm.isEmpty()) return null;
        if (elm.get().getSize() >0 ) return null; //запрет на удаление
        repository.deleteById(id);
        return elm.get();
    }
}//class DbDaoOrder
