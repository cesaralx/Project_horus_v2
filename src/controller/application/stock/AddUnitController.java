/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import Basics.UnitBasics;
import java.net.URL;
import java.util.ResourceBundle;

import Actions.UnitGetway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import UserLogged.userNameMedia;
import custom.*;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import Models.Unit;

/**
 * FXML Controller class
 * Agrega unidad de producto
 * @author alexi
 */
public class AddUnitController implements Initializable {

    Unit unit = new Unit();
    UnitGetway unitGetway = new UnitGetway();
    UnitBasics unitBLL = new UnitBasics();

    /**
     *
     */
    public String unitId;

    /**
     *
     */
    @FXML
    public Button btnSave;
    @FXML
    private TextField tfUnitName;
    
    @FXML
    private Button btnClrUnitName;
    
    private String usrId;
    
    private userNameMedia nameMedia;
    
    CustomTf ctf = new CustomTf();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private TextArea taDescription;

    /**
     *
     */
    @FXML
    public Button btnUpdate;

    /**
     *
     */
    @FXML
    public Label lblContent;
    @FXML
    private Button btnClose;

    /**
     *
     * @return
     */
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    /**
     *
     * @param nameMedia
     */
    public void setNameMedia(userNameMedia nameMedia) {
        usrId =  nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctf.clearTextFieldByButton(tfUnitName, btnClrUnitName);
        BooleanBinding bb = tfUnitName.textProperty().isEmpty();
        btnSave.disableProperty().bind(bb);

        
    }    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

        unit.unitName = tfUnitName.getText().trim();
        unit.unitDescription = taDescription.getText().trim();
        unit.creatorId = usrId;
        unitBLL.save(unit);
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        unit.id = unitId;
        unit.unitName = tfUnitName.getText().trim();
        unit.unitDescription =taDescription.getText().trim();
        unitGetway.update(unit);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     *
     */
    public void showDetails() {
        unit.id = unitId;
        unitGetway.selectedView(unit);
        tfUnitName.setText(unit.unitName);
        taDescription.setText(unit.unitDescription);
    }


}
