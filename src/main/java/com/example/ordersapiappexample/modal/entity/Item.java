package com.example.ordersapiappexample.modal.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_t")
public class Item {//продукт
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String itemName;
    @Column
    private Long    itemArticle;
    @Column
    private Float   price;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItem;

    public Item() {
        this(-1,"undefine",-1L, 0F); }

    public Item(Integer id, String itemName, Long itemArticle, Float price) {
        this.id = id;
        this.itemName = itemName;
        this.itemArticle = itemArticle;
        this.price = price;
        this.orderItem = new HashSet<OrderItems>();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getItemName() {return itemName;}
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Long getItemArticle() {return itemArticle;}
    public void setItemArticle(Long itemArticle) {
        this.itemArticle = itemArticle;
    }
    public Set<OrderItems> OrderItem() {return orderItem;}

    public int[] getOrderItem() {
        if (orderItem.isEmpty()) return null;
        int[] x = new int[orderItem.size()];
        int i=0;
        for (OrderItems y:orderItem )  x[i++] = y.getId();
        return x;
    }
    public void setOrderItem(Set<OrderItems> orderItem) {
        this.orderItem = orderItem;
    }
    public Integer getSize(){return orderItem.size();}
    public Float getPrice() {return price;}
    public void setPrice(Float price) {this.price = price;}
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemArticle=" + itemArticle +
                '}';
    }
}//class Item
