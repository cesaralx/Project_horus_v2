package controller.application.sell;

import Basics.CustomerBasics;
import Actions.CustomerGetway;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import UserLogged.userNameMedia;
import Models.Customer;

/**
 * Agrega cliente a ventas
 * @author alexi
 */
public class AddCustomerController implements Initializable {
    @FXML
    private TextField tfCustomerName;
    @FXML
    private TextArea taCustomerContact;
    @FXML
    private TextArea taCustomerAddress;

    /**
     *
     */
    @FXML
    public Button btnSave;

    /**
     *
     */
    @FXML
    public Label lblCustomerContent;
    @FXML
    private Button btnClose;

    /**
     *
     */
    @FXML
    public Button btnUpdate;
    
    /**
     *
     */
    public String customerId;
    
    private String userId;
    
    userNameMedia nameMedia;
    
    /**
     *
     * @param nameMedia el usuario logeado
     */
    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    CustomerBasics customerBLL = new CustomerBasics();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        customer.customerName = tfCustomerName.getText().trim();
        customer.customerAddress = taCustomerAddress.getText().trim();
        customer.customerConNo = taCustomerContact.getText().trim();
        customer.userId = userId;
        customerBLL.save(customer);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        customer.id = customerId;
        customer.customerName = tfCustomerName.getText().trim();
        customer.customerAddress = taCustomerAddress.getText().trim();
        customer.customerConNo = taCustomerContact.getText().trim();
        customerBLL.update(customer);
    }
    
    /**
     *
     */
    public void viewDetails(){
        customer.id = customerId;
        customerGetway.selectedView(customer);
        tfCustomerName.setText(customer.customerName);
        taCustomerContact.setText(customer.customerConNo);
        taCustomerAddress.setText(customer.customerAddress);
    }
}
