package Basics;

import Models.CurrentProduct;
import Actions.CurrentProductGetway;
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
 * @see Actions.CurrentProductGetway
 * @author alexi
 */
public class CurrentProductBasics {

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();

    /**
     *
     * @param currentProduct
     */
    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            currentProductGetway.save(currentProduct);
        }
        
    }

    /**
     *
     * @param currentProduct
     */
    public void update(CurrentProduct currentProduct) {
        if(isNotNull(currentProduct)){
        if (isUpdate(currentProduct)) {
            if (checkUpdateCondition(currentProduct)) {
                currentProductGetway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                currentProductGetway.update(currentProduct);
            }
        }
        }
    }

    /**
     *
     * @param currentProduct
     * @return
     */
    public boolean isUniqName(CurrentProduct currentProduct) {
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from productos where ProductoId=?");
            pst.setString(1, currentProduct.productId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : No unico");
                alert.setContentText("ID Producto" + "  '" + currentProduct.productId + "' " + "no es unico");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }

    /**
     *
     * @param currentProduct
     * @return
     */
    public boolean isUpdate(CurrentProduct currentProduct) {
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from productos where Id=? and ProductoId=? and ProductoNombre=? and Cantidad=? and Descripcion=? and ProveedorId=? and MarcaID=? "
                    + "and CategoriaId=? and UnitId=? and MonederoPrecio=? and VentaPecio=? and DevoluionId=? and Fecha=?");
            pst.setString(1, currentProduct.id);
            pst.setString(2, currentProduct.productId);
            pst.setString(3, currentProduct.productName);
            pst.setString(4, currentProduct.quantity);
            pst.setString(5, currentProduct.description);
            pst.setString(6, currentProduct.supplierId);
            pst.setString(7, currentProduct.brandId);
            pst.setString(8, currentProduct.catagoryId);
            pst.setString(9, currentProduct.unitId);
            pst.setString(10, currentProduct.pursesPrice);
            pst.setString(11, currentProduct.sellPrice);
            pst.setString(12, currentProduct.rmaId);
            pst.setString(13, currentProduct.date);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    /**
     *
     * @param currentProduct
     * @return
     */
    public boolean checkUpdateCondition(CurrentProduct currentProduct) {
        boolean isTrueUpdate = false;
        if (isUpdate(currentProduct)) {
            try {
                pst = con.prepareStatement("select * from productos where id=? and ProductoId=?");
                pst.setString(1, currentProduct.id);
                pst.setString(2, currentProduct.productId);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isTrueUpdate;
    }

    /**
     *
     * @param currentProduct
     * @return
     */
    public boolean isNotNull(CurrentProduct currentProduct) {
        
        boolean isNotNull = false;
        if (currentProduct.productId.isEmpty() || currentProduct.sellPrice.isEmpty() || currentProduct.quantity.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Null");
                alert.setContentText("Por favor llena todos los campos");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            
            return isNotNull;
        }
        return isNotNull;
    }

    /**
     *
     * @param currentProduct
     * @return
     */
    public Object delete(CurrentProduct currentProduct){
        if(currentProductGetway.isNotSoled(currentProduct)){
            currentProductGetway.delete(currentProduct);
        }else{
            //noting
        }
        return currentProduct;
    }
    

}
