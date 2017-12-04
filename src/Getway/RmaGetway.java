/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.RMA;
import DAL.Orders;
import List.ListRma;
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


public class RmaGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(RMA rma) {
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("insert into devolucion (DevoluionNombre, "
                    + "DevoluionDias, Comentario, CreatorId, DevoluionFecha) values(?,?,?,?,?)");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            pst.setString(3, rma.rmaComment);
            pst.setString(4, rma.creatorId);
            pst.setString(5, LocalDate.now().toString());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado correctamente");
            alert.setContentText("Devolución" + "  '" + rma.rmaName + "' " + "Agregada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(RMA rma) {

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from devolucion");
            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);
                rma.creatorId = rs.getString(5);
                rma.date = rs.getString(6);
                rma.creatorName = sql.getName(rma.creatorId, rma.creatorName, "usuario");
                rma.rmaDetails.addAll(new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(RMA rma) {
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from devolucion where DevoluionId=?");
            pst.setString(1, rma.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(RMA rma) {
        rma.rmaDetails.clear();
        System.out.println("nombre :" + rma.rmaName);
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from devolucion where DevoluionNombre like ? or DevoluionDias like ? ORDER BY DevoluionNombre");

            pst.setString(1, "%" + rma.rmaName + "%");
            pst.setString(2, "%" + rma.rmaName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);
                rma.creatorId = rs.getString(5);
                rma.date = rs.getString(6);
                rma.creatorName = sql.getName(rma.creatorId, rma.creatorName, "usuario");
                rma.rmaDetails.addAll(new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(RMA rma) {
        try {
            pst = con.prepareStatement("update devolucion set DevoluionNombre=? , DevoluionDias=?, Comentario=? where DevoluionId=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            pst.setString(3, rma.rmaComment);
            pst.setString(4, rma.id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado correcto");
            alert.setContentText("Devolución" + "  '" + rma.rmaName + "' " + "Actualizada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(RMA rma) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from devolucion where DevoluionId=?");
            pst.setString(1, rma.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isUniqName(RMA rma) {
        con = dbCon.geConnection();
        boolean uniqRMA = false;
        try {
            pst = con.prepareCall("select * from devolucion where DevoluionNombre=? or DevoluionDias=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Correcto");
                alert.setHeaderText("ERROR : guardado ");
                alert.setContentText("Devolucion" + "  '" + rma.rmaName + "' " + "ya existe");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return uniqRMA;
            }
            rs.close();
            pst.close();
            con.close();
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }

    public boolean isNotUse(RMA rma) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from productos where DevoluionId=?");
            pst.setString(1, rma.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Correcto");
                alert.setHeaderText("ERROR : guardado");
                alert.setContentText("Esta devolucion'" + rs.getString(2) + "'  \n borra producto primero");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(RmaGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;

    }
}
