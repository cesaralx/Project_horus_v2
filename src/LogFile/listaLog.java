package LogFile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;

/**
 * esta clase se encarga de el manejo del logfile
 * @author alexi
 */
public class listaLog {

    int tam;
    modeloLog inicio, fin, anterior;
    int pos;
    
    /**
     * inicia el logile
     */
    public void inicializacion() {
        this.inicio = null;
        this.anterior = null;
        this.fin = null;
        this.tam = 0;
    }

    /**
     * este metodo busca si la lista esta vacia
     * @param log
     * @throws IOException
     */
    public void ins_vacia(String log) throws IOException {
        if (tam != 0) {
            System.out.println("\n La lista tiene datos...");
        } else {
            modeloLog nuevo;
            nuevo = new modeloLog();
            nuevo.setLineaLog(log);
            nuevo.siguiente = null;
            nuevo.anterior = null;
            inicio = nuevo;
            fin = nuevo;
            tam++;
        }
    }
    
    /**
     *
     * @param log
     * @throws IOException
     */
    public void ins_inicio(String log) throws IOException {
        if (tam == 0) {
            System.out.println("\n La lista esta vacia...");
        } else {
            modeloLog nuevo;
            nuevo = new modeloLog();
            nuevo.setLineaLog(log);
            nuevo.siguiente = inicio;
            inicio.anterior = nuevo;
            nuevo.anterior = null;
            inicio = nuevo;
            tam++;
        }
    }
//
//    public void ins_fin() throws IOException {
//        persona actual;
//        actual = fin;
//        persona nuevo;
//        nuevo = new persona();
//        nuevo.captura();
//        actual.siguiente = nuevo;
//        nuevo.anterior = actual;
//        nuevo.siguiente = null;
//        fin = nuevo;
//        tam++;
//    }
     
//    public void ins_pos() throws IOException {
//        if (tam < 0) {
//            System.out.println("\n Posicion invalida... tamaño menor a Cero");
//        } else {
//
//            if (pos < 1 || pos > tam) {
//                System.out.println("\n Posicion invalida...");
//            } else {
//                persona actual, nuevo;
//                int i;
//                nuevo = new persona();
//                actual = inicio;
//                for (i = 1; i < pos - 1; i++) {
//                    actual = actual.siguiente;
//                }
//
//                nuevo.captura();
//                nuevo.siguiente = actual.siguiente;
//                nuevo.anterior = actual;
//                actual.siguiente = nuevo;
//                tam++;
//            }
//        }
//
//    }

    /**
     * imprime los datos del log
     * @throws IOException
     */
     
    public void visualizar() throws IOException {
        if (tam == 0) {
            System.out.println("NO HAY DATOS ALMACENADOS");
        } else {
            modeloLog actual;
            actual = inicio;
            while (actual != null) {
                actual.imprimir(); 
                actual = actual.siguiente;
            }
        }
    }
    
    /**
     * genera el logfile ordenado y lo guarda en una ubicacion
     * @throws IOException
     */
    public void generarLogOrdenado() throws IOException {
        if (tam == 0) {
            System.out.println("NO HAY DATOS ALMACENADOS");
        } else {
            modeloLog actual;
            actual = inicio;
            String text;
            PrintWriter fh;
            
            fh = new PrintWriter( System.getProperty("user.dir")+ "/logOrdenado.log");

            while (actual != null) {
               text = actual.imprimirTA(); 
               fh.append(text+"\n");
                actual = actual.siguiente;
            }
            fh.close();
        }
    }

    /**
     * visualiza el logfile invertido
     * @throws IOException
     */
    public void visualizar_invert() throws IOException {
        if (tam == 0) {
            System.out.println("NO HAY DATOS ALMACENADOS");
        } else {
            modeloLog actual;
            actual = fin;
            while (actual != null) {
                actual.imprimir(); 
                actual = actual.anterior;
            }
        }
    }
        
        
//    public static void ordenar(paciente[] arre, int tam) {
//        paciente auxiliar = new paciente();
//        for (int i = 0; i < (tam - 1); i++) {
//            for (int j = 0; j < (tam - 1); j++) {
//                if (arre[i].getCodigo() > arre[i + 1].getCodigo()) {
//                    auxiliar = arre[i];
//                    arre[i] = arre[i + 1];
//                    arre[i + 1] = auxiliar;
//                }
//
//            }
//
//        }
//
//    }
     
//    public void sup_inicio() {
//        if (tam == 0) {
//            System.out.println("\n Lista vacia");
//        } else {
//            modeloLog sup;
//            sup = inicio;
//            inicio = inicio.siguiente;
//            if (tam == 1) {
//                fin = null;
//            }
//            System.out.println("\n Eliminando a... \n");
//            sup.imprimir();
//            sup = null;
//            tam--;
//        }
//    }
     
//    public void sup_posicion() {
//        if (tam <= 1 || pos < 1 || pos >= tam) {
//            System.out.println("\n Posicion invalida");
//        } else {
//            int i;
//            modeloLog act, sup;
//            act = inicio;
//            for (i = 1; i < pos - 1; i++) {
//                act = act.siguiente;
//            }
//            sup = act.siguiente;
//            act.siguiente = sup.siguiente;
//            if (act.siguiente == null) {
//                fin = act;
//            }
//            System.out.println("\n Eliminando a... \n");
//            sup.imprimir();
//            sup = null;
//            tam--;
//        }
//    }
     
//    public void sup_fin() {
//        if (tam == 0) {
//            System.out.println("\n Lista vacia");
//        } else {
//            persona sup;
//            sup = inicio;
//
//            for (int i = 1; i < tam - 1; i++) {
//                sup = sup.siguiente;
//            }
//            System.out.println("\n Eliminando a... \n");
//            fin.imprimir();
//            sup.siguiente = null;
//            fin = sup;
//            sup = null;
//            tam--;
//        }
//    }
          
//    public void shinra_tensei() {
//        persona sup;
//        sup = inicio;
//
//        for (int i = 1; i < tam; i++) {
//            inicio = inicio.siguiente;
//            tam--;
//        }
//        inicio = null;
//        anterior = null;
//        fin = null;
//        tam = 0;
//    }
          
//    public void busqueda() {
//        if (tam == 0 || pos < 1 || pos > tam) {
//            System.out.println("\n Posicion invalida");
//        } else {
//
//            persona actual;
//            actual = inicio;
//            for (int i = 1; i <= pos - 1; i++) {
//                actual = actual.siguiente;
//            }
//            System.out.println("\n Elemento encontrado en posicion " + pos + "\n");
//            actual.imprimir();
//            actual = null;
//        }
//
//    }

