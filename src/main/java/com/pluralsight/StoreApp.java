package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreApp {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> inventory = getInventory();
    public static boolean keepGoing = true;

    static void main(String[] args) {
        menu();
    }

    public static void menu() {
        do {
            String choices = """
                    1-List all products
                    2-Lookup a product by its id
                    3-Find all products within a price range
                    4-Add a new product
                    5-Quit the application \n
                    """;
            System.out.println("\nWhat do you want to do? \n" + choices);
            numberOfChoice(readInt());
        } while (keepGoing);
    }

    public static int readInt() {
        return Integer.parseInt(scanner.nextLine());
        }

    public static double readDouble() {
        return Double.parseDouble(scanner.nextLine());
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static ArrayList<Product> getInventory() {

        ArrayList<Product> inventory = new ArrayList<Product>();

        //Reads file line by line
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split("\\|");

                int id = Integer.parseInt(lineSplit[0]);
                String productName = lineSplit[1];
                double price = Double.parseDouble(lineSplit[2]);

                Product product = new Product(id, productName, price);

                inventory.add(product);

            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }

    public static void numberOfChoice(int number) {

        switch (number) {
            case 1:
                System.out.println("We carry the following inventory: ");
                inventory.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

                for (Product p : inventory) {
                    System.out.printf("id: %d %s - Price: $%.2f%n",
                            p.getId(), p.getName(), p.getPrice());
                }
                break;
            case 2:
                System.out.println("What is the ID #: ");
                int id = readInt();

                Product foundProduct = findById(inventory, id);
                if (foundProduct != null) {
                    System.out.printf("Product Name: %s Price: $%.2f%n", foundProduct.getName(), foundProduct.getPrice());
                } else {
                    System.out.println("Not found.");
                }
                break;
            case 3:
                System.out.println("Enter minimum price: ");
                double minimumPrice = readDouble();

                System.out.println("Enter maximum price: ");
                double maximumPrice = readDouble();

                ArrayList<Product> results = findByPriceRange(inventory, minimumPrice, maximumPrice);
                if (!results.isEmpty()) {
                    for (Product p : results) {
                        System.out.printf("ID: %d, Name: %s, Price: $%.2f%n", p.getId(), p.getName(), p.getPrice());
                    }
                } else {
                    System.out.println("No products found");
                }
                break;
            case 4:
                //method for adding products
                System.out.println("What is the name of the product? ");
                String productName = readString();

                System.out.println("What is the price of the product? ");
                double productPrice = readDouble();

                System.out.println("What is the ID #:");
                int productId = readInt();

                addProduct(inventory, productId, productName, productPrice);
                break;
            case 5:
                keepGoing = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
    }

    public static Product findById(ArrayList<Product> product, int targetId) {
        for (Product p : product) {
            if (p.getId() == targetId) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Product> findByPriceRange(ArrayList<Product> products, double minimumPrice, double maximumPrice) {
        ArrayList<Product> results = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= minimumPrice && product.getPrice() <= maximumPrice) {
                results.add(product);
            }
        }
        return results;
    }

    public static void addProduct(ArrayList<Product> products, int productId, String productName, double productPrice) {
        Product newProduct = new Product(productId, productName, productPrice);
        products.add(newProduct);
    }
}

