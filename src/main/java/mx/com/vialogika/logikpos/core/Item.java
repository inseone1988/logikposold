package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import mx.com.vialogika.logikpos.util.Currency;

import java.math.BigDecimal;

@Entity
public class Item implements ChangeListener{
    @Reference
    private Product product; //not lazy
    private int quantity;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private DiscountType discountType;
    private BigDecimal total = BigDecimal.ZERO;

    private StringProperty productDescriptionProperty = new SimpleStringProperty();
    private IntegerProperty quantityProperty = new SimpleIntegerProperty();
    private StringProperty priceProperty = new SimpleStringProperty();
    private StringProperty discountProperty = new SimpleStringProperty();
    private StringProperty totalProperty = new SimpleStringProperty();


    public Item(Product product, int quantity) {
        this.product = product;
        productDescriptionProperty.set(product.getDescription());
        quantityProperty.addListener(this);
        priceProperty.addListener(this);
        discountProperty.addListener(this);
        setQuantity(quantity);
    }

    private void updateItem() {
        setTotal(price.multiply(BigDecimal.valueOf(quantity)).subtract(discount));
    }

    private void setTotal(BigDecimal total) {
        this.total = total;
        totalProperty.set(Currency.format(total));
    }

    private void applyDiscount(BigDecimal value, DiscountType type) {
        switch (type) {
            case FIXED:
                setDiscount(value);
                break;
            case PERCENT:
                setDiscount(total.multiply(value).divide(BigDecimal.valueOf(100)));
                break;
        }
    }

    private void setDiscount(BigDecimal value) {
        this.discount = value;
        discountProperty.set(Currency.format(value));
    }

    public BigDecimal getPrice() {
        price = product.getPrice(quantity);
        return this.price;
    }

    private BigDecimal queryPrice() {
        return null;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setQuantity(int quantity) {
        this.quantityProperty.set(quantity);
        this.quantity = quantity;
    }

    public void addQuantity(int qty){
        setQuantity(this.quantity+=qty);
    }

    public void subQuantity(int qty){
        setQuantity(this.quantity-=qty);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.priceProperty.set(Currency.format(price));
    }

    public String getProductDescriptionProperty() {
        return productDescriptionProperty.get();
    }

    public StringProperty productDescriptionProperty() {
        return productDescriptionProperty;
    }

    public int getQuantityProperty() {
        return quantityProperty.get();
    }

    public IntegerProperty quantityProperty() {
        return quantityProperty;
    }

    public String getPriceProperty() {
        return priceProperty.get();
    }

    public StringProperty priceProperty() {
        return priceProperty;
    }

    public String getDiscountProperty() {
        return discountProperty.get();
    }

    public StringProperty discountProperty() {
        return discountProperty;
    }

    public String getTotalProperty() {
        return totalProperty.get();
    }

    public StringProperty totalProperty() {
        return totalProperty;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        updateItem();
    }

    public boolean hasDiscount() {
        return discount.compareTo(BigDecimal.ZERO) > 0;
    }
}
