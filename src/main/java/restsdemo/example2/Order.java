package restsdemo.example2;

import restsdemo.base.LongId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.AccessType.PROPERTY;
import static restsdemo.example2.Order.Status.PAYMENT_EXPECTED;

/**
 * @author Cepro, 2016-12-27
 */
@Entity
@Access(PROPERTY)
@Table(name = "orders")
public class Order extends LongId {
    
    private LocalDateTime registered = LocalDateTime.now();
    
    private Status status = PAYMENT_EXPECTED;
    
    private List<LineItem> items = new ArrayList<>();
    
    public Order() {
    }
    
    public Order(List<LineItem> items) {
        setItems(items);
    }
    
    public LocalDateTime getRegistered() {
        return registered;
    }
    
    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<LineItem> getItems() {
        return items;
    }
    
    public void setItems(List<LineItem> items) {
        items.forEach(item -> item.setOrder(this));
        this.items = items;
    }
    
    public static enum Status {
        PAYMENT_EXPECTED, PAID, PREPARING, READY, TAKEN;
    }
}
