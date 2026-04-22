package com.pluralsight;
import java.util.ArrayList;

public class StoreApp {

    static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();

        System.out.println("We carry the following inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
        inventory.add(new Product(124, "Conditioner", 6.49));
        inventory.add(new Product(125, "Body Wash", 5.99));
        inventory.add(new Product(126, "Toothpaste", 3.75));
        inventory.add(new Product(127, "Hand Soap", 2.89));
        inventory.add(new Product(128, "Face Cream", 12.50));
        return inventory;
    }
}
