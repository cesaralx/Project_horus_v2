/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import LogFile.logger;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class LogFileController implements Initializable {
    
    logger log = new logger();

    
    
    
    @FXML
    JFXTextArea taLog;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    @FXML
    private void readLog(ActionEvent e) throws IOException {
        taLog.clear();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/LogFile.logger.log"));
            String str;
            while ((str = in.readLine()) != null) {
                taLog.appendText(str+"\n");
            }
        } catch (IOException a) {
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
            }
        }
        log.readFile();
                TrayNotification tn = new TrayNotification();
            tn.setTitle("Log");
            tn.setMessage("Log mostrado");
            tn.setAnimationType(AnimationType.POPUP);
            tn.setNotificationType(NotificationType.SUCCESS);
            tn.showAndDismiss(Duration.seconds(1));
    }
    
        
    private void readLogInit() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/LogFile.logger.log"));
            String str;
            while ((str = in.readLine()) != null) {
                taLog.appendText(str+"\n");
            }
        } catch (IOException a) {
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
            }
        }
    }
    
        private void readLogOrdenado(String path) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                taLog.appendText(str+"\n");
            }
        } catch (IOException a) {
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
            }
        }
    }
    
    @FXML
    private void orderByTipo(ActionEvent e) throws IOException {
        taLog.clear();

        log.orderByTipo();
        readLogOrdenado(System.getProperty("user.dir") + "/logOrdenado.log");
    }
    
    @FXML
    private void orderByUser(ActionEvent e) throws IOException {
        taLog.clear();

        log.orderByQuickSort();
        readLogOrdenado(System.getProperty("user.dir") + "/logOrdenado.log");
    }
    
    
}
