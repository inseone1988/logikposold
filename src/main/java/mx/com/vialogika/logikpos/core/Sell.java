package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mx.com.vialogika.logikpos.database.Database;
import mx.com.vialogika.logikpos.util.Currency;
import mx.com.vialogika.logikpos.util.Dialogs;
import mx.com.vialogika.logikpos.util.DiscountDialog;
import org.bson.types.ObjectId;

import javax.swing.event.ChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("orders")
public class Sell{
    private final Date create;
    private final List<Item> itemList = new ArrayList<>();
    private final List<Payment> paymentList = new ArrayList<>();
    private final StringProperty grandTotalProperty = new SimpleStringProperty();
    private boolean editable;
    @Id
    private ObjectId id;
    private Customer customer;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;
    private BigDecimal discounts = BigDecimal.ZERO;
    private BigDecimal paid = BigDecimal.ZERO;
    private BigDecimal grandTotal = BigDecimal.ZERO;
    private boolean applyTax;
    private SellStatus status;

    public Sell() {
        this.create = new Date();
        this.editable = true;
        setGrandTotal(grandTotal);
        //save();
    }

    public Sell(SellStatus status) {
        this();
        this.status = status;
    }

    private void update(){
        subTotal = getSubTotal();
        tax = getTax();
        discounts = getDiscounts();
        paid = getPayments();
        setGrandTotal(subTotal.add(grandTotal.add(tax).subtract(discounts).subtract(paid)));
    }

    public void setApplyTax(boolean applyTax) {
        this.applyTax = applyTax;
        update();
    }

    public void applyDiscount(){
        Dialogs.discountDialog(new DiscountDialog.DiscountApplyCallback() {
            @Override
            public void onDiscount(String discount, DiscountType type) {

            }
        });
    }

    private BigDecimal getPayments() {
        return paymentList.size() > 0 ? BigDecimal.valueOf(paymentList.stream().mapToDouble(param->param.getValue().doubleValue()).sum()) : BigDecimal.ZERO;
    }

    private BigDecimal getDiscounts() {
        return BigDecimal.valueOf(itemList.stream().filter(Item::hasDiscount).mapToDouble(item->item.getDiscount().doubleValue()).sum());
    }

    private BigDecimal getTax() {
        return applyTax ? subTotal.multiply(BigDecimal.valueOf(16)).divide(BigDecimal.valueOf(100)): BigDecimal.ZERO;
    }

    private BigDecimal getSubTotal() {
        return BigDecimal.valueOf(itemList.stream().mapToDouble(item->item.getTotal().doubleValue()).sum());
    }

    public void save() {
        Database.getInstance().getDatastore().save(this);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setGrandTotal(BigDecimal total){
        this.grandTotal = total;
        this.grandTotalProperty.set(Currency.format(total));
    }

    public String getGrandTotalProperty() {
        return grandTotalProperty.get();
    }

    public StringProperty grandTotalProperty() {
        return grandTotalProperty;
    }

    public void setGrandTotalProperty(String grandTotalProperty) {
        this.grandTotalProperty.set(grandTotalProperty);
    }

    public enum SellStatus {
        OPEN,
        PENDING,
        CLOSED,
        BUDGET
    }
}
