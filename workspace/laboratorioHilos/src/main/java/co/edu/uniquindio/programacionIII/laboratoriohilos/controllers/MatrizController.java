package co.edu.uniquindio.programacionIII.laboratoriohilos.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MatrizController extends Thread implements Initializable {

	@FXML
	private Pane panel;
	private Button[][] matriz1, matriz2;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		// Se inicializan las dos matrices, una al lado de la otra
		matriz1 = crearMatrizBotones(4, 0);
		matriz2 = crearMatrizBotones(4, 250);
	}

	public void iniciarLlenado() {
		// Se llena la primer matriz al dar click en el botón de la ventana

		new Thread(new Runnable() {
			@Override
			public void run() {
				llenarMatriz1(matriz1);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				llenarMatrizDiagonal(matriz2);
				
			}
		}).start();
	}

	private void llenarMatriz1(Button[][] m) {

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				// En cada iteración se actualiza el color de fondo a rojo
				m[i][j].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}
	
	private void llenarMatrizDiagonal(Button[][] m) {
		int alto= m.length-1;
		int ancho=m[0].length-1;
		int total= alto+ancho;
		for(int k=0;k<=total;k++) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if(i+j==k) {
					m[i][j].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		}
		
	}

	private Button[][] crearMatrizBotones(int n, int xInicial) {

		Button[][] botones = new Button[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				Button boton = new Button();
				boton.setPrefWidth(50);
				boton.setPrefHeight(50);

				// En cada iteración se actualiza la posición del botón en el eje X, X cambia
				// cuando cambia el valor de j. Ya que x representa las columnas (horizontal).
				boton.setLayoutX(xInicial + 50 * j + 2);

				// En cada iteración se actualiza la posición del botón en el eje Y, Y cambia
				// cuando cambia el valor de i. Ya que y representa las fila (vertical).
				boton.setLayoutY(50 * i + 2);

				botones[i][j] = boton;

				// El botón se añade al panel para poder visualizarlo
				panel.getChildren().add(boton);

			}
		}

		return botones;

	}

}
