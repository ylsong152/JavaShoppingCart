import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        ArrayList<String> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();
        
        // list
        while (!"stop".equals(input)){
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
                int index = Integer.parseInt(scanner.next());
                System.out.println(cart.get(index) + " has been deleted");
                cart.remove(index);
                input = scanner.next();
            }
            else {
                System.out.println("Please use command 'list', 'add', or 'delete'");
                input = scanner.next();
            }
        }
        scanner.close();
    }
}

// list --> print out all items in the list (cart empty = empty msg)
// add --> add apple = add apple to list, add apple, orange = added apple to list, added orange to list
// delete --> delete 2 = delete 2nd item from the list, if invalid index show error msg