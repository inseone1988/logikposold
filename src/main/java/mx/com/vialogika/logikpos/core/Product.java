package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity("products")
public class Product {
    @Id
    private ObjectId id;
    private Date created;
    private String code;
    private String upc;
    private String description;
    @Embedded
    private List<Price> priceList;
    @Reference
    private Inventory inventory;
    @Reference
    private Provider provider;

    public Product() {
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public BigDecimal getPrice(int quantity) {
        return priceList.size() > 1 ? priceList.stream().filter(price -> price.match(quantity)).findFirst().get().getValue(): priceList.get(0).getValue();
    }
}
