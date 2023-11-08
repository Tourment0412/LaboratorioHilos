package co.edu.uniquindio.programacionIII.laboratoriohilos.p3.model;

import java.util.Random;

import co.edu.uniquindio.programacionIII.laboratoriohilos.p2.model.Observador;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class CajaHilo extends ImageView implements Runnable{


	   @Setter
	   private boolean animationRunning;
	   private final int pos;
	   
	   @Getter @Setter
	   private Observador observer;

	   public CajaHilo(int pos, double x, double y, String ruta){
	       //Se llama al constructor de Rectangle, tiene posición x, posición y, ancho y alto
	       this.setImage(new Image(this.getClass().getResourceAsStream(ruta)));
	       this.setFitHeight(100);
	       this.setFitWidth(100);
	       this.setX(x);
	       this.setY(y);


	       //Un simple número que diferencia al hilo de los demás hilos
	       this.pos = pos;


	       //Se le da un color de fondo al Rectangle
	      


	       //Esta variable controla la ejecución del hilo
	       this.animationRunning = true;
	   }


	   @Override
	   public void run() {

		   int velocidad= new  Random().nextInt(10)+3;
	       while (animationRunning) {
	           Platform.runLater(this::moverCaja);
	           if(this.getX()+this.getFitWidth()>300) {
	        	   //Preguntar si esto si es asi
	        	    velocidad= new  Random().nextInt(10)+3;
		       }
	           try {
	               //Ajusta la velocidad del movimiento
	        	   
	        	   //Pero esto lo cambia todo el rato hagamoslo que sea constante
	               Thread.sleep(velocidad);
	           } catch (InterruptedException e) {
	               e.printStackTrace();
	           }
	       }
	   }


	   private void moverCaja(){


	       double movementDelta = 2; // Cantidad de movimiento en píxeles


	       if (this.getX() + this.getFitWidth() > 600) { // Se detiene al llegar al borde derecho
	    	   
	    	   
	    	   observer.notificar();
	           animationRunning = false;
	           Alert alerta= new Alert(AlertType.CONFIRMATION, "");
	           alerta.setTitle("!Hay Ganador!");
	           alerta.setContentText("El caballo ganador es el: " +pos+" !Felicitaciones!");
	           alerta.show();
	           
	           return;
	       }
	       
	       


	       this.setX(this.getX() + movementDelta); // Se actualiza su posición en el eje x
	   }


	}

