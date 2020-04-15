package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mx.com.vialogika.logikpos.database.Database;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("orders")
public class Sell {
    @Id
    private ObjectId id;
    private Date create;
    private Customer customer;
    private boolean editable;
    private List<Item> itemList = new ArrayList<>();
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;
    private BigDecimal discounts = BigDecimal.ZERO;
    private List<Payment> paymentList = new ArrayList<>();
    private BigDecimal grandTotal = BigDecimal.ZERO;

    private StringProperty grandTotalProperty = new SimpleStringProperty();
    private boolean applyTax;

    public Sell(){
        this.create = new Date();
        this.editable = true;
        save();
    }

    public void addItem(){
        updateSell();
    }

    private void updateSell() {
        setGrandTotal(BigDecimal.ZERO);
        subTotal = grandTotal.add(BigDecimal.valueOf(itemList.stream().mapToDouble(item -> item.getPrice().doubleValue()).sum()));
        tax = getTax();

    }

    private BigDecimal getTax() {
        if (applyTax) return subTotal.divide(BigDecimal.valueOf(16)).multiply(BigDecimal.valueOf(100d));
        return BigDecimal.ZERO;
    }

    public void save() {
        Database.getInstance().getDatastore().save(this);
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
