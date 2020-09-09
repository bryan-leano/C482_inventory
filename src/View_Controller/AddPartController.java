package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import static Model.Inventory.addPart;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
/* import javax.xml.bind.ValidationException; */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        partIdTxt.setDisable(true);
        partIdTxt.setText("***Auto Generated***");
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try
        {
            //int id = Integer.parseInt(partIdTxt.getText());
            int id = 1;
            for(Part i: Model.Inventory.getAllParts())
            {
                if (i.getId() >= id)
                {
                    id = i.getId() + 1;
                }
            }

            String name = nameTxt.getText();
            double price = Double.parseDouble(priceCostTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            boolean isInHouse;

            if ((min <= max) && (stock <= max && stock >= min)) {
                if(InHouseRBtn.isSelected()) {
                    isInHouse = true;
                    int machineId = Integer.parseInt(machineIdTxt.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                }
                else {
                    isInHouse = false;
                    String CompanyName = machineIdTxt.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, CompanyName));
                }

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
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
    void onActionDisplayMainScreen(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field " +
                "values, do you want to continue?");

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
    void OnActionInHouseSelection(ActionEvent event) throws IOException {
        machineCompanyLbl.setText("Machine ID");
    }

    @FXML
    void OnActionOutsourcedSelection(ActionEvent event) throws IOException {
        machineCompanyLbl.setText("Company Name");
    }

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private RadioButton InHouseRBtn;

    @FXML
    private RadioButton OutsourcedRBtn;

    @FXML
    private Label machineCompanyLbl;

}
