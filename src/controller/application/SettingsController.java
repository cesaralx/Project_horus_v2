/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import LogFile.logger;
import controller.application.settings.MyAccountController;
import controller.application.settings.OrgSettingController;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import UserLogged.userNameMedia;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author alexi
 */
public class SettingsController implements Initializable {
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miOrganize;
    @FXML
    private MenuItem miBackup;
    @FXML
    private MenuItem miLogFile;
    @FXML
    private MenuItem miChat;
    @FXML
    private StackPane spSettingContent;
    @FXML
    private Label lblCurrentStatus;
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    logger log = new logger();
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    private String userID;
    userNameMedia usrMedia;    

    /**
     *
     */
    @FXML
    public BorderPane bpSettings;
 
    /**
     *
     * @return
     */
    public userNameMedia getUsrMedia() {
        return usrMedia;
    }

    /**
     *
     * @param usrMedia
     */
    public void setUsrMedia(userNameMedia usrMedia) {
        userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void miMyASccountOnClick(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Mi cuenta");
        
        MyAccountController myAccount = new MyAccountController();
        userNameMedia usrIdMedia = new userNameMedia();
        
        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/view/application/settings/MyAccount.fxml").openStream());
        
        usrIdMedia.setId(userID);
        
        MyAccountController mAccount = fxmlload.getController();
        
        mAccount.setUsrMediaID(usrMedia);
        mAccount.showDetails();
        AnchorPane acPane = fxmlload.getRoot();
        
        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);
        
    }
    
        @FXML
    private void openChat(ActionEvent event) throws IOException {
        log.wirteLogInfo("Abriendo servidor chat");
        TrayNotification tn = new TrayNotification();
            tn.setTitle("Chat");
            tn.setMessage("Mostrando servidor");
            tn.setAnimationType(AnimationType.POPUP);
            tn.setNotificationType(NotificationType.NOTICE);
            tn.showAndDismiss(Duration.seconds(1));
        
        Parent root = null;
        try {
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
    
        
    @FXML
    private void openChatClient(ActionEvent event) throws IOException {
        log.wirteLogInfo("Mostrando cliente chat");
       TrayNotification tn = new TrayNotification();
        tn.setTitle("Chat");
        tn.setMessage("Mostrando cliente");
        tn.setNotificationType(NotificationType.NOTICE);
        tn.setAnimationType(AnimationType.POPUP);
        tn.showAndDismiss(Duration.seconds(1));

        Parent root = null;
        try {
//            log.wirteLogInfo("Iniciando menu de configuracion de BD");
            root = FXMLLoader.load(getClass().getResource("/view/chat/ViewChatClient.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Chat Cliente - Nice Vaping");
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void miOrganizeOnAction(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Empresa, configuracion");
        
        OrgSettingController orgSetting = new OrgSettingController();
        userNameMedia useridMedia = new userNameMedia();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/settings/OrgSetting.fxml").openStream());
        
        useridMedia.setId(userID);
        
        OrgSettingController orgnizeUsrId = fXMLLoader.getController();
        orgnizeUsrId.setUsrIdMedia(useridMedia);
        orgnizeUsrId.showDetails();
        spSettingContent.getChildren().clear();
        AnchorPane orgAp = fXMLLoader.getRoot();
        spSettingContent.getChildren().add(orgAp);
    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {
        
    }
    
            @FXML
    private void openLogFile(ActionEvent event) throws IOException {
        log.wirteLogInfo("Mostrando log");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/logFile.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("LogFile visor -Nice Vaping");
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     */
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from userpermission where UsuarioId=?");
            pst.setString(1, userID);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(18)==0){
                    miOrganize.setDisable(true);
                }else{
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
