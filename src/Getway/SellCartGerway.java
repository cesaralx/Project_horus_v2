package Getway;

import DAL.RMA;
import DAL.SellCart;
import List.ListSold;
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

public class SellCartGerway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();


    public void save(SellCart sellCart){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into venta(VentaId,ClienteId,"
                    + "ProductoId,MonederoPrecio,VentaPecio,Cantidad,PrecioTotal,"
                    + "FechaLimiteGarantia,VendedorId,VendedorFecha ) values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, sellCart.sellID);
            pst.setString(2, sellCart.customerID);
            pst.setString(3, sellCart.productID);
            pst.setDouble(4, Double.parseDouble(sellCart.pursesPrice));
            pst.setDouble(5, Double.parseDouble(sellCart.sellPrice));
            pst.setString(6, sellCart.quantity);
            pst.setDouble(7, Double.parseDouble(sellCart.totalPrice));
            pst.setString(8, sellCart.warrentyVoidDate);
            pst.setString(9, sellCart.sellerID);
            pst.setString(10, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(SellCart sellCart){
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from venta");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "productos");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "usuario");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "cliente");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void firstTenView(SellCart sellCart){
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select top 15 * from venta ");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "productos");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "usuario");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "cliente");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchView(SellCart sellCart){
        con = dbCon.geConnection();
        sellCart.carts.clear();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from venta where ventaId like ?");
            pst.setString(1, "%" + sellCart.sellID + "%");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "productos");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "usuario");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "cliente");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void update(SellCart sellCart) {
           con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update venta set VentaId =?,ClienteId=?,"
                    + "ProductoId=?,MonederoPrecio=?,VentaPecio=?,Cantidad=?,PrecioTotal=?,"
                    + "FechaLimiteGarantia=?,VendedorId=?,VendedorFecha=? WHERE VentaId = ?");
            pst.setString(1, sellCart.sellID);
            pst.setString(2, sellCart.customerID);
            pst.setString(3, sellCart.productID);
            pst.setDouble(4, Double.parseDouble(sellCart.pursesPrice));
            pst.setDouble(5, Double.parseDouble(sellCart.sellPrice));
            pst.setString(6, sellCart.quantity);
            pst.setDouble(7, Double.parseDouble(sellCart.totalPrice));
            pst.setString(8, sellCart.warrentyVoidDate);
            pst.setString(9, sellCart.sellerID);
            pst.setString(10, LocalDateTime.now().toString());
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Correcto : guardado correcto");
            alert.setContentText("Actualizado correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
