package restsdemo.example2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static restsdemo.example2.Order.Status.PAYMENT_EXPECTED;

/**
 * @author Cepro, 2016-12-27
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Access(PROPERTY)
@Table(name = "orders")
public class Order extends LongId {
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registered = LocalDateTime.now();
    
    private Status status = PAYMENT_EXPECTED;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> items = new ArrayList<>();
    
    public Order(List<LineItem> items) {
        setItems(items);
    }
    
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
