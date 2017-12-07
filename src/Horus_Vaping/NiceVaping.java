/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Horus_Vaping;

import dataBase.DBModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author rifat
 */
public class NiceVaping extends Application {
    
    public NiceVaping() {
        
        DBModel model = new DBModel();
        model.createDataBase();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login_1.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vienvenido a Nice Vaping ");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(500.0);
            primaryStage.setMinWidth(850.0);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NiceVaping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
