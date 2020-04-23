package mx.com.vialogika.logikpos.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Currency {

    public static String format(double value){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }

    public static String format(BigDecimal value){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }
}
