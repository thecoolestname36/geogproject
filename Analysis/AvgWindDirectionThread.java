package geogproject.Analysis;

import geogproject.Input.Metar;
import java.util.LinkedList;

/**
 *
 * @author Brad
 */
public class AvgWindDirectionThread extends Thread {
        
    private String windDirection;
    private String station;
    private LinkedList metars;
    
    public AvgWindDirectionThread(String s, LinkedList m) {
        this.metars = m;
        this.station = s;
        this.windDirection = new String();
    }
    
    @Override
    public void run() {

        this.windDirection = "Direction: " + averageWindDirByStation();
    }
    
    private String averageWindDirByStation() {

        double cos=0.0, sin=0.0, avg=0.0;
        int sampleSize = 0;

        for(int i=0; i<this.metars.size(); i++) {
            Metar metar = (Metar) this.metars.get(i);

            if(metar.getWindDir()<999 && metar.getWindDir()>-999) {

                sin += Math.sin(Math.toRadians(metar.getWindDir()));
                cos += Math.cos(Math.toRadians(metar.getWindDir()));
                sampleSize++;
            }
        }
        
        avg = Math.toDegrees(Math.atan2(sin / ((double)sampleSize), cos / ((double)sampleSize)));
        if(avg < 0) {
            avg = avg + 360;
        }      
        return this.station + ": " + avg;
    }
    
    public String getWindDirection() {
        return new String(this.windDirection);
    }
}
