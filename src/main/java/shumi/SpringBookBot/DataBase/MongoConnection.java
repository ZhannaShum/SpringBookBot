package shumi.SpringBookBot.DataBase;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import shumi.SpringBookBot.DataBase.Entity.Book;

import java.util.ArrayList;


public class MongoConnection {
    public static MongoDatabase mongo = new MongoClient(new MongoClientURI
            (System.getenv("MONGO_URI")))
            .getDatabase("data")
            .withCodecRegistry(CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.
                            builder().automatic(true).build())
            ));
    public MongoConnection() {
    }

}
