package LogFile;
import java.io.IOException;

public class listaLog {

    int tam;
    modeloLog inicio, fin, anterior;
    int pos;
    
    public void inicializacion() {
        this.inicio = null;
        this.anterior = null;
        this.fin = null;
        this.tam = 0;
    }

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
        
}
