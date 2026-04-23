package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.classfile.instruction.SwitchCase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StoreApp {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = getInventory();

        //Prompts for the menu
        boolean keepGoing = false;
        do {
            String choices = """
                    1-List all products
                    2-Lookup a product by its id
                    3-Find all products within a price range
                    4-Add a new product
                    5-Quit the application
                    """;

            System.out.println("What do you want to do? \n" + choices);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    System.out.println("We carry the following inventory: ");
                    Collections.sort(inventory, Comparator.comparing(Product::getName));
                    for (int i = 0; i < inventory.size(); i++) {
                        Product p = inventory.get(i);
                        System.out.printf("id: %d %s - Price: $%.2f%n",
                                p.getId(), p.getName(), p.getPrice());
                    };
                    break;
                case 2:
                    keepGoing = true;
                    break;
            }


        } while (!keepGoing);
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

