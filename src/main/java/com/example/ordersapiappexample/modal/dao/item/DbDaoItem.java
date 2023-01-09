package com.example.ordersapiappexample.modal.dao.item;
import com.example.ordersapiappexample.modal.entity.Item;
import com.example.ordersapiappexample.modal.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//имплементация Dao для сущнисти
@Service
public class DbDaoItem implements IDaoItem{
    private final ItemRepository repository;
    public static ItemRepository xxx;
    public DbDaoItem(ItemRepository repository) {
        this.repository = repository;
        xxx = repository;
    }
    @Override
    public List<Item> findAll() {
        return (List<Item>) repository.findAll();
    }
    @Override
    public Optional<Item> findById(Integer id) {
        if (id<0) return Optional.empty();
        return repository.findById(id);
    }
    @Override
    public Item update(Item elm) {
        if (repository.findById(elm.getId()).isPresent()){
           if (elm.getItemName().isBlank())
               elm.setItemName(repository.findById(elm.getId()).get().getItemName());
           if (elm.getItemArticle()==0)
               elm.setItemArticle(repository.findById(elm.getId()).get().getItemArticle());
           if (elm.getPrice()==0)
               elm.setPrice(repository.findById(elm.getId()).get().getPrice());
        }
        return repository.save(elm);
    }
    @Override
    public Item delete(Integer id) {
        Optional<Item> elm = repository.findById(id);
        if (elm.isEmpty()) return null;
        if (elm.get().getSize() >0 ) return null; //запрет на удаление
        repository.deleteById(id);
        return elm.get();
    }
}//class DbDaoItem
