/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geogproject.Input;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;

/**
 *
 * @author Brad
 */
public class Metar {
    
    private String metar;
    private LinkedList<String> splitMetar;
    private String station;
    private String date;
    private String time;
    private String Wx;
    private int windDir;
    private int windSpeed;
    private int temp;
    private int dewPoint;
    
    public Metar(String s) {
        char[] cArr = new char[s.length()];
        for(int i = 0; i < s.length(); i++) {
            if(s.toCharArray()[i] != '\r' && s.toCharArray()[i] != '\n'){
                cArr[i] = s.toCharArray()[i];
            }
        }

        this.metar = new String(cArr);
        this.splitMetar = new LinkedList<>();
        this.Wx = "";
        metarToArr();
        processStation();
        processTempDewpoint();
        processWindDir();
        processDateTime();
        processWx();
        //print("New Metar :" + this.metar);
        

    }
    
    private void print(String s) {
        System.out.println(s);
    }
    
    private void metarToArr() {
        String m = this.metar;
        while(m.length() > 0) {
            if(m.indexOf(' ') > -1) {
                this.splitMetar.addLast(m.substring(0, m.indexOf(' ')));
                m = m.substring(m.indexOf(' ')+1);
                
            } else {
                this.splitMetar.addLast(m);
                m = "";
            }            
        }
    }
    
    private void processTempDewpoint() {
        
//        for(String s : this.splitMetar) {
//            if(s.contains("/") && !s.contains("SM") && !s.contains("FT")) {
//                print(s);
//                return;
//            }
//        }
        
        
        
        String s = "";
        for(int i=0; i<this.splitMetar.size(); i++) {
            String si = this.splitMetar.get(i);
            if(si.contains("/") && !si.contains("SM") && !si.contains("FT")) {
                s = si;
                //print(s);
                i = this.splitMetar.size();
            }
        }
        if(s != "" && s.contains("/")) {
            
            
            // Process Temperature
            //print(s.substring(0,s.indexOf('/')));
            String t = s.substring(0,s.indexOf('/'));
            
            if(s.contains("M")) {
                t = '-' + s.substring(s.indexOf("M")+1);
            }
            
            try {
                this.temp = Integer.parseInt(t);
            } catch(NumberFormatException e) {
                this.temp = -999;
            }
            
            // Process Dew Point
            //print(s.substring(s.indexOf('/') + 1));
            String dp = s.substring(s.indexOf('/') + 1);
            
            if(s.contains("M")) {
                dp = '-' + s.substring(s.indexOf("M")+1);
            }
            
            try {
                this.dewPoint = Integer.parseInt(dp);
            } catch(NumberFormatException e) {
                this.dewPoint = -999;
            }
        }
    }
    
    private void processWindDir() {
        
        String s = "";
        for(int i=0; i<this.splitMetar.size(); i++) {
            String si = this.splitMetar.get(i);
            if(si.contains("KT") && !si.contains("G")) {
                s = si;
                //print(s);
                i = this.splitMetar.size();
            }
        }
        if(s != "" && !s.contains("VRB") && !s.contains("000")) {
            
            // Process Temperature
            String t = s.substring(0,3);
            
            try {
                this.windDir = Integer.parseInt(t);
            } catch(NumberFormatException e) {
                this.windDir = -999;
            }
        } else {
            this.windDir = -999;
        }
    }
    
    private void processDateTime() {
        this.date = this.splitMetar.get(1).substring(0, 2);
        this.time = this.splitMetar.get(1).substring(2);
    }
    
    private void processWx() {
        String[] ts = {"+TS","TS","-TS"};
        for(String s : this.splitMetar) {
            for(String t : ts) {
                if (s.contains(t)) {
                    if(this.station.length() > 0 && this.station != null) {
                        this.Wx += this.station + " ";
                    }
                    this.Wx += this.date+ " " + this.time+ " " + s;
                }
            }
        }
    }
    
    private void processStation() {
        this.station = this.splitMetar.get(0);
    }
    
    public String getWx() {
        return new String(this.Wx);
    }
    
    public String getStation() {
        return new String(this.station);
    }
    
    public String getDate() {
        return new String(this.date.toCharArray());
    }
    
    public String getTime() {
        return new String(this.time.toCharArray());
    }
    
    public int getWindDir() {
        return this.windDir;
    }
    
    public int getWindSpeed() {
        return this.windSpeed;
    }

    public int getTemp() {
        return this.temp;
    }
    
    public int getDewPoint() {
        return this.dewPoint;
    }
}
