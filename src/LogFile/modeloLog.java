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
        System.out.println("El nombre es: " + getLineaLog()+" \n");
    }
}

