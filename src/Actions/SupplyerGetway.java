/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Orders;
import List.ListSupplyer;
import dataBase.DBConnection;
import dataBase.DBProperties;
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
 * maneja los proveedores
 * @author alexi
 */
public class SupplyerGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     * guarda un nuevo proeedor
     * @param supplyer
     */
    public void save(Orders supplyer) {
        con = dbCon.geConnection();
        if (isUniqSupplyerName(supplyer)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into proveedor (ProveedorNombre, ProveedorTelefono, ProveedorDireccion, ProveedorDescripcion, CreatorId, Fecha ) values(?,?,?,?,?,?)");
                pst.setString(1, supplyer.supplyerName);
                pst.setString(2, supplyer.supplyerContactNumber);
                pst.setString(3, supplyer.supplyerAddress);
                pst.setString(4, supplyer.supplyerDescription);
                pst.setString(5, supplyer.creatorId);
                pst.setString(6, LocalDate.now().toString());
                pst.executeUpdate();
                con.close();
                pst.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto");
                alert.setHeaderText("Correcto : guardado");
                alert.setContentText("Proveedor" + "  '" + supplyer.supplyerName + "' " + "Agregado correctamente");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * busca todos los proveedores
     * @param supplyer
     */
    public void view(Orders supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from proveedor");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);

                supplyer.supplyerDetails.addAll(new ListSupplyer(supplyer.id, supplyer.supplyerName, supplyer.supplyerContactNumber, supplyer.supplyerAddress, supplyer.supplyerDescription, supplyer.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
//            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception");
        }
    }

    /**
     * busca proveedoores por nombre o telefono
     * @param supplyer
     */
    public void searchView(Orders supplyer) {
        supplyer.supplyerDetails.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from proveedor where proveedorNombre like ? or proveedorTelefono like ? ORDER BY proveedorNombre");
            pst.setString(1, "%" + supplyer.supplyerName + "%");
            pst.setString(2, "%" + supplyer.supplyerName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);
                supplyer.supplyerDetails.addAll(new ListSupplyer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * selecciona proveedores por id
     * @param supplyer
     */
    public void selectedView(Orders supplyer) {
        System.out.println("nombre :" + supplyer.supplyerName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from proveedor where ProveedorId=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * actualiza proveedores existentes
     * @param supplyer
     */
    public void update(Orders supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from proveedor where ProveedorId=? and ProveedorNombre=?");
            pst.setString(1, supplyer.id);
            pst.setString(2, supplyer.supplyerName);
            rs = pst.executeQuery();
            while (rs.next()) {
                updateNow(supplyer);
                rs.close();
                pst.close();
                con.close();
                return;
            }
            rs.close();
            con.close();
            pst.close();
            if (isUniqSupplyerName(supplyer)) {
                updateNow(supplyer);
                rs.close();
                con.close();
                pst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * elimina un proveedor existente
     * @param supplyer
     */
    public void delete(Orders supplyer) {
        con = dbCon.geConnection();
        try {

            con = dbCon.geConnection();
            pst = con.prepareCall("SELECT * FROM marcas WHERE ProveedorId=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Correcto");
                alert.setHeaderText("ERROR : Acción denegada");
                alert.setContentText("Este provvedor tiene algunas marcas, por favor elimina las marcas primero!");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return;
            }
            rs.close();
            con.close();
            pst.close();
            deleteParmanently(supplyer);
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * busca si ya existe el proveedor
     * @param supplyer
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isUniqSupplyerName(Orders supplyer) {
        con = dbCon.geConnection();
        boolean uniqSupplyer = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select ProveedorNombre from proveedor where ProveedorNombre=?");
            pst.setString(1, supplyer.supplyerName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR :  Acción denegada");
                alert.setContentText("Proveedor" + "  '" + supplyer.supplyerName + "' " + "Ya existe");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return uniqSupplyer;
            }
            rs.close();
            con.close();
            pst.close();
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

    /**
     * actualiza el proveedor
     * @param supplyer
     */
    public void updateNow(Orders supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update proveedor set ProveedorNombre=? , ProveedorTelefono=?, ProveedorDireccion=? ,ProveedorDescripcion=? where ProveedorId=?");
            pst.setString(1, supplyer.supplyerName);
            pst.setString(2, supplyer.supplyerContactNumber);
            pst.setString(3, supplyer.supplyerAddress);
            pst.setString(4, supplyer.supplyerDescription);
            pst.setString(5, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Update : Actualización correcta");
            alert.setContentText("Proveedor" + "  '" + supplyer.supplyerName + "' " + "Actualizado correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * elimina un proveedor
     * @param supplyer
     */
    public void deleteParmanently(Orders supplyer) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from proveedor where ProveedorId=?");
            pst.setString(1, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isUpdate(Orders supplyer) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from proveedor where ProveedorId=?");
            pst.setString(1, supplyer.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    /**
     * busca proveedores existentes
     * @param supplyer
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isNotUse(Orders supplyer) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from marcas where ProveedorId=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("Este proveedor tiene  '" + rs.getString(2) + "' marca \n Elimina la marca primero");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
                return isNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplyerGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
