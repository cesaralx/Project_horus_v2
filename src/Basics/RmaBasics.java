package Basics;

import Models.RMA;
import Models.Orders;
import Actions.RmaGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @see Actions.RmaGetway
 * @author alexi
 */
public class RmaBasics {
    SQL sql = new SQL();
    RmaGetway rmaGetway = new RmaGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     *
     * @param rma
     */
    public void save(RMA rma){
        if(isUniqName(rma)){
            rmaGetway.save(rma);
        }
    }

    /**
     *
     * @param rma
     */
    public void update(RMA rma){
        if(sameName(rma)){
            rmaGetway.update(rma);
        }else if (isUniqName(rma)){
            rmaGetway.update(rma);
        }
    }
    
    /**
     *
     * @param rma
     * @return
     */
    public Object delete(RMA rma){
        if(rmaGetway.isNotUse(rma)){
            rmaGetway.delete(rma);
        }else{
            //nothing
        }
        return rma;
    }

    /**
     *
     * @param rma
     * @return
     */
    public boolean sameName(RMA rma){
        boolean sameName =false;
        try {
            pst = con.prepareStatement("select * from devolucion where DevoluionId=? and DevoluionNombre=? and DevoluionDias=?");
            pst.setString(1, rma.id);
            pst.setString(2, rma.rmaName);
            pst.setString(3, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {

                return sameName = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sameName;
    }

    /**
     *
     * @param rma
     * @return
     */
    public boolean isUniqName(RMA rma) {

        boolean uniqRMA = false;
        try {
            pst = con.prepareCall("select * from devolucion where DevoluionNombre=? or DevoluionDias=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : usado");
                alert.setContentText("Devolucion" + "  '" + rma.rmaName +"/"+ rma.rmaDays + "' " + "ya existe");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return uniqRMA;
            }
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }
}
