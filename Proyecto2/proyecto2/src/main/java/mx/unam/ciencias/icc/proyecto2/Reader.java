package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to read lines from a list of file paths.
 */
public class Reader {
    
    /**
     * Reads lines from files and returns a sorted list.
     * @param args The list of file paths.
     * @return A sorted list of lines from the input files.
     */
    public static Lista<Line> reader(Lista<String> args) {
	Lista<Line> list = new Lista<>();

	// For each used to iterate through each file path provided.
	for (String arg : args) {
	    try (BufferedReader br = new BufferedReader(new FileReader(arg))) {
		String line;
		
		/**
		 * Reads lines from the current file until
		 * there are no more lines.
		 */
		while ((line = br.readLine()) != null) {
		    // Creates a Line object from the read line.
		    Line objLine = new Line(line);
		    // Adds the Line object to the list.
		    list.agregaFinal(objLine);
		}
	    } catch (IOException b) {
		/**
		 * Prints the stack trace if an IOException
		 * (from the try used)occurs while reading the file.
		 */
		b.printStackTrace();
		return null;
	    }
	}

	// Sorts the list of Line objects and returns it.
	return Sorting.sort(list);
    }
}
