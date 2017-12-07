/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.CurrentProduct;
import List.ListProduct;
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
public class CurrentProductGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    SQL sql = new SQL();

    public void save(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into productos(ProductoId,ProductoNombre,"
                    + "Cantidad,Descripcion,ProveedorId, MarcaID, "
                    + "CategoriaId,UnitId,MonederoPrecio,VentaPecio, "
                    + "DevoluionId, UsuarioId, Fecha ) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, currentProduct.productId);
            pst.setString(2, currentProduct.productName);
            pst.setString(3, currentProduct.quantity);
            pst.setString(4, currentProduct.description);
            pst.setString(5, currentProduct.supplierId);
            pst.setString(6, currentProduct.brandId);
            pst.setString(7, currentProduct.catagoryId);
            pst.setString(8, currentProduct.unitId);
            pst.setString(9, currentProduct.pursesPrice);
            pst.setString(10, currentProduct.sellPrice);
            pst.setString(11, currentProduct.rmaId);
            pst.setString(12, currentProduct.userId);
            pst.setString(13, currentProduct.date);
            pst.executeUpdate();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Muchas Conexiones");
        }

    }

    public void view(CurrentProduct currentProduct) {
        currentProduct.currentProductList.clear();
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("SELECT * FROM productos DBCC DROPCLEANBUFFERS DBCC FREEPROCCACHE");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from productos where id=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
//                id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewFistTen(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("SELECT TOP 15 * FROM productos WHERE Id NOT IN (SELECT TOP 15 id FROM productos ORDER BY Id)  ORDER BY Id");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        try {
            pst = con.prepareStatement("select * from productos where ProductoId like ? or ProductoNombre like ?");
            pst.setString(1, "%" + currentProduct.productId + "%");
            pst.setString(2, "%" + currentProduct.productId + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBySupplyer(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.supplierId = sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "proveedor", "ProveedorNombre");
        try {
            pst = con.prepareStatement("select * from productos where ProveedorId=?");
            pst.setString(1, currentProduct.supplierId);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByBrand(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.supplierId = sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "proveedor", "ProveedorNombre");
        currentProduct.brandId = sql.getBrandID(currentProduct.supplierId, currentProduct.brandId, currentProduct.brandName);
        System.out.println("Marca ID: " + currentProduct.brandId);

        try {
            pst = con.prepareStatement("select * from productos where MarcaID=?");
            pst.setString(1, currentProduct.brandId);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByCatagory(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();
        currentProduct.supplierId = sql.getIdNo(currentProduct.supplierName, currentProduct.supplierId, "proveedor", "ProveedorNombre");
        currentProduct.brandId = sql.getBrandID(currentProduct.supplierId, currentProduct.brandId, currentProduct.brandName);
        currentProduct.catagoryId = sql.getCatagoryId(currentProduct.supplierId, currentProduct.brandId, currentProduct.catagoryId, currentProduct.catagoryName);
        try {
            pst = con.prepareStatement("select * from productos where CategoriaId=?");
            pst.setString(1, currentProduct.catagoryId);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchByRMA(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        currentProduct.currentProductList.clear();

        try {
            pst = con.prepareStatement("select * from productos where DevoluionId=?");
            pst.setString(1, currentProduct.rmaId);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.currentProductList.addAll(new ListProduct(currentProduct.id, currentProduct.productId, currentProduct.productName, currentProduct.quantity, currentProduct.description, currentProduct.supplierName, currentProduct.brandName, currentProduct.catagoryName, currentProduct.unitName, currentProduct.pursesPrice, currentProduct.sellPrice, currentProduct.rmaName, currentProduct.userName, currentProduct.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sView(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from productos where ProductoId=?");
            pst.setString(1, currentProduct.productId);
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.id = rs.getString(1);
                currentProduct.productId = rs.getString(2);
                currentProduct.productName = rs.getString(3);
                currentProduct.quantity = rs.getString(4);
                currentProduct.description = rs.getString(5);
                currentProduct.supplierId = rs.getString(6);
                currentProduct.brandId = rs.getString(7);
                currentProduct.catagoryId = rs.getString(8);
                currentProduct.unitId = rs.getString(9);
                currentProduct.pursesPrice = rs.getString(10);
                currentProduct.sellPrice = rs.getString(11);
                currentProduct.rmaId = rs.getString(12);
                currentProduct.userId = rs.getString(13);
                currentProduct.date = rs.getString(14);
                currentProduct.supplierName = sql.getName(currentProduct.supplierId, currentProduct.supplierName, "proveedor");
                currentProduct.brandName = sql.getName(currentProduct.brandId, currentProduct.brandName, "marcas");
                currentProduct.catagoryName = sql.getName(currentProduct.catagoryId, currentProduct.catagoryName, "categoria");
                currentProduct.unitName = sql.getName(currentProduct.unitId, currentProduct.unitName, "unit");
                currentProduct.rmaName = sql.getName(currentProduct.rmaId, currentProduct.rmaName, "devolucion");
                currentProduct.userName = sql.getName(currentProduct.userId, currentProduct.userName, "usuario");
                currentProduct.rmaDayesss = sql.getDayes(currentProduct.rmaDayesss, currentProduct.rmaId);
                long dateRMA = Long.parseLong(currentProduct.rmaDayesss);

                currentProduct.warrentyVoidDate = LocalDate.now().plusDays(dateRMA).toString();

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cbSupplyer(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from proveedor");
            rs = pst.executeQuery();
            while (rs.next()) {
                currentProduct.supplyerList = rs.getString(2);
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CurrentProduct currentProduct) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update productos set ProductoId=?, ProductoNombre=?, Cantidad=?, Descripcion=?, "
                    + "ProveedorId=?, MarcaID=?, CategoriaId=?,"
                    + " UnitId=?, MonederoPrecio=?, VentaPecio=?, DevoluionId=?, Fecha=?  where Id=?");
            pst.setString(1, currentProduct.productId);
            pst.setString(2, currentProduct.productName);
            pst.setString(3, currentProduct.quantity);
            pst.setString(4, currentProduct.description);
            pst.setString(5, currentProduct.supplierId);
            pst.setString(6, currentProduct.brandId);
            pst.setString(7, currentProduct.catagoryId);
            pst.setString(8, currentProduct.unitId);
            pst.setString(9, currentProduct.pursesPrice);
            pst.setString(10, currentProduct.sellPrice);
            pst.setString(11, currentProduct.rmaId);
            pst.setString(12, currentProduct.date);
            pst.setString(13, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            rs.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Update : update correcto");
            alert.setContentText("Categoria" + "  '" + currentProduct.productId + "' " + "Actualizada correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from productos where id=?");
            pst.setString(1, currentProduct.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNotSoled(CurrentProduct currentProduct) {
        con = dbCon.geConnection();
        boolean isNotSoled = false;
        try {
            pst = con.prepareStatement("select * from venta where ProductoId=?");
            pst.setString(1, currentProduct.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Correcto");
                alert.setHeaderText("Alerta ");
                alert.setContentText("Este producto ya ha sido vendido no puedes eliminarlo");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isNotSoled;
            }
            rs.close();
            pst.close();
            con.close();
            isNotSoled = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotSoled;
    }

}
