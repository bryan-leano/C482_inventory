package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import static Model.Inventory.addPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
/* import javax.xml.bind.ValidationException; */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.RadioButton;

public class AddPartController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {


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

}
