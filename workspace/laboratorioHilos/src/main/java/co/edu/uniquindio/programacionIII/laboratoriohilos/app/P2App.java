package co.edu.uniquindio.programacionIII.laboratoriohilos.app;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import co.edu.uniquindio.programacionIII.laboratoriohilos.p2.model.Principal;
import co.uniquindio.programacionIII.laboratoriohilos.utils.ArchivoUtils;

public class P2App {
	private static ArrayList<String> datos = new ArrayList<>();

	public static void main(String[] args) {

		//llenarRegistros();
		Principal principal = new Principal("4199999");
		principal.realizarBusquedaSecuencial();
		int numHilos= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de hilos de ejecucion: "));
		principal.realizarBusquedaHilos(numHilos);

	}

	public static void llenarRegistros() {
		for (int i = 0; i < 5000000; i++) {
			datos.add("nombre "+(i + 1) + " cedula "+(i+1)+" color "+(i+1)+" ciudad "+(i+1)+" barrio "+(i+1)+" casa "+(i+1));
		}
		try {
			ArchivoUtils.escribirArchivoBufferedWriter("src/main/resources/cliente.txt", datos, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
