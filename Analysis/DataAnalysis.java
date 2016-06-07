package geogproject.Analysis;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Brad
 */
public class DataAnalysis {
    
    
    public DataAnalysis() {
        
    }
    
    public String[] averageTempByStation(HashMap m) {
        
        int length = m.keySet().toArray().length;
        String[] returnArr = new String[length];
        
        AvgTemperatureThread[] threadArr = new AvgTemperatureThread[length];
        for(int i=0; i<length; i++) {
            threadArr[i] = new AvgTemperatureThread(
                    (String)m.keySet().toArray()[i],
                    (LinkedList) m.get(m.keySet().toArray()[i])
            );
            threadArr[i].start(); 
        }

        // Track thread completion using an additional array
        boolean[] doneArr = new boolean[length];
        for(boolean i : doneArr) {
            i=false;
        }
        int count = 0;
        
        while(count < length) {
            for(int i=0; i<length; i++) {
                AvgTemperatureThread t = threadArr[i];
                if(!threadArr[i].isAlive() && doneArr[i] == false) {
                    //DO something with finished thread
                    returnArr[i] = t.getTemperature();
                    count++;
                    doneArr[i] = true;
                }
            }
            // Wait to check again
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Data Analysis: Main thread completion error!");
                count = length;
            }
        }
        return returnArr;
    }
    

    public String[] averageWindDirByStation(HashMap m) {
        int length = m.keySet().toArray().length;
        String[] returnArr = new String[length];
        
        AvgWindDirectionThread[] threadArr = new AvgWindDirectionThread[length];
        for(int i=0; i<length; i++) {
            threadArr[i] = new AvgWindDirectionThread(
                    (String)m.keySet().toArray()[i], 
                    (LinkedList) m.get(m.keySet().toArray()[i])
            );
            threadArr[i].start(); 
        }

        // Track thread completion using an additional array
        boolean[] doneArr = new boolean[length];
        for(boolean i : doneArr) {
            i=false;
        }
        int count = 0;
        
        while(count < length) {
            for(int i=0; i<length; i++) {
                AvgWindDirectionThread t = threadArr[i];
                if(!threadArr[i].isAlive() && doneArr[i] == false) {
                    //DO something with finished thread
                    returnArr[i] = t.getWindDirection();
                    count++;
                    doneArr[i] = true;
                }
            }
            // Wait to check again
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("GeogProject: Main thread completion error!");
                count = length;
            }
        }
        return returnArr;
    }
    
    public String[] dateOfTStorm(HashMap m) {
        
        int length = m.keySet().toArray().length;
        String[] returnArr = new String[length];
        
        ThunderStormThread[] threadArr = new ThunderStormThread[length];
        for(int i=0; i<length; i++) {
            threadArr[i] = new ThunderStormThread(
                    (String)m.keySet().toArray()[i],
                    (LinkedList) m.get(m.keySet().toArray()[i])
            );
            threadArr[i].start(); 
        }

        // Track thread completion using an additional array
        boolean[] doneArr = new boolean[length];
        for(boolean i : doneArr) {
            i=false;
        }
        int count = 0;
        
        while(count < length) {
            for(int i=0; i<length; i++) {
                ThunderStormThread t = threadArr[i];
                if(!threadArr[i].isAlive() && doneArr[i] == false) {
                    //DO something with finished thread
                    returnArr[i] = t.getStormDates();
                    count++;
                    doneArr[i] = true;
                }
            }
            // Wait to check again
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Data Analysis: Main thread completion error!");
                count = length;
            }
        }
        return returnArr;
    }
}
