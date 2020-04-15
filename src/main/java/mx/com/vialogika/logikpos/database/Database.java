package mx.com.vialogika.logikpos.database;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import javax.xml.crypto.Data;

public class Database {

    private final Morphia morphia = new Morphia();
    private final Datastore datastore;

    private Database(){
        morphia.mapPackage("mx.com.vialogika.logikpos.core");
        datastore = morphia.createDatastore(new MongoClient(),"logikpos");
        datastore.ensureIndexes();
    }

    private static class DatabaseHolder{
        private static final Database database = new Database();
    }

    public static Database getInstance(){
        return DatabaseHolder.database;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
