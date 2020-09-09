package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import Model.Inventory;
import Model.Product;
import static Model.Inventory.addPart;

import javafx.collections.FXCollections;
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

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    public void sendProduct(Product product)
    {
        productIdTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(String.valueOf(product.getStock()));
        productPriceCostTxt.setText(String.valueOf(product.getPrice()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        productMinTxt.setText(String.valueOf(product.getMin()));

        modifyProductParts = product.getAllAssociatedParts();
        showIncludePartTableView();

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

    private static ObservableList<Part> modifyProductParts = FXCollections.observableArrayList();

    public void showIncludePartTableView()
    {
        includePartTableView.setItems(modifyProductParts);
    }

    public void search(int id)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == id)
                listPartTableView.getSelectionModel().select(selectPart(id));
        }
    }

    public Part selectPart(int id)
    {
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id)
                return part;
        }
        return null;
    }

    @FXML
    void onActionSearchPart(ActionEvent event) {
        if(searchPartTxt.getText() instanceof String) {
            String searchTextField = searchPartTxt.getText();
            listPartTableView.setItems(filter(searchTextField));
        }

        int searchID = Integer.parseInt(searchPartTxt.getText());
        search(searchID);
    }

    @FXML
    void onActionAddPart(ActionEvent event) {

        Part part = listPartTableView.getSelectionModel().getSelectedItem();
        boolean isPartSelected = listPartTableView.getSelectionModel().isEmpty();

        if(!isPartSelected) {
            modifyProductParts.add(part);

            showIncludePartTableView();
        } else {
            System.out.println("This doesn't work");
        }
    }

    @FXML
    void onActionSaveModifyProduct(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(productIdTxt.getText());
            String name = productNameTxt.getText();
            double price = Double.parseDouble(productPriceCostTxt.getText());
            int stock = Integer.parseInt(productInvTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());

            if ((min <= max) && (stock <= max && stock >= min)) {
                Product selectedProduct = new Product(id, name, price, stock, min, max);
                Inventory.updateProduct(selectedProduct);

                modifyProductParts.forEach((i) -> {
                    selectedProduct.addAssociatedPart(i);
                });

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else if (min > max) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning dialog");
                alert.setContentText("Please add correct values for min and max");
                alert.showAndWait();
            } else if (stock <= min || stock >= max) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning dialog");
                alert.setContentText("Inventory must be between min and max");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning dialog");
                alert.setContentText("Please add correct values for this form!");
                alert.showAndWait();
            }


        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setContentText("Please add valid value for each text field");
            alert.showAndWait();
        }

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want " +
                "to remove this part?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Part part = includePartTableView.getSelectionModel().getSelectedItem();
            boolean isIncludedPartSelected = includePartTableView.getSelectionModel().isEmpty();

            if(!isIncludedPartSelected) {
                modifyProductParts.remove(part);

                showIncludePartTableView();
            } else {
                System.out.println("This doesn't work");
            }
        }
    }

    @FXML
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want " +
                "to cancel modifying a product and go back to the main screen?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
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

    @FXML
    private TextField searchPartTxt;
}
