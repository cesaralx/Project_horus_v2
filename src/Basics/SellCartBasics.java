package Basics;

import Models.SellCart;
import Actions.SellCartGerway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @see Actions.SellCartGerway
 * @author alexi
 */
public class SellCartBasics {

    SellCartGerway sellCartGerway = new SellCartGerway();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     *
     * @param sellCart
     */
    public void sell(SellCart sellCart) {

        updateCurrentQuentity(sellCart);
        sellCartGerway.save(sellCart);

    }

    /**
     *
     * @param sellCart
     */
    public void updateCurrentQuentity(SellCart sellCart) {
        int oQ = Integer.parseInt(sellCart.oldQuentity);
        int nQ = Integer.parseInt(sellCart.quantity);
        int uQ = (oQ - nQ);
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        try {
            System.out.println("In Processing Update");
            pst = con.prepareStatement("update productos set Cantidad=? where Id=?");
            pst.setString(1, updatedQuentity);
            pst.setString(2, sellCart.Id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update Complete");
    }

}
