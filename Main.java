import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.*;
import org.bson.*;
import javax.swing.text.Document;

import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) {
        int ch;
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            Scanner s = new Scanner(System.in);
            MongoDatabase database = mongoClient.getDatabase("Rental");
            System.out.println("Connected to database successfully.");
            MongoCollection<Document> coll = database.getCollection("Data");
            System.out.println("collection imported");

            do {
                System.out.println("Hello!,Welcome to MongoDB...");
                System.out.println("1.Add an Entry \n 2.View the contents\n 3.Update an Entry\n 4.Delete an Entry\n 5.Exit\n");
                System.out.println("Enter ur task");
                ch = s.nextInt();
                switch (ch) {
                    case 1:
                        System.out.println("enter name,license plate and contact number:");
                        String n = s.next();
                        String l = s.next();
                        String p = s.next();
                        Document doc = new Document()
                                .append("name", n)
                                .append("License plate", l)
                                .append("Phone number", p);
                        coll.insertOne(doc);
                        break;
                    case 2:
                        System.out.println("The following entries are:");
                        FindIterable<Document> doc1 = coll.find();
                        for (Document doco : doc1)
                            System.out.println(doco.toJson());
                        break;
                    case 3:
                        System.out.println("Enter the client's name:");
                        String na = s.next();
                        try {
                            Document query = new Document("name", na);
                            System.out.println("enter the stuff u wanna change");
                            System.out.println("1.Client's name\n2.License number\n3.Contact number\n");
                            int ch1 = s.nextInt();
                            switch (ch1) {
                                case 1:
                                    System.out.println("enter the client's name to be updated");
                                    String n2 = s.next();
                                    Document update = new Document("$set",new Document("name", n2));
                                    UpdateResult res1 = coll.updateOne(query, update);
                                    break;
                                case 2:
                                    System.out.println("enter new License number:");
                                    String n3 = s.next();
                                    Document update1 = new Document("$set",new Document("License plate", n3));
                                    UpdateResult res2 = coll.updateOne(query, update1);
                                    break;
                                case 3:
                                    System.out.println("enter new Contact number:");
                                    long ph1 = s.nextLong();
                                    Document update2 = new Document("$set",new Document("Phone number", ph1));
                                    UpdateResult res3 = coll.updateOne(query, update2);
                                    break;
                                default:
                                    System.out.println("Choose any one of the above!!");
                                    break;
                            }
                        } caatch (Exception e) {
                            System.out.println("Client not found");
                        }
                        break;
                    case 4:
                        System.out.println("Enter the name u wanna delete");
                        String n2 = s.next();
                        Document query = new Document("name", n2);
                        DeleteResult res3 = coll.deleteOne(query);
                        break;
                    case 5:
                        System.out.println("Glad to assist you!!");
                        exit(0);
                        break;
                    default:
                        System.out.println("Choose a valid choice!!");
                }
            }while (ch!=6);
                } catch(Exception e){
                    System.err.println("Error connecting to MongoDB: " + e.getMessage());}
            }
        }