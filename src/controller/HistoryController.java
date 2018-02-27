/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/** 
 * ESTA CLASE NO SE USA
 * FXML Controller class
 * clase pensada en guardar historial, pero despues se desarrollo el logfile
 * @author alexi
 */
public class HistoryController implements Initializable {
    @FXML
    private Label lblHistory;

    /**
     * utilidades usadas en la vista
     */
    @FXML
    public TextArea tfHistory;
    @FXML
    private Button btnClose;

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
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
        
    }

    /**
     * mustra la informacion
     * @deprecated  no se usa
     */
    public void showData(){
        
    }
    
}
