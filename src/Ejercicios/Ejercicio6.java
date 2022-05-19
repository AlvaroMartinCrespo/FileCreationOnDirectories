package Ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio6 {

	static final String PATRON = "[A-Z| ]* [1-9][D|B][A][M|C]";
	static final String PATRONNOMBRE = " [1-9][D|B][A][M|C]";
	static final String PATRONCLASE = "[A-Z| ]* ";

	public static void main(String[] args) {

		String nombreArchivo;

		nombreArchivo = introduceCadenas();

		leerArchivo(nombreArchivo);

	}

	/**
	 * Se el pasa el nombre del archivo que tiene que buscar por paramtros, crea los
	 * directorio y los archivos dentro de los directorios
	 * 
	 * @param nombreArchivo
	 */
	public static void leerArchivo(String nombreArchivo) {

		String linea;
		String[] nombreClase;
		String[] nombreAlumno;
		int indexClase, indexAlumno;
		String direccion = "C:\\Users\\alvar\\Downloads\\WorkspaceRepasoExamenProgramacionConjuntos\\Ficheros\\";

		try (FileReader reader = new FileReader(nombreArchivo + ".txt");
				BufferedReader filtro = new BufferedReader(reader);) {

			linea = filtro.readLine();

			while (linea != null) {

				if (comprobarPatron(linea)) {

					nombreClase = linea.split(PATRONCLASE);
					indexClase = (nombreClase.length - 1);

					crearDirectorio(nombreClase[indexClase]);

					nombreAlumno = linea.split(PATRONNOMBRE);

					indexAlumno = (nombreAlumno.length - 1);

					crearArchivo(nombreAlumno[indexAlumno] + ".txt", direccion + nombreClase[indexClase]);

				}

				linea = filtro.readLine();

			}

		} catch (FileNotFoundException e) {

			System.out.println("Error al encontrar el archivo");

		} catch (IOException e1) {

			System.out.println("Error al leer el archivo");

		}

	}

	/**
	 * Crea un fichero con el nombre que se le pase por parametros
	 * 
	 * @param nombreArchivo
	 */
	public static void crearArchivo(String nombreArchivo, String direccion) {

		File archivo = new File(direccion, nombreArchivo);

		try {

			archivo.createNewFile();

		} catch (IOException e) {

			System.out.println("Error al crear el archivo");

		}

	}

	/**
	 * Crea un directorio con el nombre que se le pase por paramentros
	 * 
	 * @param nombreDirectorio
	 */
	public static void crearDirectorio(String nombreDirectorio) {

		File directorio = new File(nombreDirectorio);

		directorio.mkdir();

	}

	/**
	 * Pide un String y lo devuelve
	 * 
	 * @return
	 */
	public static String introduceCadenas() {

		Scanner entrada = new Scanner(System.in);

		String cadena;

		System.out.println("Introduca el nombre del fichero:");
		cadena = entrada.nextLine();

		return cadena;

	}

	/**
	 * Comprueba si el patron es valido o no y devuelve un booleano
	 * 
	 * @param cadena
	 * @param patron
	 * @return
	 */
	public static boolean comprobarPatron(String cadena) {

		if (cadena.matches(PATRON)) {
			return true;
		} else {
			return false;
		}

	}

}
