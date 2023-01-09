package com.example.ordersapiappexample.modal.dao.orderitem;
import com.example.ordersapiappexample.modal.dao.item.DbDaoItem;
import com.example.ordersapiappexample.modal.dao.order.DbDaoOrder;
import com.example.ordersapiappexample.modal.entity.OrderItems;
import com.example.ordersapiappexample.modal.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DbDaoOrderItem implements IdaoOrderItem {
    private final OrderItemsRepository repository;
    public static OrderItemsRepository xxx;
    public DbDaoOrderItem(OrderItemsRepository repository) {
        this.repository = repository;
        xxx = repository;
    }
    @Override
    public List<OrderItems> findAll() {
        return (List<OrderItems>) repository.findAll();
    }
    @Override
    public Optional<OrderItems> findById(Integer id) {
        return repository.findById(id);
    }
    @Override
    public OrderItems update(OrderItems elm, Integer orderId, Integer itemId) {
        System.out.println("************* OrderItems update "+elm+"\t"+orderId+"\t"+itemId);
        if (repository.findById(elm.getId()).isPresent()) {//есть такой элемент
            System.out.println("isPresent");
            if (elm.getQuantity() < 1 )
                elm.setQuantity(repository.findById(elm.getId()).get().getQuantity());
            if (orderId > -1 && DbDaoOrder.xxx.findById(orderId).isPresent())
                    elm.setOrder(DbDaoOrder.xxx.findById(orderId).get());
            else elm.setOrder(repository.findById(elm.getId()).get().getOrder());
            if (itemId > -1 && DbDaoItem.xxx.findById(itemId).isPresent())
                    elm.setItem(DbDaoItem.xxx.findById(itemId).get());
            else elm.setItem(repository.findById(elm.getId()).get().getItem());
            return repository.save(elm);

        } else if (DbDaoOrder.xxx.findById(orderId).isPresent()
                    && DbDaoItem.xxx.findById(itemId).isPresent()) {
            elm.setOrder(DbDaoOrder.xxx.findById(orderId).get());
            elm.setItem(DbDaoItem.xxx.findById(itemId).get());
            return repository.save(elm);}
        return null;
    }
    @Override
    public OrderItems delete(Integer id) {
        Optional<OrderItems> elm =  repository.findById(id);
        if (elm.isEmpty()) return null;
        repository.deleteById(id);
        return elm.get();
    }
}
