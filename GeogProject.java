package geogproject;

import geogproject.Input.FileManager;
import geogproject.Input.InputManager;
import geogproject.Analysis.DataAnalysis;



/**
 *
 * @author Brad
 */
public class GeogProject {
    
    /**
     * Multi processing thread (for calculations)
     * @param args 
     */
    public static void main(String[] args) {     
        
        
        debug();
        //runWithArgs(args);
        
    }
    
    static void debug() {
        FileManager fM = new FileManager("C:/Users/Brad/Documents/Workspace/geog2306", "/InputData", "/OutputData");
        InputManager iM = new InputManager(fM.getFiles());
        fM.saveOutputToFileWithName("Temperature", new DataAnalysis().averageTempByStation(iM.getHashMap()));
        fM.saveOutputToFileWithName("Wx", new DataAnalysis().dateOfTStorm(iM.getHashMap()));
    }
    
    static void runWithArgs(String[] args) {
        if(args[0].toLowerCase() == "help") {
            System.out.println("Enter a directory that contains the folder: InputData");
        } else if(!(args.length > 1)) {
            String location = new String();
            for(char c : args[0].toCharArray()) {
                if(c == '\\') {
                    c = '/';
                }
                location += c;
            }
            
            System.out.println(location);
            
            
            FileManager fM = new FileManager(location, "/InputData", "/OutputData");
            InputManager iM = new InputManager(fM.getFiles());
            //fM.saveOutputToFile(new DataAnalysis().averageTempByStation(iM.getHashMap()));
            fM.saveOutputToFileWithName("Temperature", new DataAnalysis().averageTempByStation(iM.getHashMap()));
            fM.saveOutputToFileWithName("Wx", new DataAnalysis().dateOfTStorm(iM.getHashMap()));
        } else {
            System.out.println("Use a folder that contains a folder named InputData\n" +
                               "ex: C:/project");
        }
    }
}
