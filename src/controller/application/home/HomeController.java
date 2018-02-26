/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.home;

import Actions.CurrentProductGetway;
import LogFile.logger;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author alexi
 */
public class HomeController implements Initializable {
    @FXML
    private Label lblTotalItem;
    @FXML
    private Label lblStockValue;
    @FXML
    private Label lblTotalSupplyer;
    @FXML
    private Label lblTotalEmployee;
    @FXML
    PieChart pcAll;
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Label lblTotalSell;
    @FXML
    private Label lblSellValue;
    @FXML
    private Label lblOrgName;
    @FXML
    private Text txtOrgAddress;
    @FXML
    private Text txtorgPhoneNumber;
    @FXML
    private Button jbntNotificacion;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    logger log = new logger();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valueCount();
        totalCount();
        try {
            getPieData();
        } catch (IOException ex) {
        }
    }    
    
    /**
     *
     * @throws IOException
     */
    public void getPieData() throws IOException {
        log.wirteLogInfo("Cargando datos inicio");
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Total Productos", Double.valueOf(lblTotalItem.getText()) ),
                        new PieChart.Data("Valor del stock", Double.valueOf(lblStockValue.getText())),
                        new PieChart.Data("Total de venta", Double.valueOf(lblTotalSell.getText())),
                        new PieChart.Data("Valor de ventas", Double.valueOf(lblSellValue.getText())),
                        new PieChart.Data("Total de empleados", Double.valueOf(lblTotalEmployee.getText())),
                        new PieChart.Data("Total de proveedores", Double.valueOf(lblTotalSupplyer.getText())));
        pieChartData.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), " ", data.pieValueProperty()
                )
        )
);
        pcAll.setAnimated(true);
        pcAll.setTitle("Datos Generales");
        pcAll.setData(pieChartData);

    }
    
    /**
     *
     */
    public void valueCount(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select Sum(cast(ventaPecio as float)) as ventaPecio from productos");
            rs = pst.executeQuery();
            while (rs.next()) {
                lblStockValue.setText(rs.getString(1));
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//        @FXML
//    private void cancelAction(ActionEvent event) {
//        TrayNotification tn = new TrayNotification();
//        tn.setTitle("Notificacion");
//        tn.setMessage("Este es un ejemplo de alerta");
//        tn.setAnimationType(AnimationType.FADE);
//        tn.showAndDismiss(Duration.seconds(2));
//    }
    
    @FXML
    private void cancelAction(ActionEvent event) {
        TrayNotification tn = new TrayNotification();
        tn.setTitle("Notificacion");
        tn.setMessage("Este es un ejemplo de alerta");
        tn.setAnimationType(AnimationType.FADE);
        tn.showAndDismiss(Duration.seconds(2));
        
        Parent root = null;
        try {
//            log.wirteLogInfo("Iniciando menu de configuracion de BD");
            root = FXMLLoader.load(getClass().getResource("/view/chat/ViewChat.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Chat - Nice Vaping");
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     */
    public void totalCount(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select count(*) from venta");
            rs = pst.executeQuery();
            while (rs.next()) {
                lblTotalSell.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from proveedor");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalSupplyer.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from productos");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalItem.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select sum(precioTotal) from venta");
            rs = pst.executeQuery();
            while(rs.next()){
                lblSellValue.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from usuario");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalEmployee.setText(rs.getString(1));
            }
            pst =con.prepareStatement("select * from empresa where EmpresaId=1");
            rs = pst.executeQuery();
            while(rs.next()){
                lblOrgName.setText(rs.getString(2));
                txtOrgAddress.setText(rs.getString(5));
                txtorgPhoneNumber.setText(rs.getString(4));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
