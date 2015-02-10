/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author blondieymollo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s = "";
        try{
            s = "["+readUrl("http://api.openweathermap.org/data/2.5/find?q=10038,USA&units=imperial")+"]"; 
        } catch(Exception e){
            
        }
        
      JSONParser parser = new JSONParser();
      
      try{
         
         Object obj = parser.parse(s);
         JSONArray array = (JSONArray)obj;
         
         JSONObject obj2 = (JSONObject)array.get(0);
         array = (JSONArray)(parser.parse(obj2.get("list").toString()));
         
         JSONObject obj3 = (JSONObject)( array.get(0));
         System.out.println("City Name: "+obj3.get("name"));
         
         /*---------------------weather---------------------*/
         array = (JSONArray)(parser.parse(obj3.get("weather").toString()));
         JSONObject obj4 = (JSONObject)array.get(0);
         
         System.out.println("Weather: "+obj4.get("description"));
         System.out.println("Icon: http://openweathermap.org/img/w/"+obj4.get("icon")+".png");
         
         /*---------------------main---------------------*/
         String temp = "["+obj3.get("main").toString()+"]";
         array = (JSONArray)(parser.parse(temp));
         JSONObject obj6 = (JSONObject)array.get(0);
         
         System.out.println("Humidity: "+obj6.get("humidity")+"%");
         System.out.println("Temperature: "+obj6.get("temp")+" F");
         
         /*---------------------wind---------------------*/
         temp = "["+obj3.get("wind").toString()+"]";
         array = (JSONArray)(parser.parse(temp));
         JSONObject obj8 = (JSONObject)array.get(0);
         
         System.out.println("Wind speed: "+obj8.get("speed")+" mph");
         
         
      }catch(ParseException pe){
         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }
        
    }
    
   
    private static String readUrl(String urlString) throws Exception {
    BufferedReader reader = null;
    try {
        URL url = new URL(urlString);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read); 

        return buffer.toString();
    } finally {
        if (reader != null)
            reader.close();
    }
}

}
