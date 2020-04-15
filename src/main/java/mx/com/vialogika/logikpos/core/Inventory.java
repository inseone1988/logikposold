package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import org.bson.types.ObjectId;

import java.util.List;

@Entity("warehouse")
public class Inventory {
    private ObjectId id;
    private int existences;
    private List<Movement> inventoryMovements;
}
