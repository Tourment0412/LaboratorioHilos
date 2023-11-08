package co.edu.uniquindio.programacionIII.laboratoriohilos.p2.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class BusquedaHilo extends Thread {


   private final ArrayList<String> clientes;
   private final String cadenaBuscada;
   private final int posInicial;
   private final int posFinal;
   @Getter @Setter
   private Observador observador;


   @Getter @Setter
   private boolean vivo;


   public BusquedaHilo(ArrayList<String> clientes, String cadenaBuscada, int posInicial, int posFinal) {
       this.clientes = clientes;
       this.cadenaBuscada = cadenaBuscada;
       this.posInicial = posInicial;
       this.posFinal = posFinal;
       this.vivo = true;
   }


   @Override
   public void run() {


       boolean encontrado = false;
       long tiempoInicial = System.currentTimeMillis();
       int iteraciones = 0;


       for (int i = posInicial; i < posFinal && vivo; i++){
           if(clientes.get(i).contains(cadenaBuscada)){
               encontrado = true;
               //Como ya encontró el valor buscado se finaliza el for haciendo la variable false
               //Se notifica al observador que ya encontró el valor buscado
               observador.notificar();
               vivo = false;
           }
           iteraciones++;
       }


       long tiempoFinal = System.currentTimeMillis();
       String mensaje = encontrado ? "lo encontré" : "no lo encontré";


       System.out.println("Tiempo total gastado en la búsqueda: "+(tiempoFinal-tiempoInicial)+" segundos pero "+mensaje+". Hice "+iteraciones+" iteraciones");


   }


}

