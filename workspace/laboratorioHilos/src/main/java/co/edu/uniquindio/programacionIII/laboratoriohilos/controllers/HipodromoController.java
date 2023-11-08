package co.edu.uniquindio.programacionIII.laboratoriohilos.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacionIII.laboratoriohilos.app.App;
import co.edu.uniquindio.programacionIII.laboratoriohilos.p2.model.Observador;
import co.edu.uniquindio.programacionIII.laboratoriohilos.p3.model.CajaHilo;
import javafx.fxml.FXML;

public class HipodromoController implements Initializable, Observador {
	@FXML
	private Pane panel;
	private ArrayList<CajaHilo> cajas;
	
	private String ruta= "/co/edu/uniquindio/programacionIII/laboratorioHilos/images/horse2.png";
	
	

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.cajas = new ArrayList<>();

		// Se crean dos Rectangles (CajaHilo) con los valores iniciales: índice,
		// posición en X, posición en Y, y color de fondo
		cajas.add(new CajaHilo(1, 0, 50, ruta));
		cajas.add(new CajaHilo(2, 0, 200, ruta));
		cajas.add(new CajaHilo(3, 0, 350, ruta));
		cajas.add(new CajaHilo(4, 0, 500, ruta));

		for (CajaHilo cajaHilo : cajas) {
			// Se agregan los Rectangles al panel de la ventana
			panel.getChildren().add(cajaHilo);

		}
	}

	public void ejecutarTarea(){
	       //Al dar click al botón se inician los hilos, por lo tanto inicia el movimiento de las cajas
	       for(CajaHilo cajaHilo : cajas){
	    	   cajaHilo.setObserver(this);
	           Thread hilo = new Thread(cajaHilo);
	           hilo.setDaemon(true);
	           hilo.start();
	       }
	   }

	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		for(CajaHilo cajaHilo: cajas) {
			cajaHilo.setAnimationRunning(false);
		}

	}

}