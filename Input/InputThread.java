package geogproject.Input;

import java.util.LinkedList;
import java.io.File;

/**
 *
 * @author Brad
 */
public class InputThread extends Thread{
    
    private File file;
    private String fileName;
    private String rawFileContent;
    private boolean isComplete;
    private InputParser inputParser;
    
    InputThread(File f) {
        this.file = f;
        this.fileName = f.getName();
        this.rawFileContent = "";
        this.isComplete = false;
        this.inputParser = new InputParser(f);
    }

    @Override
    public void run() {
        System.out.println(" + Reading " + this.fileName);
        this.rawFileContent = inputParser.fileToString(this.file);
        this.isComplete = true;
        //System.out.println("Reading Done " + this.fileName);
    }
    
    public String getFileName() {
        return new String(this.fileName);
    }
    
    public String getMinimizedFileContent() {
        String s = "";
        if(this.isComplete) {
            s = new String(inputParser.minimizeString(this.rawFileContent));
        }
        return s;
    }

    public LinkedList getMetarAsList() {
        LinkedList l = new LinkedList();
        if(this.isComplete) {
            l = new LinkedList(inputParser.asList(inputParser.minimizeString(this.rawFileContent)));
        }
        return l;
    }
}
