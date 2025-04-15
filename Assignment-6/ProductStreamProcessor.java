import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.text.DecimalFormat;

class Product {
    private String name;
    private String category;
    private double price;

    // Constructor
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class ProductStreamProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Smartphone", "Electronics", 800),
            new Product("Headphones", "Electronics", 150),
            new Product("Sofa", "Furniture", 700),
            new Product("Dining Table", "Furniture", 900),
            new Product("Chair", "Furniture", 120),
            new Product("T-Shirt", "Clothing", 25),
            new Product("Jeans", "Clothing", 50),
            new Product("Jacket", "Clothing", 100)
        );

        // 1. Group products by category
        System.out.println("📦 Products Grouped by Category:");
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        groupedByCategory.forEach((category, items) -> {
            System.out.println("\nCategory: " + category);
            items.forEach(p -> System.out.println(" - " + p));
        });

        // 2. Find the most expensive product in each category
        System.out.println("\n💰 Most Expensive Product in Each Category:");
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));

        mostExpensiveByCategory.forEach((category, productOpt) -> 
            System.out.println(category + ": " + productOpt.orElse(null))
        );

        // 3. Calculate average price of all products
        double averagePrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("\n📊 Average Price of All Products: $" + df.format(averagePrice));
    }
}
