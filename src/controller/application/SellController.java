/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.application.sell.ViewCustomerController;
import controller.application.sell.ViewSellController;
import controller.application.stock.CurrentStoreController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import UserLogged.userNameMedia;

/**
 * FXML Controller class
 *
 * @author alexi
 */
public class SellController implements Initializable {
    private String usrId;

    @FXML
    private ToggleButton tbtnSell;
    @FXML
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnReports;
    @FXML
    private ToggleButton tbtPedidos;
    @FXML
    private Label lblPathInfo;
    @FXML
    private StackPane spMainContent;

    userNameMedia nameMedia;

    String userId;

    /**
     *
     */
    @FXML
    public AnchorPane acMainSells;

    /**
     *
     * @param nameMedia
     */
    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        tbtnSell.setSelected(true);
        tbtnCustomer.setToggleGroup(group);
        tbtnSell.setToggleGroup(group);
        tbtnReports.setToggleGroup(group);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void tbtnSellOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Ventas");
        ViewSellController sellController = new ViewSellController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewSell.fxml").openStream());
        media.setId(userId);
        ViewSellController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
//        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Clientes");
        ViewCustomerController customerController = new ViewCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewCustomer.fxml").openStream());
        media.setId(userId);
        ViewCustomerController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event) throws IOException {

    }
    
    @FXML
    private void tbnPedidos(ActionEvent event) throws IOException{
               lblPathInfo.setText("Pedidos");
        CurrentStoreController asc = new CurrentStoreController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/CurrentStore.fxml").openStream());
        media.setId(usrId);
        CurrentStoreController currentStoreController = fXMLLoader.getController();
        currentStoreController.setMedia(nameMedia);
        currentStoreController.viewDetails();
        currentStoreController.showDetails();
        currentStoreController.apCombobox.getStylesheets().add("/style/StoreCombobox.css");
        currentStoreController.settingPermission();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    

}
