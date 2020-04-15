package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;

import java.util.Date;

@Entity("price_conditions")
public class Condition {

    private int from;
    private int to;
    private Date expiry;
    private ConditionType type;

    public Condition(ConditionType type, Object args){
        this.type = type;
        process(args);
    }

    private void process(Object args) {
        switch (type){
            case WHOLESALE:
                from = ((int[])args)[0];
                to = ((int[])args)[1];
                break;
        }
    }

    public boolean match(ConditionType conditionType,Object arg){
        switch(conditionType){
            case WHOLESALE:
                int quantity = (int)arg;
                return quantity >= from && quantity <= to;
            default:
                return false;
        }
    }

    public enum ConditionType{
        BASE,
        WHOLESALE
    }
}
