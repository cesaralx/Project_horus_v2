/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import Basics.CategoryBasics;
import custom.CellFactories;
import dataBase.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import UserLogged.userNameMedia;
import Models.Catagory;
import Actions.CatagoryGetway;
import List.ListCatagory;
import java.util.Optional;

/**
 * FXML Controller class
 *
 * @author alexi
 */
public class ViewCatagoryController implements Initializable {

    private String usrId;
    private String catagoryId;
    private String brandId;
    private String brandName;
    private String creatorId;

    SQL sql = new SQL();
    Catagory catagory = new Catagory();
    CellFactories cellFactories = new CellFactories();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CategoryBasics catagoryBLL = new CategoryBasics();

    private userNameMedia media;
    @FXML
    private TableView<ListCatagory> tblCatagory;
    @FXML
    private TableColumn<Object, Object> clmCatagoryId;
    @FXML
    private TableColumn<Object, Object> clmCatagoryName;
    @FXML
    private TableColumn<Object, Object> clmCatagoryBrand;
    @FXML
    private TableColumn<Object, Object> clmCatagoryCreator;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDate;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDescription;

    /**
     *
     */
    @FXML
    public TableColumn<Object, Object> clmSupplyer;

    /**
     *
     */
    @FXML
    public TableColumn tablClmBox;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;

    /**
     *
     * @return
     */
    public userNameMedia getMedia() {
        return media;
    }

    /**
     *
     * @param media
     */
    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private TextField tfSearch;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void tblCatagoryOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewDetails();
        } else {
            System.out.println("click 2 veces");
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        AddCategoryController addCatagoryController = new AddCategoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddCategory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCategoryController catagoryController = fxmlLoader.getController();
            media.setId(usrId);
            catagoryController.setMedia(media);
            catagoryController.lblHeaderContent.setText("Agregar Categoria");
            catagoryController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (tblCatagory.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("vacio");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        ListCatagory selectedCatagory = tblCatagory.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("estas seguro de eliminarlo \n click ok para confirmar");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            catagory.id = selectedCatagory.getId();
            catagoryBLL.delete(catagory);
            tblCatagory.getItems().clear();
            showDetails();
        }
        
    }

    /**
     *
     */
    public void showDetails() {
        tblCatagory.setItems(catagory.catagoryDetails);
        tablClmBox.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryName.setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        clmSupplyer.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory<>("catagoryDescription"));
        clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        clmCatagoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        catagoryGetway.view(catagory);
        tablClmBox.setCellFactory(cellFactories.cellFactoryCheckBox);

    }

    /**
     *
     * @param event
     */
    @FXML
    public void tfSearchOnType(Event event) {
        catagory.catagoryDetails.clear();
        catagory.catagoryName = tfSearch.getText().trim();
        tblCatagory.setItems(catagory.catagoryDetails);

        clmCatagoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryName.setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        clmSupplyer.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory<>("catagoryDescription"));
        clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        clmCatagoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        catagoryGetway.searchView(catagory);

    }

    private void viewDetails() {
        if (!tblCatagory.getSelectionModel().isEmpty()) {
            ListCatagory selectedCatagory = tblCatagory.getSelectionModel().getSelectedItem();
            System.out.println(selectedCatagory.getCreatorId());
            String items = selectedCatagory.getId();
            if (!items.equals(null)) {
                AddCategoryController addCatagoryController = new AddCategoryController();
                userNameMedia media = new userNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddCategory.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddCategoryController catagoryController = fxmlLoader.getController();
                    media.setId(usrId);
                    catagoryController.setMedia(media);
                    catagoryController.lblHeaderContent.setText("Detalle Categoria");
                    catagoryController.btnUpdate.setVisible(true);
                    catagoryController.btnSave.setVisible(false);
                    catagoryController.catagoryId = selectedCatagory.id;
                    catagoryController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("vacio");
        }

    }

    @FXML
    private void miSearchOnAction(ActionEvent event) {
        tblCatagory.getSelectionModel().clearSelection();
        tfSearch.requestFocus();
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        catagory.catagoryDetails.clear();
        showDetails();
    }

}
