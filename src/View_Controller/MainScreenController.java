package View_Controller;

import Model.*;

import static Model.Inventory.addPart;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
/* import javax.xml.bind.ValidationException; */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements  Initializable {

    Stage stage;
    Parent scene;

    public boolean search(int id)
    {
        for(Part part : Inventory.getAllParts() )
        {
            if(part.getId() == id)
                return true;
        }
        return false;
    }

    public boolean update(int id, Part partUpdate)
    {
        int index = -1;

        for(Part part : Inventory.getAllParts())
        {
            index++;

            if(part.getId() == id)
            {
                Inventory.getAllParts().set(index, partUpdate);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == id)
                return Inventory.getAllParts().remove(part);
        }

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /*
        if(search(4))
            System.out.println("Found");
        else
            System.out.println("Not found");
        */

        /*
        if(update(1, new Part(1, "Screw", 9.99, 12, 1, 5)))
            System.out.println("update successful");
        else
            System.out.println("update failed");
         */

        if(delete(3))
            System.out.println("delete successful");
        else
            System.out.println("no match");
    }

    @FXML
    void onActionCreatePart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionCreateProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TableColumn<Part, Double> partPricePerUnitCol;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, Double> productPricePerUnitCol;

    @FXML
    private TableView<Product> productTableView;

}
