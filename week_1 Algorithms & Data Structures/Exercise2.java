package weekone;
import java.util.Arrays;
import java.util.Comparator;




public class Exercise2{

    // Product class
    static class Product {
        private int productId;
        private String productName;
        private String category;

        public Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        public int getProductId() { return productId; }
        public void setProductId(int productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        @Override
        public String toString() {
            return "Product{" +
                    "productId=" + productId +
                    ", productName='" + productName + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }
    }

    // Search class
    static class Search {

        // Linear Search Method
        public Product linearSearch(Product[] products, String productName) {
            for (Product product : products) {
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    return product;
                }
            }
            return null;
        }

        // Binary Search Method
        public Product binarySearch(Product[] products, String productName) {
            // Ensure the array is sorted by productName
            Arrays.sort(products, Comparator.comparing(Product::getProductName));

            int left = 0;
            int right = products.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int result = products[mid].getProductName().compareToIgnoreCase(productName);

                if (result == 0) {
                    return products[mid];
                }
                if (result < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Headphones", "Electronics"),
            new Product(3, "Coffee Mug", "Kitchen"),
            new Product(4, "Notebook", "Stationery"),
            new Product(5, "Smartphone", "Electronics")
        };

        Search search = new Search();

        // Linear Search
        System.out.println("Linear Search:");
        Product result = search.linearSearch(products, "Notebook");
        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found.");
        }

        // Binary Search
        System.out.println("\nBinary Search:");
        result = search.binarySearch(products, "Notebook");
        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found.");
        }
    }
}
