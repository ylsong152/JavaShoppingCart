import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShoppingCart {
    public static void main(String[] args) {
        ArrayList<String> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

    // Paths & Files
    // in src directory: (java ShoppingCart nameOfDB) to start program
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        Path userFilePath = null;

        String dbName = "";

        if (args.length == 0) {
            dbName = "db";
        } else {
            dbName = args[0];
        }

        Path dbFolder = currentDirectory.resolve(dbName);
        
        if (!Files.exists(dbFolder)) {
            try {
                Files.createDirectories(dbFolder);
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("Directory created for " + dbFolder);
         } 
         System.out.println("Using " + dbName);

        String input = scanner.next();
        

        while (!"stop".equals(input)){
            // list
            if(input.equals("list")) {
                if (cart.isEmpty()) {
                    System.out.println("The cart is empty!");
                }
                
                for(int i = 0; i < cart.size(); i++) {
                    System.out.println((i+1) + ". " + cart.get(i));
                }
                input = scanner.next();
            } 

            // add
            else if (input.equals("add")) {
                String addedItems = scanner.nextLine().trim();
                String[] itemsArray = addedItems.split(", ");
                for (int i = 0; i < itemsArray.length; i++) {
                    itemsArray[i] = itemsArray[i].toLowerCase();
                    if(cart.contains(itemsArray[i])) {
                        System.out.println("You have " + itemsArray[i] + " in your cart!");
                    } else{
                        cart.add(itemsArray[i]);
                        System.out.println(itemsArray[i] + " is added to the cart");
                    }
                }
                input = scanner.next();
            }

            // delete
            else if (input.equals("delete")) {
                if (cart.isEmpty()) {
                    System.out.println("The cart is empty!");
                } else {
                    int index = scanner.nextInt();
                    if (index < 1 || index > cart.size()) {
                        System.out.println("Incorrect item index!");
                    } else {
                        String deletedItem = cart.remove(index - 1);
                        System.out.println(deletedItem + " has been deleted");
                    }
                }
                input = scanner.next();
            } 
            
            // login
            else if (input.equals("login")) {
                String user = scanner.next();
                userFilePath = dbFolder.resolve(user + ".txt");
                
                if (!Files.exists(userFilePath)) {
                    try {
                        Files.createFile(userFilePath);
                        System.out.println("User not found. Creating cart for user " + user);
                        System.out.println("Successfully created cart, use command 'save' to store your cart.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try (BufferedReader br = new BufferedReader(new FileReader(userFilePath.toString()))) {
                        String line;
                        cart.clear();
                        while ((line = br.readLine()) != null) {
                            cart.add(line);
                        }
                        if (cart.isEmpty()) {
                            System.out.println(user + ", your cart is empty");
                        } else {
                            System.out.println(user + ", your cart contains the following items:");
                            for (int i = 0; i < cart.size(); i++) {
                                System.out.println((i + 1) + ". " + cart.get(i));
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                input = scanner.next();
            }

            // save
            else if (input.equals("save")) {
                if (userFilePath != null) {
                    try (FileWriter fw = new FileWriter(userFilePath.toString())) {
                        if (cart.size() > 0) {
                            for (String item:cart) {
                                fw.write(item + "\n");
                            }
                            System.out.println("Your cart has been saved.");
                        } else {
                            System.out.println("The shopping cart is empty.");
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                input = scanner.next();
            }

            else if (input.equals("users")) {
                File dbFolderFile = new File(dbFolder.toString());
                String[] users = dbFolderFile.list();
                System.out.println("The following users are registered");
                for (int i = 0; i < users.length; i++) {
                    String user = users[i].substring(0, users[i].length() - 4);
                    System.out.println((i + 1) + ". " + user);
                }
                input = scanner.next();
            }

            else {
                System.out.println("Please use command 'list', 'add', or 'delete', 'login', 'save', or 'users'");
                input = scanner.next();
            }
        }
        scanner.close();
    }
}