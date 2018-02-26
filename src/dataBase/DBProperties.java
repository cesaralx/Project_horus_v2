/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;


import Horus_Vaping.NiceVaping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexi
 */
public class DBProperties {
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    /**
     *
     */
    public void mkDbProperties() {
        
        try {
            output = new FileOutputStream("database.properties");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "49679");
            properties.setProperty("db", "horus_v2");
            properties.setProperty("user", "cesar");
            properties.setProperty("password", "alexaltair360Q");
//            properties.setProperty("host", "den1.mssql6.gear.host ");
//            properties.setProperty("port", "49679");
//            properties.setProperty("db", "horusv2");
//            properties.setProperty("user", "horusv2");
//            properties.setProperty("password", "Aj6TK0XR_d9_");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NiceVaping.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NiceVaping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return
     */
    public String loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            return properties.getProperty("db");
        } catch (IOException e) {
        }
        return "";
    }
}