    /**
     * ordena tippo burbuja el logfile
     * @throws IOException
     */
        
         public void bubbleSortTipo() throws IOException {
        logger log = new logger();
        boolean done = false;
        try {
            while (!done) {
                modeloLog actual = inicio;
                done = true;
                log.wirteLogInfo("Entro");
                while (actual != null) {
                    log.wirteLogInfo("Actual es distinto de null");
                    if (actual.siguiente != null) {
                        int compare = actual.siguiente.getTipo().compareTo(actual.getTipo());
                        if (compare > 0) {
                            swap(actual.siguiente, actual);
                            done = false;
                            log.wirteLogInfo("siguiengte es mayor a actual");
                        }
                    } else {
                        actual.siguiente = null;
                        int compare2 = actual.getTipo().compareTo(actual.anterior.getTipo());
                        if (compare2 > 0) {
                            swap(actual, actual.anterior);
                            done = true;
                        }
                    }
                    actual = actual.siguiente;
                    log.wirteLogInfo("Actual = a siguiente " + done);
                }
                log.wirteLogInfo("Salio " + done);
            }
            log.wirteLogInfo("Termino ciclo");
        } catch (NullPointerException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     *ayuda del metodo burbuja para ordenar
     */
    private void swap(modeloLog n1, modeloLog n2) throws IOException {
        logger log = new logger();
        log.wirteLogInfo("Cambiando n1: " + n1.getTipo() + " con n2: " + n2.getTipo());

        modeloLog aux = new modeloLog();
        aux.setAnterior(n1.getAnterior());
        aux.setSiguiente(n1.getSiguiente());
        aux.setLineaLog(n1.getLineaLog());

        n1.setLineaLog(n2.getLineaLog());
        n1.setAnterior(n2.getAnterior());
        n1.setSiguiente(n2.getSiguiente());

        n2.setLineaLog(aux.getLineaLog());
        n2.setAnterior(aux.getAnterior());
        n2.setSiguiente(aux.getSiguiente());
    }
    
    /**
     * ordena por metodo quickshort
     */
    public void quickSort() {
        modeloLog actual = inicio;
        modeloLog head = lastNode(actual);
        _quickSort(actual, head);
    }

/* A recursive implementation of quicksort for linked list */ 
    void _quickSort(modeloLog l,modeloLog h) { 
        if(h!=null && l!=h && l!=h.siguiente ){ 
            modeloLog temp = partition(l,h); 
            _quickSort(l,temp.anterior); 
            _quickSort(temp.siguiente,h); 
        } 
    }

     modeloLog partition(modeloLog l, modeloLog h) {
// set pivot as h element 
        String x = h.getUsuario();
// similar to i = l-1 for array implementation 
        modeloLog i = l.anterior;
// Similar to "for (int j = l; j <= h- 1; j++)" 
        for (modeloLog j = l; j != h; j = j.siguiente) {
            int compare = j.getUsuario().compareTo(x);
            if (compare < 0) {
// Similar to i++ for array 
                i = (i == null) ? l : i.siguiente;
                
                modeloLog temp = new modeloLog();
                temp.setLineaLog(i.getLineaLog());
                
                i.setLineaLog(j.getLineaLog());
                
                j.setLineaLog(temp.getLineaLog());

            }
        }
        
        i = (i == null) ? l : i.siguiente;
        modeloLog temp = new modeloLog();
        
        temp.setLineaLog(i.getLineaLog());

        i.setLineaLog(h.getLineaLog());

        h.setLineaLog(temp.getLineaLog());


        return i;
    }

// A utility function to find last node of linked list 
     modeloLog lastNode(modeloLog node){ 
         while(node.siguiente!=null) 
             node = node.siguiente; 
         return node; 
     }
        
}
