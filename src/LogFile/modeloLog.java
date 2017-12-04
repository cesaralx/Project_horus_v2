/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexi
 */
package LogFile;
 
import java.io.*;
import java.util.*;

public class modeloLog {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
 Scanner leer = new Scanner (System.in); 
    public String lineaLog;
    public modeloLog siguiente;
    public modeloLog anterior;

    public String getLineaLog() {
        return lineaLog;
    }

    public void setLineaLog(String lineaLog) {
        this.lineaLog = lineaLog;
    }

    public modeloLog getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(modeloLog siguiente) {
        this.siguiente = siguiente;
    }

    public modeloLog getAnterior() {
        return anterior;
    }

    public void setAnterior(modeloLog anterior) {
        this.anterior = anterior;
    }


    public void imprimir() {
        System.out.println(getLineaLog()+" \n");
//        System.out.println(getTipo()+"\n");
    }
    
        public String imprimirTA() {
        return getLineaLog();
//        System.out.println(getTipo()+"\n");
    }
    
    public String getTipo(){
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part3;   
    }

    public String getHora() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part2;
    }
    
    public String getAccion() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part4;
    }

    public String getFecha() {
        String[] parts = getLineaLog().split(",");
        String part1 = parts[0]; // fecha
        String part2 = parts[1]; // hora
        String part3 = parts[2]; // tipo
        String part4 = parts[3]; // accion
        return part1;
    }
    
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

