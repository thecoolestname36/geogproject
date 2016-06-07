package geogproject.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class will be used by the InputThread class to parse text.
 * @author Brad
 */
public class InputParser {
    
    private File[] inputFiles;
    private File file;
    
    /**
     * Default Constructor
     */
    public InputParser() {
        this.inputFiles = new File[0];
    }
    
    /**
     * Primarily used when adding this class to a thread
     * @param f File name
     */
    public InputParser(File f) {
        this.file = f;
    }

    /**
     * Simply converts the file character by character into a string.
     * @param f File
     * @return 
     */
    public String fileToString(File f) {
        String s = "";
        try {
            FileReader reader = new FileReader(f.toString());
            try {
                while(reader.ready()) {
                    s += (char) reader.read();
                }
            } catch(IOException e) {
                System.out.println("Reader Not Ready: " + Arrays.toString(e.getStackTrace()));
            }
            try {
                reader.close();
            } catch(IOException e) {
                System.out.println("Reader Close Failed: " + Arrays.toString(e.getStackTrace()));
            }
        } catch(FileNotFoundException e) {
            System.out.println("File Read Error: " + Arrays.toString(e.getStackTrace()));
        }
        return s;
    }
    
    /**
     * Remove unnecessary text from the input file.
     * @param s String to minimize
     * @return 
     */
    public String minimizeString(String s) {
        String newS = "";
        String entry = "";
        boolean writeEntry = false;
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            
            if(c == '\n') {
                c = ' ';
            }
            if(entry.endsWith(" ") && c == ' ') {
                
            } else {
                entry += c;
            }
            
            // Start Writing
            if(entry.endsWith("METAR") && !writeEntry) {
                writeEntry = true;
                entry = "";
                i++;
            }
            
            // End writing
            if(writeEntry && entry.endsWith("=")) {
                newS += entry.substring(0, entry.length()-2) + '\n';
                entry = "";
                writeEntry = false;
            }
            
            // Do not write NIL statement
            if(writeEntry && entry.contains("NIL")) {
                entry = "";
                writeEntry = false;
            }
        }
        return newS;
    }
    
    /**
     * List must be minimized before sent here
     * @param s
     * @return 
     */
    public LinkedList asList(String s) {
        LinkedList l = new LinkedList<>();
        String entry = "";
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            entry += c;
            
            if(c=='\n') {
                l.addLast(new Metar(entry.substring(0, entry.indexOf('\n'))));
                entry = "";
            }
        }
        return l;
    }
}
