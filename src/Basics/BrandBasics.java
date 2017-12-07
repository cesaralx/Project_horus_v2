package Basics;

import Models.Brands;
import Models.Orders;
import Actions.BrandsGetway;
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

public class BrandBasics {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    BrandsGetway brandsGetway = new BrandsGetway();


    public void save(Brands brands) {
        if (isUniqName(brands)) {
            brandsGetway.save(brands);
        }
    }

    public void update(Brands brands) {
        if (isTrueUpdate(brands)) {
            brandsGetway.update(brands);
        } else if (isUniqName(brands)) {
            brandsGetway.update(brands);
        }

    }

    public void delete(Brands brands){
        if(brandsGetway.isNotUsed(brands)){
            brandsGetway.delete(brands);
        }else{
      }
    }

    public boolean isTrueUpdate(Brands brands) {
        boolean isTreu = false;
        brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "proveedor", "ProveedorNombre");

        try {
            pst = con.prepareStatement("SELECT * FROM marcas WHERE MarcaNombre=? AND ProveedorId =? AND MarcaId =?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplyrId);
            pst.setString(3, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isTreu;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTreu;
    }


    public boolean isUniqName(Brands brands) {
        boolean uniqSupplyer = false;
        try {
            pst = con.prepareCall("select * from marcas where MarcaNombre=? and ProveedorId=?");
            brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "proveedor", "ProveedorNombre");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplyrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ejecutado");
                alert.setHeaderText("ERROR : usado");
                alert.setContentText("Marca" + "  '" + brands.brandName + "' " + "ya en uso!");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
               
                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

}
