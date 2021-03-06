/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexi
 */
public class DBConnection {

    Properties properties = new Properties();
    InputStream inputStream;

    /**
     *se encarga de la conexion a base de datos
     */
    public Connection con;

    String url;
    String user;
    String pass;
    String db;
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

    /**
     *carga las propiedades por default
     */
    public void loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            user = properties.getProperty("user");
            pass = properties.getProperty("password");
            db = properties.getProperty("db");
            url = "jdbc:sqlserver://" + properties.getProperty("host") + ":" + properties.getProperty("port") + ";databaseName="+db+";user="+user+";password="+pass;
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }

    /**
     *
     * @return el objeto de conexion a SQL
     * @throws SQLException
     */
    public Connection mkDataBase() throws SQLException {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    /**
     *
     * @return el objeto de conexion a SQL
     */
    public Connection geConnection() {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
