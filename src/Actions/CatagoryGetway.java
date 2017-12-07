/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Catagory;
import Models.Orders;
import List.ListCatagory;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author alexi
 */
public class CatagoryGetway {

    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Catagory catagory) {
        con = dbCon.geConnection();
        catagory.brandName = sql.getIdNo(catagory.brandName, catagory.brandId, "marcas", "MarcaNombre");
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "marcas", "MarcaNombre");
        catagory.supplyerId = sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "proveedor", "ProveedorNombre");
        try {
            pst = con.prepareStatement("insert into categoria(CategoriaNombre, CategoriaDescripcion, MarcaID, ProveedorId, CreatorId, Fecha) values(?,?,?,?,?,?)");
            pst.setString(1, catagory.catagoryName);
            pst.setString(2, catagory.catagoryDescription);
            pst.setString(3, catagory.brandId);
            pst.setString(4, catagory.supplyerId);
            pst.setString(5, catagory.creatorId);
            pst.setString(6, LocalDate.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado correctamente");
            alert.setContentText("Categoria" + "  '" + catagory.catagoryName + "' " + "Agregada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from categoria");
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);
                catagory.catagoryDescription = rs.getString(3);
                catagory.brandId = rs.getString(4);
                catagory.supplyerId = rs.getString(5);
                catagory.creatorId = rs.getString(6);
                catagory.date = rs.getString(7);
                catagory.brandName = sql.getName(catagory.brandId, catagory.brandName, "marcas");
                catagory.supplyerName = sql.getName(catagory.supplyerId, catagory.supplyerName, "proveedor");
                catagory.creatorName = sql.getName(catagory.creatorId, catagory.catagoryName, "usuario");
                catagory.catagoryDetails.addAll(new ListCatagory(catagory.id, catagory.catagoryName, catagory.catagoryDescription, catagory.brandName, catagory.supplyerName, catagory.creatorName, catagory.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from categoria where CategoriaId=?");
            pst.setString(1, catagory.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);
                catagory.catagoryDescription = rs.getString(3);
                catagory.brandId = rs.getString(4);
                catagory.supplyerId = rs.getString(5);
                catagory.brandName = sql.getName(catagory.brandId, catagory.brandName, "marcas");
                catagory.supplyerName = sql.getName(catagory.supplyerId, catagory.supplyerName, "proveedor");
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void brandView(Catagory catagory) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from marcas where ProveedorId=?");
            pst.setString(1, catagory.supplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.brandName = rs.getString(2);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchView(Catagory catagory) {
        con = dbCon.geConnection();
        catagory.catagoryDetails.clear();

        try {
            pst = con.prepareStatement("select * from categoria where CategoriaNombre like ? ORDER BY CategoriaNombre");

            pst.setString(1, "%" + catagory.catagoryName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                catagory.id = rs.getString(1);
                catagory.catagoryName = rs.getString(2);
                catagory.catagoryDescription = rs.getString(3);
                catagory.brandId = rs.getString(4);
                catagory.supplyerId = rs.getString(5);
                catagory.creatorId = rs.getString(6);
                catagory.date = rs.getString(7);

                catagory.brandName = sql.getName(catagory.brandId, catagory.brandName, "marcas");
                catagory.supplyerName = sql.getName(catagory.supplyerId, catagory.supplyerName, "proveedor");
                catagory.creatorName = sql.getName(catagory.creatorId, catagory.catagoryName, "usuario");

                catagory.catagoryDetails.addAll(new ListCatagory(catagory.id, catagory.catagoryName, catagory.catagoryDescription, catagory.brandName, catagory.supplyerName, catagory.creatorName, catagory.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Catagory catagory) {
        con = dbCon.geConnection();
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "marcas", "MarcaNombre");
        catagory.supplyerId = sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "proveedor", "ProveedorNombre");

        try {
            pst = con.prepareStatement("update categoria set CategoriaNombre=? , CategoriaDescripcion=?,MarcaID=?,ProveedorId=? where CategoriaId=?");
            pst.setString(1, catagory.catagoryName);
            pst.setString(2, catagory.catagoryDescription);
            pst.setString(3, catagory.brandId);
            pst.setString(4, catagory.supplyerId);
            pst.setString(5, catagory.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Update : actualizacion correcta");
            alert.setContentText("Categoria" + "  '" + catagory.catagoryName + "' " + "Actualizada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Catagory catagory) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from categoria where CategoriaId=?");
            pst.setString(1, catagory.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNotUse(Catagory catagory) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareCall("select * from productos where CategoriaId=?");
            pst.setString(1, catagory.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROR : Ya existe ");
                alert.setContentText("Categoria" + "  '" + rs.getString(2) + "' " + "Ya existe");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            pst.close();
            rs.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatagoryGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }

}
