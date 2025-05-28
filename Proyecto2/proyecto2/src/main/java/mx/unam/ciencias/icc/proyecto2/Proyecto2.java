package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;


/**
 * Proyecto 2: Lexicographic sorter.
 * Main class in charge of reading provided arguments and using the
 * corresponding methods, including handling shell flags
 * for java -jar execution.
 **/
public class Proyecto2 {

/**
 * Main method to execute the sorting functionality
 * based on provided arguments.
 * @param args Command line arguments.
 **/
    public static void main(String[] args) {
	// Variables to manage program behavior.
	boolean reverse = false; // Flag to indicate reverse sorting.
	boolean standardIn = false; // Flag to indicate standard input.
        String outputFile = null; // Path for the output file.
	Lista<String> inputFile = new Lista<>(); // List of input files.

	 // Check if there are no command line arguments.
        if (args.length == 0) {
	    standardIn = true; // Setting standard input flag.
	} else {
	    // Parse the command line arguments.
	    for (int i = 0; i < args.length; i++) {
		if (args[i].equals("-r")) {
		    reverse = true; // Setting reverse flag.
		} else if (args[i].equals("-o")) {
		    if (i + 1 < args.length) {
			// Setting output file path.
			outputFile = args[i + 1];
			i++;
		    } 
		} else {
		     // We add input files to the list.
		    inputFile.agregaFinal(args[i]);
		}
	    }
	}

	// List were we're going to store lines of text.
	Lista<Line> lines = new Lista<>();

	// Check if no input files were provided.
	if (inputFile.getLongitud() == 0) {
	    // Reads standard input.
	    lines = Writtening.readIn(inputFile, true);
	} else {
	    // Reads input files.
	    lines = Writtening.readIn(inputFile, false);
	}

	// Sort lines based on reverse order flag.
	if (reverse) {
	    lines = Sorting.sortRev(lines);
	} else {
	    lines = Sorting.sort(lines);
	}

	/**
	 * If an output file is specified, write lines to it;
	 * otherwise, print to standard output.
	 **/
	if (outputFile != null) {
	    Writtening.write(lines, outputFile);
	} else {
	    // Now we print lines to standard output.
	    for (int k = 0; k < lines.getLongitud(); k++) {
		System.out.println(lines.get(k).getLine());
	    }
	}
    }
}
