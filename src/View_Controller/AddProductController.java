package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/* import javax.xml.bind.ValidationException; */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        productIdTxt.setDisable(true);
        productIdTxt.setText("***Auto Generated***");

        listPartTableView.setItems(Inventory.getAllParts());
        listPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        listPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        listPartInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        listPartPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        includePartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        includePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        includePartInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        includePartPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    void onActionSearchPart(ActionEvent event) {

    }

    private static ObservableList<Part> productParts = FXCollections.observableArrayList();

    @FXML
    void onActionAddPart(ActionEvent event) {
        
        Part part = listPartTableView.getSelectionModel().getSelectedItem();
        productParts.add(part);

        System.out.println(productParts);
        showIncludePartTableView();

    }

    public void showIncludePartTableView()
    {
        includePartTableView.setItems(productParts);
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) {
        int id = 1;
        for(Product i: Model.Inventory.getAllProducts())
        {
            if (i.getId() >= id)
            {
                id = i.getId() + 1;
            }
        }

        String name = productNameTxt.getText();
        double price = Double.parseDouble(productPriceCostTxt.getText());
        int stock = Integer.parseInt(productInvTxt.getText());
        int max = Integer.parseInt(productMaxTxt.getText());
        int min = Integer.parseInt(productMinTxt.getText());
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productPriceCostTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productMaxTxt;



    @FXML
    private TableColumn<?, ?> listPartIdCol;

    @FXML
    private TableColumn<?, ?> listPartNameCol;

    @FXML
    private TableColumn<?, ?> listPartInvLvlCol;

    @FXML
    private TableColumn<?, ?> listPartPricePerUnitCol;

    @FXML
    private TableView<Part> listPartTableView;

    @FXML
    private TableColumn<?, ?> includePartIdCol;

    @FXML
    private TableColumn<?, ?> includePartNameCol;

    @FXML
    private TableColumn<?, ?> includePartInvLvlCol;

    @FXML
    private TableColumn<?, ?> includePartPricePerUnitCol;

    @FXML
    private TableView<Part> includePartTableView;

}
