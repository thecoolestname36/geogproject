package geogproject.Input;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brad
 */
public class InputManager {
    
    private File[] inputFiles;
    private HashMap inputMap;
            
    public InputManager() {
        this.inputFiles = new File[0];
        this.inputMap = new HashMap();
    }
    
    /**
     * 
     * @param fArr File Array
     */
    public InputManager(File[] fArr) {
        this.inputFiles = fArr;
        this.inputMap = readFilesToMap();
    }  
    
    /**
     * This class should be kicked off by
     * @return 
     */
    public HashMap readFilesToMap() {
        HashMap m = new HashMap(this.inputFiles.length);
        
        // Multi-Threaded File Reading
        InputThread[] threadArr = new InputThread[this.inputFiles.length];
        for(int i=0; i<threadArr.length; i++) {
            threadArr[i] = new InputThread(this.inputFiles[i]);
            threadArr[i].start();
        }
        
        boolean[] doneArr = new boolean[threadArr.length];
        for(boolean i : doneArr) {
            i=false;
        }
        int count = 0;
        while(count < threadArr.length) {
            for(int i=0; i<threadArr.length; i++) {
                InputThread t = threadArr[i];
                if(!threadArr[i].isAlive() && doneArr[i] == false) {
                    //m.put(t.getFileName(), t.getMinimizedFileContent());
                    m.put(t.getFileName(), t.getMetarAsList());
                    count++;
                    doneArr[i] = true;
                }
            }
            
            // Wait to check again
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(InputParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new HashMap(m);
    }
    
    public HashMap getHashMap() {
        return new HashMap(this.inputMap);
    }
    
    public void setHashMap(HashMap m) {
        this.inputMap = new HashMap(m);
    }
}
