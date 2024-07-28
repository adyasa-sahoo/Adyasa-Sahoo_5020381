package weekone;
import java.util.HashMap;

public class Exercise1 {

	public static void main(String[] args) {
		
		InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add some products
        Product product1 = new Product("P001", "Laptop", 10, 999.99);
        Product product2 = new Product("P002", "Smartphone", 20, 499.99);
        Product product3 = new Product("P003", "Tablet", 15, 299.99);

        ims.addProduct(product1);
        ims.addProduct(product2);
        ims.addProduct(product3);

        
        System.out.println("Initial Inventory:");
        ims.printInventory();

        
        ims.updateProduct("P002", 25, 479.99);
        System.out.println("\nAfter updating product P002:");
        ims.printInventory();

        
        ims.deleteProduct("P003");
        System.out.println("\nAfter deleting product P003:");
        ims.printInventory();

	}

}

class InventoryManagementSystem {
    private HashMap<String, Product> inventory;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    // Method to add a product
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Method to update a product
    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
        } else {
            System.out.println("Product not found");
        }
    }

    // Method to delete a product
    public void deleteProduct(String productId) {
        inventory.remove(productId);
    }

    // Method to print the inventory
    public void printInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Quantity: " + quantity + ", Price: $" + price;
    }
}
