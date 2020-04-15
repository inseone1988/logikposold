package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;

import java.math.BigDecimal;

@Entity
public class Price {
    private boolean base;
    private BigDecimal value = BigDecimal.ZERO;
    private Condition condition;

    public BigDecimal getValue() {
        return value;
    }

    public boolean match(int quantity) {
        return false;
    }
}
