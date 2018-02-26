/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Brands;
import Models.Orders;
import List.ListBrands;
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
 * Maneja las marcas
 * @author alexi
 */
public class BrandsGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     *permite guardar una marca nueva
     * @param brands el objeto brands para dar de alta
     */
    public void save(Brands brands) {
        con = dbCon.geConnection();
        brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "proveedor", "ProveedorNombre");

        try {
            pst = con.prepareStatement("insert into marcas (MarcaNombre, Descripcion, ProveedorId, CreatorId, Fecha ) values(?,?,?,?,?)");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.brandComment);
            pst.setString(3, brands.supplyrId);
            pst.setString(4, brands.creatorId);
            pst.setString(5, LocalDate.now().toString());
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado");
            alert.setContentText("Marca" + "  '" + brands.brandName + "' " + "Agregada");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *permite recuperar todas las marcas disponibles
     * @param brands el objeto para recuperar
     */
    public void view(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareCall("select * from marcas");
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "proveedor");
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "usuario");
                brands.brandDitails.addAll(new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *selecciona una marca en espesifico
     * @param brands retorna la marca buscada
     */
    public void selectedView(Brands brands) {
        con = dbCon.geConnection();

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from marcas where MarcaID=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "proveedor");
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *busca marca por nombre
     * @param brands
     */
    public void searchView(Brands brands) {
        con = dbCon.geConnection();

        brands.brandDitails.clear();
        System.out.println("nombre :" + brands.brandName);

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from marcas where MarcaNombre like ? ORDER BY MarcaNombre");
            System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + brands.brandName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "proveedor");
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "usuario");
                brands.brandDitails.addAll(new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *elimina una marca por id
     * @param brands recibe el id a eliminar
     */
    public void delete(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("delete from marcas where MarcaID=?");
            pst.setString(1, brands.id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *actualiza una marca
     * @param brands la marca a actualizar
     */
    public void update(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update marcas set MarcaNombre=? , Descripcion=?,ProveedorId=? where MarcaID=?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.brandComment);
            pst.setString(3, brands.supplyrId);
            pst.setString(4, brands.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : actualización ");
            alert.setContentText("Actualización" + "  '" + brands.brandName + "' " + "Correcta");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *busca si una marca ya esta dada de alta
     * @param brands
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isNotUsed(Brands brands){
        con = dbCon.geConnection();
        boolean inNotUse = false;
        try {
            pst = con.prepareStatement("select * from categoria where MarcaID=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while(rs.next()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR : Ya existe ");
            alert.setContentText("Marca" + "  '" + brands.brandName + "' " + "Ya existe");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
                return inNotUse;
            }rs.close();
            pst.close();
            con.close();
            inNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandsGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inNotUse;
    }

}
