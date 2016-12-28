package restsdemo.example2;

import restsdemo.base.LongId;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.AccessType.*;

/**
 * @author Cepro, 2016-12-27
 */
@Entity
@Access(PROPERTY)
@Table(name = "items")
public class LineItem extends LongId {
    
    private Order order;
    
    private String title;
    
    private Integer quantity;
    
    private BigDecimal price;
    
    public LineItem() {
    }
    
    public LineItem(Order order, String title, Integer quantity, BigDecimal price) {
        this.order = order;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }
    
    @ManyToOne
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
