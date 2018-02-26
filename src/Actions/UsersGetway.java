/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Models.Orders;
import Models.Users;
import List.ListEmployee;
import dataBase.DBConnection;
import dataBase.DBProperties;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author alexi
 */
public class UsersGetway {

    DBConnection dbConnection = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     * guarda un nuevo usuario
     * @param users
     */
    public void save(Users users) {

        if (isUniqName(users)) {
            con = dbConnection.geConnection();
            try {
                pst = con.prepareStatement("insert into usuario(UsrName, Nombre, EmailDireccion, NumeroContacto,"
                        + "Salario, Direccion, Password, Status, UserImage, Fecha, CreatorId) values(?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, users.userName);
                pst.setString(2, users.fullName);
                pst.setString(3, users.emailAddress);
                pst.setString(4, users.contactNumber);
                pst.setDouble(5, Double.parseDouble(users.salary));
                pst.setString(6, users.address);
                pst.setString(7, users.password);
                pst.setString(8, "1");
                if (users.imagePath != null) {
                    InputStream is;
                    is = new FileInputStream(new File(users.imagePath));
                    pst.setBlob(9, is);
                } else {
                    pst.setBlob(9, (Blob) null);
                }
                pst.setString(10, LocalDate.now().toString());
                pst.setString(11, users.creatorId);
                pst.executeUpdate();
                pst.close();
                con.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto :");
                alert.setHeaderText("Correcto");
                alert.setContentText("Usuario " + users.userName + " Agregado correctamente");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error :");
                alert.setHeaderText("Error");
                alert.setContentText("Usuario " + users.userName + " no se a agregado");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * selecciona todos los usuarios
     * @param users
     */
    public void view(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from usuario");
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.userName = rs.getString(2);
                users.employeeLists.addAll(new ListEmployee(users.id, users.userName));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * selecciona todos los usuarios por id
     * @param users
     */
    public void selectedView(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareCall("select * from usuario where UsuarioId=?");
            pst.setString(1, users.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.userName = rs.getString(2);
                users.fullName = rs.getString(3);
                users.emailAddress = rs.getString(4);
                users.contactNumber = rs.getString(5);
                users.salary = rs.getString(6);
                users.address = rs.getString(7);
                users.password = rs.getString(8);
                users.status = rs.getString(9);
                users.userImage = rs.getBlob(10);
                if (users.userImage != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(users.userImage.getBytes(1, (int) users.userImage.length()));
                    users.image = new Image(byteArrayInputStream);
                } else {
                    users.image = new Image("/image/rifat.jpg");
                }
                users.date = rs.getString(11);
                users.creatorId = rs.getString(12);

            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * actualiza un uduario existente
     * @param users
     */
    public void update(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("UPDATE usuario SET Nombre=?, EmailDireccion=?,NumeroContacto=?,Salario=COALESCE(?, Salario),Direccion=?,Password=COALESCE(?, Password), Status=COALESCE(?, Status), UserImage=COALESCE(?, UserImage) WHERE UsrName=?");
            pst.setString(1, users.fullName);
            pst.setString(2, users.emailAddress);
            pst.setString(3, users.contactNumber);
            pst.setString(4, users.salary);
            pst.setString(5, users.address);
            pst.setString(6, users.password);
            pst.setString(7, users.status);
            if (users.imagePath != null) {
                InputStream is;
                is = new FileInputStream(new File(users.imagePath));
                pst.setBlob(8, is);
            } else if (users.imagePath == null) {
                pst.setBlob(8, (Blob) null);
            }
            pst.setString(9, users.userName);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito :");
            alert.setHeaderText("Actualizado !!");
            alert.setContentText("Usuario " + users.userName + " Actualizado Correctamente");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * elimina un usuario existente
     * @param users
     */
    public void delete(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("delete userpermission where UsuarioId=?");
            pst.setString(1, users.id);
            pst.executeUpdate();
            pst = con.prepareStatement("delete usuario where UsuarioId=?");
            pst.setString(1, users.id);
            pst.executeUpdate();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * busca si el usuario ya existe
     * @param users
     * @return retorna true si ya existe y false si no existe
     */
    public boolean isUniqName(Users users) {
        con = dbConnection.geConnection();
        boolean isUniqName = false;
        try {
            pst = con.prepareStatement("select * from usuario where UsrName=?");
            pst.setString(1, users.userName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR :");
                alert.setHeaderText("ERROR : Existe el nombre");
                alert.setContentText("Nombre de usuario " + users.userName + " ya en uso");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isUniqName;
            }
            rs.close();
            pst.close();
            con.close();
            isUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUniqName;
    }
}
