/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geogproject.Analysis;

import geogproject.Input.Metar;
import java.util.LinkedList;

/**
 *
 * @author Brad
 */
public class ThunderStormThread extends Thread{
    
    private String stormDates;
    private String station;
    private LinkedList metars;
    
    public ThunderStormThread(String s, LinkedList m) {
        this.metars = m;
        this.station = s;
        this.stormDates = new String();
    }
    
    @Override
    public void run() {

        for(int i=0; i<this.metars.size(); i++) {
            Metar metar = (Metar) this.metars.get(i);
            
            if(metar.getWx().length() > 0) {
                this.stormDates += metar.getWx() + "\n";
            }
        }
        
        
            
    }
    
    public String getStormDates() {
        return new String(this.stormDates);
    }
    
}
