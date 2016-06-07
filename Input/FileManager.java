package geogproject.Input;

import java.io.*;
import java.util.Date;

/**
 * A path must be provided
 * @author Brad
 */
public class FileManager {
    
    private String path;
    private String inputDir;
    private String outputDir;
    private File[] files;
    
    public FileManager(String p) {
        this.path = new String(p);        
        this.files = new File[0];
    }
    
    public FileManager(String p, String i) {
        this.path = new String(p);     
        this.inputDir = this.path + i;
        this.files = getFilesInDir(this.inputDir);
    }
    
    public FileManager(String p, String i, String o) {
        this.path = new String(p);        
        this.inputDir = this.path + i;
        this.files = getFilesInDir(this.inputDir);
        this.outputDir = this.path + o;
        makeDir(this.outputDir);
    }
    
    public boolean makeDir(String s) {
        boolean t = !new File(s).exists();
        if(t) {
            new File(s).mkdir();
            System.out.println("Created Directory: " + s);
        } else if(!t) {
            t = false;
            System.out.println("Directory exists: " + s);
        } else {
            System.out.println("File Error: " + s);
        }
        return t;
    }
    
    /**
     * Save to output file, the output file must be 
     * @param sArr Array of output data
     * @return false if save not successful or output directory was not provided
     */
    public boolean saveOutputToFile(String[] sArr) {
        boolean b = false;
        if(this.outputDir != null && !new String("").equals(this.outputDir)) {
            Date d = new Date();
            File f = new File(this.outputDir
                    + "/Output__" 
                    + d.getHours() + "-"
                    + d.getMinutes() + "-"
                    + d.getSeconds() + "__"
                    + (d.getMonth() + 1) + "-"
                    + d.getDate() + "-"
                    + d.getYear() + ".txt"
            );
            try {
                PrintWriter w  = new PrintWriter(f.toString());
                for(String s : sArr) {
                    w.println(s);
                }
                w.close();
                b = true;
            } catch (FileNotFoundException ex) {
                System.out.println("1 Cannot write file");
            }
        }
        return b;
    }
    /**
     * Save to output file, the output file must be 
     * @param sArr Array of output data
     * @return false if save not successful or output directory was not provided
     */
    public boolean saveOutputToFileWithName(String fName, String[] sArr) {
        boolean b = false;
        if(this.outputDir != null && !new String("").equals(this.outputDir)) {
            Date d = new Date();
            File f = new File(this.outputDir
                    + "/" + fName + "__" 
                    + d.getHours() + "-"
                    + d.getMinutes() + "-"
                    + d.getSeconds() + "__"
                    + (d.getMonth() + 1) + "-"
                    + d.getDate() + "-"
                    + d.getYear() + ".txt"
            );
            try {
                PrintWriter w  = new PrintWriter(f.toString());
                for(String s : sArr) {
                    w.println(s);
                }
                w.close();
                b = true;
            } catch (FileNotFoundException ex) {
                System.out.println("1 Cannot write file");
            }
        }
        return b;
    }
    
    /**
     * @param p file path
     * @return List of files
     */
    public File[] getFilesInDir(String p) {    
        return new File(p).listFiles();
    }
    
    public File[] getFiles() {
        return new File(this.inputDir).listFiles();
    }
    
    public String getInputDir() {
        return new String(this.inputDir);
    }
    
    public String getOutputDir() {
        return new String(this.outputDir);
    }
    
    public void setOutputDir(String s) {
        if(this.path != null && !new String("").equals(this.path)) {
            this.outputDir = new String(s);
            makeDir(this.outputDir);
        }
    }

}
