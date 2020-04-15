package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;

import java.math.BigDecimal;

@Entity
public class Item {
    @Reference
    private Product product; //not lazy
    private int quantity;
    private BigDecimal price;
    private BigDecimal discount = BigDecimal.ZERO;

    public Item(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
        updateItem();
    }

    private void updateItem() {

    }

    public BigDecimal getPrice() {
        return product.getPrice(quantity);
    }

    private BigDecimal queryPrice() {
        return null;
    }
}
