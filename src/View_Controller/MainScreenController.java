package View_Controller;

import Model.*;

import static Model.Inventory.addPart;

import javafx.collections.ObservableList;
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
import java.util.concurrent.BlockingDeque;

public class MainScreenController implements  Initializable {

    Stage stage;
    Parent scene;

    public void search(int id)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == id)
                partTableView.getSelectionModel().select(selectPart(id));
        }
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

    public Part selectPart(int id)
    {
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id)
                return part;
        }
            return null;
    }

    public ObservableList<Part> filter(String name)
    {
        if(!(Inventory.getAllFilteredParts().isEmpty())) {
            Inventory.getAllFilteredParts().clear();
        }

        for(Part part : Inventory.getAllParts()){
            if(part.getName().contains(name)) {
                Inventory.getAllFilteredParts().add(part);
            }
        }

        if(Inventory.getAllFilteredParts().isEmpty()) {
            return Inventory.getAllParts();
        }
        else {
            return Inventory.getAllFilteredParts();
        }
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        loader.load();

        ModifyPartController MPartCController = loader.getController();
        MPartCController.sendPart(partTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);
        partTableView.setItems(Inventory.getAllParts());
    }

    @FXML
    void onActionSearchPart(ActionEvent event) {
        if(searchTxt.getText() instanceof String) {
            String searchTextField = searchTxt.getText();
            partTableView.setItems(filter(searchTextField));
        }

            int searchID = Integer.parseInt(searchTxt.getText());
            search(searchID);
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        loader.load();

        ModifyProductController MProductController = loader.getController();
        MProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(selectedProduct);
        productTableView.setItems(Inventory.getAllProducts());
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

    @FXML
    private TextField searchTxt;

}
