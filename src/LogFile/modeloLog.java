/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * clase que ayuda a ordenar el logfile
 * @author alexi
 */
package LogFile;
 
import java.io.*;
import java.util.*;

/**
 *
 * @author alexi
 */
public class modeloLog {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
 Scanner leer = new Scanner (System.in); 

    /**
     *
     */
    public String lineaLog;

    /**
     *
     */
    public modeloLog siguiente;

    /**
     *
     */
    public modeloLog anterior;

    /**
     *
     * @return
     */
    public String getLineaLog() {
        return lineaLog;
    }

    /**
     *
     * @param lineaLog
     */
    public void setLineaLog(String lineaLog) {
        this.lineaLog = lineaLog;
    }

    /**
     *
     * @return
     */
    public modeloLog getSiguiente() {
        return siguiente;
    }

    /**
     *
     * @param siguiente
     */
    public void setSiguiente(modeloLog siguiente) {
        this.siguiente = siguiente;
    }

    /**
     *
     * @return
     */
    public modeloLog getAnterior() {
        return anterior;
    }

    /**
     *
     * @param anterior
     */
    public void setAnterior(modeloLog anterior) {
        this.anterior = anterior;
    }

    /**
     *
     */
    public void imprimir() {
        System.out.println(getLineaLog()+" \n");
//        System.out.println(getTipo()+"\n");
    }
    
    /**
     *
     * @return
     */
    public String imprimirTA() {
        return getLineaLog();
//        System.out.println(getTipo()+"\n");
    }
    
    /**
     * lee el logfile y obtiene el tipo de mensaje
     * @return
     */
    public String getTipo(){
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part3;   
    }

    /**
    * lee el logfile y obtiene la hora del mensaje
     * @return
     */
    public String getHora() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part2;
    }
    
    /**
     * lee el logfile y obtiene el mensaje
     * @return
     */
    public String getAccion() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part4;
    }

    /**
     * lee el logfile y guarda la fecha
     * @return
     */
    public String getFecha() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part1;
    }
    
    /**
     * lee el logfile y guarda el usuario
     * @return
     */
    public String getUsuario() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        String part5 = parts[4]; // usuario
        return part5;
    }
    
    
}

