package geogproject.Analysis;

import geogproject.Input.Metar;
import java.util.LinkedList;

/**
 *
 * @author Brad
 */
public class AvgTemperatureThread extends Thread{
    
    private String temperature;
    private String station;
    private LinkedList metars;
    
    public AvgTemperatureThread(String s, LinkedList m) {
        this.metars = m;
        this.station = s;
        this.temperature = new String();
    }
    
    @Override
    public void run() {

            this.temperature = "Temperature: " + averageTempByStation();
    }
    
    private String averageTempByStation() {

        int temp = 0;
        int sampleSize = 0;
        for(int i=0; i<this.metars.size(); i++) {
            Metar metar = (Metar) this.metars.get(i);
            
            if(metar.getTemp()<999 && metar.getTemp()>-999) {
                temp += metar.getTemp();
                sampleSize++;
            }
        }
        return this.station + ": " + (temp/sampleSize);
    }
    
    public String getTemperature() {
        return new String(this.temperature);
    }
}
