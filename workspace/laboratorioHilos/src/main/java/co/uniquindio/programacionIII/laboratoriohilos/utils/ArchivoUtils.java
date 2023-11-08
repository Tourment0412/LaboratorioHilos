package co.uniquindio.programacionIII.laboratoriohilos.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * Clase de utilidad para la lectura y escritura de archivos
 * @author caflorezvi
 *
 */
public class ArchivoUtils {

    /**
     * Permite leer un archivo desde una ruta específica mediante Scanner
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoScanner(String ruta) throws IOException{

        ArrayList<String> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File(ruta));

        while(sc.hasNextLine()) {
            lista.add(sc.nextLine());
        }

        sc.close();

        return lista;
    }

    /**
     * Permite leer un archivo desde una ruta específica mediante BufferedReader
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoBufferedReader(String ruta) throws IOException{

        ArrayList<String> lista = new ArrayList<>();
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while( ( linea = br.readLine() )!=null ) {
            lista.add(linea);
        }

        br.close();
        fr.close();

        return lista;
    }

    /**
     * Escribe datos en un archivo de texo
     * @param ruta Ruta donde se va a crear el archivo
     * @param lista Datos que se escriben en el archivo
     * @throws IOException
     */
    public static void escribirArchivoFormatter(String ruta, List<String> lista) throws IOException{
        Formatter ft = new Formatter(ruta);
        for(String s : lista){
            ft.format(s+"%n");
        }
        ft.close();
    }

    /**
     * Escribe datos en un archivo de texo
     * @param ruta ruta Ruta donde se va a crear el archivo
     * @param lista Información a guardar en el archivo
     * @param concat True si se concatena los nuevos datos sin sobreescibir todo el archivo
     * @throws IOException
     */
    public static void escribirArchivoBufferedWriter(String ruta, List<String> lista, boolean concat) throws IOException{

        FileWriter fw = new FileWriter(ruta, concat);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String string : lista) {
            bw.write(string);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    /**
     * Serializa un objeto en disco
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws IOException
     */
    public static void serializarObjeto(String ruta, Object objeto) throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ruta));
        os.writeObject(objeto);
        os.close();
    }

    /**
     * Deserializa un objeto que está guardado en disco
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Object deserializarObjeto(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(ruta));
        Object objeto = is.readObject();
        is.close();

        return objeto;
    }

    /**
     * Serializa un objeto en un archivo en formato XML
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws FileNotFoundException
     */
    public static void serializarObjetoXML(String ruta, Object objeto) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.writeObject(objeto);
        encoder.close();
    }

    /**
     * Deserializa un objeto desde un archivo XML
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws IOException
     */
    public static Object deserializarObjetoXML(String ruta) throws IOException{
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(ruta));
        Object objeto = decoder.readObject();
        decoder.close();

        return objeto;
    }
    
    
    /* Estos metodos son propios de serializacion, deserializacion, lectura y escritura de archivos de texto plano
     * 
     */
    
    /*
    
    private final static String RUTA1 = "src/main/resources/co/uniquindio/programacionIII/propertiesManejo/data/jugadores.txt";
    
    public void escribirArchivoFormatter(List<Jugador> lista) throws IOException {
		Formatter ft = new Formatter(RUTA1);
		for (Jugador s : lista) {
			ft.format(s.getNombre() + ";" + s.getDorsal() + ";" + s.getPais() + ";" + s.getPosicion() + "%n");
		}
		ft.close();
	}
    
	public ArrayList<Jugador> leerArchivoScanner() throws IOException {

		ArrayList<String> lista = new ArrayList<>();
		Scanner sc = new Scanner(new File(RUTA1));

		while (sc.hasNextLine()) {
			lista.add(sc.nextLine());
		}

		sc.close();
		Stream<Jugador> map = lista.stream().map(t -> {
			String[] param = t.split(";");
			System.out.println(Arrays.toString(param));
			Jugador nj = Jugador.builder().nombre(param[0]).dorsal(param[1]).pais(param[2])
					.posicion(Posiciones.valueOf(param[3])).build();
			;
			return nj;
		});

		return map.collect(Collectors.toCollection(ArrayList::new));
	}
	
	//Metodos para serializar
	
	private static final String RUTA2 = "src/main/resources/co/uniquindio/programacionIII/propertiesManejo/data/salidas.dat";
	public void serializarObjeto(Object objeto) {
		// Se crea un objecto de flujo de salida que recibe como parametro un archivo
		// con flujo de salida
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA2))) {
			// el objeto de flujo se encarga de escribir el objeto
			oos.writeObject(objeto);
			// Se cierra la "conexion" o flujo
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// No inmplemente aca directamente el try catch lo que siginifica que los
		// metodos que lo implementen
		// lo deberan de hacer
	}

	// Retornaria un Object tambien pero voy a hacerlo con una lista de strings que
	// es lo que necesito
	// Se puede castear como lista directamente
	@SuppressWarnings("unchecked")
	public List<String> deserializarObjeto() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA2))) {
			Object objeto= ois.readObject();
			return (List<String>) objeto;
		} catch (ClassNotFoundException  |IOException e) {
			ArrayList<String> lista = new ArrayList<>();
			serializarObjeto(lista);
			return lista;
		}

	}*/

}
