package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;

/**
 * Class handling input/output operations for Line objects.
 */
public class Writtening {

    /**
     * Writes Line objects to a file.
     * @param lines The list of Line objects to write.
     * @param outputFile The path of the output file to write the lines to.
     */
    public static void write(Lista<Line> lines, String outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
	    // Iterates through each Line object in the list.
            for (int i = 0; i < lines.getLongitud(); i++) {
		// Writes the Line's content to the output file.
                writer.write(lines.get(i).getLine());
		// Writes a new line character after each Line's content.
                writer.newLine();
            }
	    // Closes the writer after writing all lines.
            writer.close();
        } catch (IOException e) {
	    // Prints the stack trace if an IOException occurs.
            e.printStackTrace();
        }
    }

    /**
     * Reads lines from standard input or files and creates Line objects.
     * @param inputFile   The list of file paths or standard input flag.
     * @param standardIn  Flag indicating whether standard input is used.
     * @return A list of Line objects created from input sources.
     */
    public static Lista<Line> readIn(Lista<String> inputFile, boolean standardIn) {
	Lista<Line> lines = new Lista<>();

	if (standardIn) {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String line;
	    try {
		/**
		 * Reads lines from standard input until
		 * there are no more lines.
		 */
                while ((line = br.readLine()) != null) {
		    // Creates a Line object from the read line.
                    Line objLine = new Line(line);
		    // Adds the Line object to the list.
                    lines.agregaFinal(objLine);
                }
		
            } catch (IOException d) {
		// Prints the stack trace if an IOException occurs.
                d.printStackTrace();
            }
	} else {
	    /**
	     * Reads input files and creates Line objects
	     * from the file content.
	     */
            lines = Reader.reader(inputFile);
        }
	// Returns the list of Line objects created from input sources.
        return lines;
    }
}
