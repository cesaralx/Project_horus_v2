/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Orders;
import Models.Unit;
import List.ListUnit;
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
 * esta clase maneja las unidades, estas estan ligadas a productos.
 * se crea una clase aparte porque existen liquidos de diferentes capacidades
 * es decir de un mismo producto existen diferentes presentaciones
 * @author alexi
 * @version 1.5
 */
public class UnitGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    SQL sql = new SQL();

    /**
     * guarda una nueva unidad
     * @param unit
     */
    public void save(Unit unit) {
        con = dbCon.geConnection();

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("insert into unit(UnitName,UnitDescripcion,CreatorId,Fecha) values(?,?,?,?)");
            pst.setString(1, unit.unitName);
            pst.setString(2, unit.unitDescription);
            pst.setString(3, unit.creatorId);
            pst.setString(4, LocalDate.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado");
            alert.setContentText("Unidad" + "  '" + unit.unitName + "' " + "Agregada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * busca todas las unidades 
     * @param unit
     */
    public void view(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from unit");
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
                unit.creatorId = rs.getString(4);
                unit.date = rs.getString(5);
                unit.creatorName = sql.getName(unit.creatorId, unit.creatorName, "usuario");
                unit.unitDetails.addAll(new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * selecciona unidad por id
     * @param unit
     */
    public void selectedView(Unit unit) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from unit where UnitId=?");
            pst.setString(1, unit.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
            }
            pst.close();
            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * selecciona unidad por nombre
     * @param unit
     */
    public void searchView(Unit unit) {
        con = dbCon.geConnection();
        unit.unitDetails.clear();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from unit where UnitName like ? ORDER BY UnitName");

            pst.setString(1, "%" + unit.unitName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
                unit.creatorId = rs.getString(4);
                unit.date = rs.getString(5);
                unit.creatorName = sql.getName(unit.creatorId, unit.creatorName, "usuario");
                unit.unitDetails.addAll(new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date));
            }
            pst.close();
            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * actualiza una unidad existente
     * @param unit
     */
    public void update(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from unit where UnitId=? and UnitName=?");
            pst.setString(1, unit.id);
            pst.setString(2, unit.unitName);
            rs = pst.executeQuery();
            while (rs.next()) {
                updateNow(unit);
                return;
            }
            pst.close();
            con.close();
            rs.close();
            if (isUniqName(unit)) {
                updateNow(unit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * actualiza nombre de unidad y descripcion
     * @param unit
     */
    public void updateNow(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update unit set UnitName=? , UnitDescripcion=? where UnitId=?");
            pst.setString(1, unit.unitName);
            pst.setString(2, unit.unitDescription);
            pst.setString(3, unit.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : actualizado");
            alert.setContentText("Unidad" + "  '" + unit.unitName + "' " + "Actualizada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * elimina una unidad
     * @param unit
     */
    public void delete(Unit unit) {
        con = dbCon.geConnection();
        deleteParmanently(unit);
    }

    /**
     * busca si ya existe una unidad
     * @param unit
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isUniqName(Unit unit) {
        con = dbCon.geConnection();
        boolean uniqBrand = false;
        try {
            pst = con.prepareCall("select * from unit where UnitName=?");
            pst.setString(1, unit.unitName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : Ya existe");
                alert.setContentText("Unidad" + "  '" + unit.unitName + "' " + "Ya existe");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return uniqBrand;
            }
            pst.close();
            con.close();
            rs.close();
            uniqBrand = true;
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqBrand;
    }

    /**
     * elimina unidad por id
     * @param unit
     */
    public void deleteParmanently(Unit unit) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareCall("delete from unit where UnitId=?");
            pst.setString(1, unit.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * busca si la unidad esta ligada a un producto
     * @param unit
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isNotUse(Unit unit) {
        con = dbCon.geConnection();
        boolean isNotUse = false;

        try {
            pst = con.prepareStatement("select * from productos where UnitId=?");
            pst.setString(1, unit.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : usado");
                alert.setContentText("Esta unidad esta usada '" + rs.getString(2) + "' producto \n borra el producto primero");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return isNotUse;
            }
            pst.close();
            rs.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(UnitGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }

}
