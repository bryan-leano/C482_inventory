package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {

        allParts.add(part);
    }

    public static Product addProduct(Product product) {

        allProducts.add(product);
        return product;
    }

    public static Part lookupPart (int partId) {
        return null;
    }

    public static  Product lookupProduct (int productId) {
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        return null;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return null;
    }

    public static void updatePart(Part selectedPart) {

        for (int i = 0; i < allParts.size(); i++)
        {
            if (allParts.get(i).getId() == selectedPart.getId())
            {
                allParts.set(i, selectedPart);
                break;
            }
        }
    }

    public static void updateProduct(int index, Product newProduct) {

    }

    public static boolean deletePart(Part selectedPart) {
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static ObservableList<Product> getAllFilteredProducts() {
        return filteredProducts;
    }

}
