package co.edu.uniquindio.programacionIII.laboratoriohilos.p2.model;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.programacionIII.laboratoriohilos.utils.ArchivoUtils;

public class Principal implements Observador {

	private BusquedaHilo[] hilos;
	private ArrayList<String> clientes;
	private String valorBuscado;

	public Principal(String valorBuscado) {
		this.clientes = new ArrayList<>();
		this.valorBuscado = valorBuscado;
		try {
			// Se leen los datos del archivo txt de clientes
			this.clientes = ArchivoUtils.leerArchivoBufferedReader("src/main/resources/cliente.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void realizarBusquedaSecuencial() {
		boolean encontrado = false;
		int i;
		long tiempoInicial = System.currentTimeMillis();

		for (i = 0; i < clientes.size() && !encontrado; i++) {
			if (clientes.get(i).contains(valorBuscado)) {
				encontrado = true;
			}
		}

		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Tiempo total gastado en la búsqueda: " + (tiempoFinal - tiempoInicial) / 1000
				+ " segundos. Hice " + i + " iteraciones");
	}
	
	/*
	 * Supongo que para el problema de los rangos dinamicos la idea es que el archivo siga con
	 * el mismo numero de lineas es decir 2 millones como constante
	 * 
	 * Vamos a hacer que el metodo siguiente cree los hilos automaticamente y les asigne un rango
	 */

	public void realizarBusquedaHilos(int numHilos) {
		
		hilos = new BusquedaHilo[numHilos];
		//Se puede volver dinamico tambien en su tope si añadimos el limite del archivo como parametro
		//O con clientes.size lo voy a hacer asi mejor
		int division= clientes.size()/numHilos;
		int cont=0;
		for(int i=0;i<numHilos;i++) {
			if(i!=(numHilos-1)) {
				hilos[i]= new BusquedaHilo(clientes, valorBuscado, cont, cont+division);
				cont+=division;
			}
			else {
				hilos[i]= new BusquedaHilo(clientes, valorBuscado, cont, clientes.size());
			}
		}

		for (BusquedaHilo h : hilos) {
			// Se le asigna el observador actual a cada uno de los hilos creados
			h.setObservador(this);
			h.start();
		}

	}

	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		// Cuando se invoca este método es porque uno de los hilos encontró el valor
		// buscado, entonces se detienen todos los hilos para que no sigan buscando
		for (BusquedaHilo h : hilos) {
			h.setVivo(false);
		}

	}

}
