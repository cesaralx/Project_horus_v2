/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.Users;
import LogFile.logger;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import custom.CustomPf;
import custom.CustomTf;
import dataBase.DBConnection;
import dataBase.DBProperties;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import media.userNameMedia;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author danml
 */
public class LoginController1 implements Initializable {

    Users users = new Users();
    logger log = new logger();
    
    private Label label;
    
    @FXML
    private JFXButton bntServer;
    @FXML
    private Hyperlink hlCrateAccount;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView imgProgress;
    
    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();
    
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidation();
        imgProgress.setVisible(false);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        log.wirteLogInfo("Iniciando sesion");
        if (isValidCondition()) {

        imgProgress.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            try {
                completeLogin(event);
            } catch (IOException ex) {
                Logger.getLogger(LoginController1.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        pauseTransition.play();
        } 
    }

    private void handleValidation() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Requerido");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtUsername.getValidators().add(fieldValidator);
        txtUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtUsername.validate();

            }
        });
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Requerido");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtPassword.getValidators().add(fieldValidator2);
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                txtPassword.validate();
            }
        });

    }

    private void completeLogin(ActionEvent event) throws IOException {
//        btnLogin.getScene().getWindow().hide();
        imgProgress.setVisible(false);
            
        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        if (con != null) {
            userNameMedia media = new userNameMedia();

            ApplicationController apController = new ApplicationController();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Application.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            
                try {
                    pst = con.prepareStatement("select * from usuario where UsrName=? and Password=? and Status=1");
                    pst.setString(1, txtUsername.getText());
                    pst.setString(2, txtPassword.getText());
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                        ApplicationController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.btnHomeOnClick(event);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.getIcons().add(new Image("/image/icon.png"));
                        adminPanelStage.setTitle(rs.getString(3));
                        adminPanelStage.show();

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                    } else {
                        log.wirteLogInfo("Password Not Match");
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Password Not Match");
//                        alert.setHeaderText("Error : Name or Pass Not matched");
//                        alert.setContentText("User Name or Password not matched \ntry Again");
//                        alert.initStyle(StageStyle.UNDECORATED);
//                        alert.showAndWait();
                        TrayNotification tn = new TrayNotification();
                        tn.setNotificationType(NotificationType.WARNING);
                               tn.setTitle("Password Not Match");
                               tn.setMessage("User Name or Password not matched \ntry Again");
                               tn.setAnimationType(AnimationType.FADE);
                               tn.showAndDismiss(Duration.seconds(2));
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Error : Server Not Found");
//            alert.setContentText("Make sure your mysql is Start properly, \n");
//            alert.initStyle(StageStyle.UNDECORATED);
//            alert.showAndWait();
            TrayNotification tn = new TrayNotification();
            tn.setNotificationType(NotificationType.ERROR);
            tn.setTitle("Errpr: Server Not Found");
            tn.setMessage("Make sure your SQL server is Start properly, \ntry Again");
            tn.setAnimationType(AnimationType.FADE);
            tn.showAndDismiss(Duration.seconds(2));
        }
        

    }
    
    private boolean isValidCondition() throws IOException {
        boolean validCondition = false;
        if (txtUsername.getText().trim().isEmpty()
                || txtPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING :");
            alert.setHeaderText("WARNING !!");
            alert.setContentText("Llena ambos campos");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        log.wirteLogInfo("Es valido: " + validCondition);
        return validCondition;
    }

    @FXML
    private void hlDbOnAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Server Status -StoreKeeper");
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void hlCreateAnAccount(ActionEvent event) throws IOException {
        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        if (con != null) {
            try {
                pst = con.prepareStatement("SELECT UsuarioId FROM usuario ORDER BY UsuarioId ASC OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY ");
                rs = pst.executeQuery();
                if (rs.next()) {
//                    apMother.setOpacity(0.7);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("You can't create an account without admin \n permission");
                    alert.initStyle(StageStyle.UNDECORATED);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
//                        apMother.setOpacity(1.0);
                    }
                    return;
                }
                con.close();
                pst.close();
                rs.close();
                loadRegistration();

            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error : Server Not Found");
            alert.setContentText("Make sure your mysql is Start properly, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }

    }

    private void loadRegistration() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Registration -StoreKeeper");
            nStage.show();
            Stage stage = (Stage) hlCrateAccount.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        
        

}
