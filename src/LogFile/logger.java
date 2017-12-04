/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogFile;


import LogFile.listaLog;
import controller.ApplicationController;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;
import java.util.stream.Stream;
import media.userNameMedia;

/**
 *
 * @author alexi
 */
public class logger {
    listaLog listLog = new listaLog();
    Logger logger = Logger.getLogger(logger.class.getName());
    FileHandler fh;
    
    private userNameMedia usrNameMedia;
    private String user;

    public logger()  {
        
        System.setProperty("java.util.logging.SimpleFormatter.format", 
            "%1$tY-%1$tm-%1$td, %1$tH:%1$tM:%1$tS, %4$-6s, %5$s%6$s%n");
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler( System.getProperty("user.dir")+ "/"+logger.class.getName()+".log");
        } catch (IOException ex) {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(logger.class.getName()).log(Level.SEVERE, null, ex);
        }
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
    }
    
    private void checkUser() throws IOException{
        try{
            checkUsrFile();
        }catch (NullPointerException n) {
            user=null;
        }
        if (user == null || "".equals(user)){
            user="NULL";
        }
    }
    
    private void checkUsrFile() throws FileNotFoundException, IOException{
    try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/usr.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                user = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void wirteLogInfo(String mensaje) throws IOException {
        try {
            checkUser();
            // aqui se escribe el string que le pasemos como parametro
            logger.info(String.format("%s,%s", mensaje,user ));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    
    public void wirteLogWarning(String mensaje) throws IOException {
        try {
            // aqui se escribe el string que le pasemos como parametro
            logger.warning(String.format("%s,", mensaje));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    
    public void readFile() throws FileNotFoundException, IOException {

        listLog.inicializacion();
        boolean isrun = false;

        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + logger.class.getName() + ".log"))) {
            for (String line; (line = br.readLine()) != null;) {
                // process the line.
                if (!isrun) {
                    listLog.ins_vacia(line);
                    isrun = true;
                } else {
                    listLog.ins_inicio(line);
                }
            }
            // line is not visible here.
            listLog.visualizar();
        }
    }
    
    public void orderByTipo() throws FileNotFoundException, IOException {

        listLog.bubbleSortTipo();
        System.out.println("Despues de ordenar");
        listLog.visualizar();
        listLog.generarLogOrdenado();
        // refresh info del log
    }
    
        public void orderByQuickSort() throws FileNotFoundException, IOException {
        listLog.quickSort();
        System.out.println("Despues de ordenar");
        listLog.visualizar();
        listLog.generarLogOrdenado();
        // refresh info del log
    }
        

}
