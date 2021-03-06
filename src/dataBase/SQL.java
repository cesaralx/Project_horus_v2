/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import controller.application.employe.AddEmployeController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.application.stock.ViewSupplyerController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author alexi
 */
public class SQL {
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    /**
     *
     * @param id
     * @param userName
     * @param fullName
     * @param emailAddress
     * @param contactNumber
     * @param salary
     * @param address
     * @param password
     * @param status
     * @param date
     * @param crratorId
     * @param imagePath
     */
    public void registration(String id,String userName,String fullName,
            String emailAddress,String contactNumber,String salary,
            String address,String password,int status,
            String date,String crratorId,String imagePath ){
        con = dbCon.geConnection();
        
        try {
            pst = con.prepareStatement("insert into usuario values(?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, userName);
            pst.setString(3, fullName);
            pst.setString(4, emailAddress);
            pst.setString(5, contactNumber);
            pst.setString(6, salary);
            pst.setString(7, address);
            pst.setString(8, password);
            pst.setString(9, "1");
            if(imagePath!=null){
                InputStream is;
                try {
                    is = new FileInputStream(new File(imagePath));
                    pst.setBlob(10, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AddEmployeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }else{
                pst.setBlob(10, (Blob) null);
            }
            
            pst.setString(11, LocalDate.now().toString());
            pst.setString(12, id);
            
            pst.executeUpdate();
            pst.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param id
     */
    public void userPermissionUpdate(int id){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement(null);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param usrName
     */
    public void basicPermission(String usrName){
        DBConnection dbc = new DBConnection();
        con = dbc.geConnection();

        try {
            pst = con.prepareStatement("Select UsuarioId FROM usuario where UsrName=?");
            pst.setString(1, usrName);
            rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into userpermission (AddProducto,AddProveedor,AddMarca,"
                        + "AddCategoria,AddUnit,AddCliente,UpFechaProductp,UpFechaProveedor,UpFechaMarca,UpFechaCatagoria,"
                        + "UpFechaUnit,UpFechaCliente,ManejoDevolucion,VentaProducto,ProveerDescuento,"
                        + "ManejoEmpleados,ManejoEmpresa,CambiarPassword,UsuarioId ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, 1);
                pst.setInt(2, 1);
                pst.setInt(3, 1);
                pst.setInt(4, 1);
                pst.setInt(5, 1);
                pst.setInt(6, 1);
                pst.setInt(7, 1);
                pst.setInt(8, 1);
                pst.setInt(9, 1);
                pst.setInt(10, 1);
                pst.setInt(11, 1);
                pst.setInt(12, 1);
                pst.setInt(13, 1);
                pst.setInt(14, 1);
                pst.setInt(15, 1);
                pst.setInt(16, 1);
                pst.setInt(17, 1);
                pst.setInt(18, 1);
                pst.setInt(19, rs.getInt("UsuarioId"));

                pst.executeUpdate();
            }con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param creatorId
     * @param creatorName
     */
    public void creatorNameFindar( String creatorId, Label creatorName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from usuario where UsuarioId=?");
            pst.setString(1, creatorId);
            rs = pst.executeQuery();
            while (rs.next()) {
                creatorName.setText(rs.getString(2));
            }con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ViewSupplyerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @param name
     * @param tableName
     * @return
     */
    public String getName(String id, String name, String tableName){
        String campo = tableName;
        if (tableName.equals("marcas")) {
            campo = "marca";  
        }
        if (tableName.equals("devolucion"))
        {
            campo = "Devoluion";
        }
        if (tableName.equals("productos")) {
            campo = "";
            
        }

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+tableName+" where "+campo+"Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(2);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    /**
     *
     * @param name
     * @param id
     * @param tableName
     * @param fieldName
     * @return
     */
    public String getIdNo( String name,String id, String tableName,String fieldName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+tableName+" where "+fieldName+" =?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    
    /**
     *
     * @param supplyerId
     * @param brandId
     * @param brandName
     * @return
     */
    public String getBrandID(String supplyerId,String brandId,String brandName){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from marcas where ProveedorId=? and MarcaNombre=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandName);
            rs  = pst.executeQuery();
            while(rs.next()){
                brandId = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brandId;
    }
    
    /**
     *
     * @param supplyerId
     * @param brandId
     * @param catagoryId
     * @param catagoryName
     * @return
     */
    public String getCatagoryId(String supplyerId,String brandId,String catagoryId,String catagoryName){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from categoria where ProveedorId=? and MarcaId=? and CategoriaNombre=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandId);
            pst.setString(3, catagoryName);
            rs  = pst.executeQuery();
            while(rs.next()){
                catagoryId = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catagoryId;
    }
    
    /**
     *
     * @param rmaDayes
     * @param id
     * @return
     */
    public String getDayes(String rmaDayes, String id){
        con = dbCon.geConnection();;
        try {
            pst = con.prepareStatement("select * from devolucion where DevoluionId=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                rmaDayes = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rmaDayes;
    }

    
}
