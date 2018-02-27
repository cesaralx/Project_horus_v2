/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Pensada en crear la Bd si no existe
 * @deprecated no se usa
 * @author alexi
 */
public class DBModel {
    
    Properties properties = new Properties();
    InputStream inputStream;
    String db;
    
    /**
     *
     */
    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
        }
    }

    PreparedStatement pst;

    /**
     * crea la base de datos
     */
    public void createDataBase() {
        loadPropertiesFile();
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("If db_id(N'"+db+"')IS NULL CREATE database "+db);
            pst.execute();

            
            System.out.println("Base de datos creada!");

        } catch (SQLException ex) {
            System.err.println(ex);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Configuracion Servidor");
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
