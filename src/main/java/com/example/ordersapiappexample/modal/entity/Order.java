package com.example.ordersapiappexample.modal.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_t")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String descript;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private  Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItem;

    public Order() {this(-1,"undefine", null);    }

    public Order(Integer id, String descript, Client client) {
        this.id = id;
        this.descript = descript;
        this.client = client;
        this.orderItem = new HashSet<OrderItems>();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescript() {
        return descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Integer getSize(){return orderItem.size();}
    public int[] getOrderItem() {
        if (orderItem.isEmpty()) return null;
        int[] x = new int[orderItem.size()];
        int i=0;
        for (OrderItems y:orderItem )  x[i++] = y.getId();
        return x;
    }
    public Set<OrderItems> orderItem() { return orderItem; }
    public void setOrderItem(Set<OrderItems> orderItem) {
        this.orderItem = orderItem;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", descript='" + descript + '\'' +
                ", client=" + client +
                '}';
    }
}
