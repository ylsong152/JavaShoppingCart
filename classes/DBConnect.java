package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DBConnect {
    public static void main(String[] args) {
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        String dbName = "";

        if (args.length == 0) {
            dbName = "db";
        } else {
            dbName = args[0];
        }

        Path dbFolder = currentDirectory.resolve(dbName);
        System.out.println(dbFolder);
        
        if (!Files.exists(dbFolder)) {
            try {
                Files.createDirectories(dbFolder);
                System.out.println("Directory created for " + dbFolder);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}