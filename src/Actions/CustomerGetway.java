/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Customer;
import List.ListCustomer;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author alexi
 */
public class CustomerGetway {

    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into cliente (ClienteNombre, "
                    + "ClienteContNo,ClienteDireccion, TotalBuy, CreatorId,Fecha) values(?,?,?,?,?,?)");
            pst.setString(1, customer.customerName);
            pst.setString(2, customer.customerConNo);
            pst.setString(3, customer.customerAddress);
            pst.setString(4, customer.totalBuy);
            pst.setString(5, customer.userId);
            pst.setString(6, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado correctamente");
            alert.setContentText("Cliente" + "  '" + customer.customerName + "' " + "agregado correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from cliente");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.id = rs.getString(1);
                customer.customerName = rs.getString(2);
                customer.customerConNo = rs.getString(3);
                customer.customerAddress = rs.getString(4);
                customer.totalBuy = rs.getString(5);
                customer.userId = rs.getString(6);
                customer.date = rs.getString(7);
                customer.userName = sql.getName(customer.userId, customer.userName, "usuario");
                customer.customerList.addAll(new ListCustomer(customer.id, customer.customerName, customer.customerConNo, customer.customerAddress, customer.totalBuy, customer.userName, customer.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from cliente where ClienteId=?");
            pst.setString(1, customer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.customerName = rs.getString(2);
                customer.customerConNo = rs.getString(3);
                customer.customerAddress = rs.getString(4);
                customer.totalBuy = rs.getString(5);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(Customer customer) {
        con = dbCon.geConnection();
        customer.customerList.clear();
        try {
            pst = con.prepareStatement("select * from cliente where ClienteNombre like ? or ClienteContNo like ?");
            pst.setString(1, "%" + customer.customerName + "%");
            pst.setString(2, "%" + customer.customerName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.id = rs.getString(1);
                customer.customerName = rs.getString(2);
                customer.customerConNo = rs.getString(3);
                customer.customerAddress = rs.getString(4);
                customer.totalBuy = rs.getString(5);
                customer.userId = rs.getString(6);
                customer.date = rs.getString(7);
                customer.userName = sql.getName(customer.userId, customer.userName, "usuario");
                customer.customerList.addAll(new ListCustomer(customer.id, customer.customerName, customer.customerConNo, customer.customerAddress, customer.totalBuy, customer.userName, customer.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE cliente set ClienteNombre=?,ClienteContNo=?,ClienteDireccion=? where ClienteId=?");
            pst.setString(1, customer.customerName);
            pst.setString(2, customer.customerConNo);
            pst.setString(3, customer.customerAddress);
            pst.setString(4, customer.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Update : Update correcto");
            alert.setContentText("Cliente" + "  '" + customer.customerName + "' " + "actualizacion correcta");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from cliente where ClienteId=?");
            pst.setString(1, customer.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isNotUsed(Customer customer) {
        con = dbCon.geConnection();
        boolean isNotUsed = false;
        try {
            pst = con.prepareStatement("select * from venta where ClienteId=?");
            pst.setString(1, customer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROR : Ya existe ");
                alert.setContentText("El cliente usado en la venta'" + rs.getString(2) + "' marca \n borra el cliente primero");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUsed;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUsed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isNotUsed;
    }

}
