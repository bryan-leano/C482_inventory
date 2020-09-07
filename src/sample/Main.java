package sample;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        InHouse part1 = new InHouse(1, "Alpha", 9.99, 15, 10, 20, 101);
        Outsourced part2 = new Outsourced(2, "Beta", 9.99, 15, 10, 20, "One");
        InHouse part3 = new InHouse(3, "Charlie", 9.99, 15, 10, 20, 101);
        Outsourced part4 = new Outsourced(4, "Delta", 9.99, 15, 10, 20, "Two");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        Product product1 = new Product(1, "ProductTestOne", 9.99, 15, 10, 20);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part4);

        Inventory.addProduct(product1);

        launch(args);
    }
}