package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment {

    private final PaymentType paymentType;
    private final String observations;
    private Date paymentDate;
    private BigDecimal value;

    public Payment(BigDecimal payment, PaymentType type,String observations) {
        this.paymentDate = new Date();
        this.value = payment;
        this.paymentType = type;
        this.observations = observations;
    }

    public BigDecimal getPaidValue() {
        return this.value;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getObservations() {
        return observations;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getValue() {
        return value;
    }
}
