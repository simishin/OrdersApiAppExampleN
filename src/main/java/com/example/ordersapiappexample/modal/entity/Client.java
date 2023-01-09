package com.example.ordersapiappexample.modal.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client_t")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    public Set<Order> orders() {
        return orders;
    }
    public int getSize(){
        return orders.size();
    }
    public int[] getOrders() {
        if (orders.isEmpty()) return null;
        int[] x = new int[orders.size()];
        int i=0;
        for (Order y:orders )  x[i++] = y.getId();
        return x;
    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    public Client() { this(-1,"undefine");  }
    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new HashSet<Order>();
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
