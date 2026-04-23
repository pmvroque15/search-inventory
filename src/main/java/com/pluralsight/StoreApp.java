package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StoreApp {

    static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();
        System.out.println("We carry the following inventory: ");
        Collections.sort(inventory, Comparator.comparing(Product::getName));
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }

    }

    public static ArrayList<Product> getInventory() {

        ArrayList<Product> inventory = new ArrayList<Product>();

        //Reads file line by line
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            //4567|10' 2x4 (grade B)|9.99
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split("\\|");

                int id = Integer.parseInt(lineSplit[0]);
                String productName = lineSplit[1];
                double price = Double.parseDouble(lineSplit[2]);

                Product product = new Product(id, productName, price);

                inventory.add(product);


            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}

