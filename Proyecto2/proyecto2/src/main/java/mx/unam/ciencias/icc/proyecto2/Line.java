package mx.unam.ciencias.icc.proyecto2;

import java.text.Normalizer;

/**
 * Class for representing a line of text (normalizing it)
 * and provide methods for handling it.
 */
  public class Line {
      
    // The original readed line of text.
    private String line; 
    // The cleaned and formatted version of the line.
    private String cleanedLine; 

/**
 * Constructor that initializes a Line object with the given line of text.
 * @param line The original line of text to be stored.
 */
    public Line(String line) {
	this.line = line;
	// Calls the format method to clean and format the line.
	this.cleanedLine = format();
    }

    public String getLine() {
	return line;
    }

    public String getCleanedLine() {
	return cleanedLine;
    }

/**
 * Formats the line by removing diacritics, converting to lowercase,
 * and removing non-alphabetic characters.
 * @return The cleaned and formatted line.
 */
    public String format() {
    // Normalizes the line to remove diacritics, then converts to lowercase.
	String cleaning = Normalizer.normalize(line, Normalizer.Form.NFD);
	// Removes all non-alphabetic characters from the line.
	    cleaning = cleaning.toLowerCase().replaceAll("[^a-z]", "");
	// Returns the cleaned and formatted line.
	return cleaning;
    }
}
