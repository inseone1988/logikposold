package mx.com.vialogika.logikpos.core;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.Date;

@Entity("providers")
public class Provider {
    @Id
    private ObjectId id;
    private Date create;
    private String providerName;
    private String commercialName;
    private String alias;
    private String rfc;
    private String address;
}
