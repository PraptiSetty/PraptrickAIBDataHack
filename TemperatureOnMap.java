import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PApplet;


public class TemperatureOnMap
{
	
	
	public static String getLocation(float lat, float longi) throws IOException
	{
		
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+longi+"&key=AIzaSyBa8Tjk08e36mEhxhOQoHziXk5Ew8xvU9E");
		URLConnection con = url.openConnection();
        
        InputStream is =con.getInputStream();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String location=null;
        while ((line = br2.readLine()) != null) 
        {
        	if(line.contains("formatted_address"))
        	{
        		System.out.println(line);
        		return null;
//        		String[] parts = line.split(", ");
//        		if (parts[parts.length - 3].contains("Dublin"))
//        		{
//        			return parts[parts.length - 5];
//        		}
//        		return parts[parts.length - 4] == null ? null : parts[parts.length - 4];
        	}
        }
        return location;
	}
    public static void mainTemp(Location location) throws IOException
    {
    	
    	String suburbName=null;
    	int num = 0 ;
        suburbName = getLocation(location.getLat(), location.getLon());
        if (suburbName!= null)
        {
	        suburbName.toLowerCase();
	        
	    	if(suburbName.equals("swords"))
	    	{
	    		num =207962;
	    	}
	    	else if(suburbName.equals("dun laoghaire"))
	    	{
	    		num = 207967;
	    	}
	        URL url2 = new URL("https://www.accuweather.com/en/ie/"+suburbName+"/"+num+"/hourly-weather-forecast/"+num);
	        URLConnection con2 = url2.openConnection();
	        
	        InputStream is2 =con2.getInputStream();
	
	        
	        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
	        String line = null;
	        int toTomTDA =0;
	        
	        while ((line = br2.readLine()) != null) 
	        {
		        	String day ="null";
		        	if(toTomTDA == 0) 
		        	{
		        		day = "TODAY (Day 1)";
		        	}
		        	if(toTomTDA == 1) 
		        	{
		        		day = "TOMORROW (Day 2)";
		        	}
		            if(toTomTDA == 2) 
		        	{
		            	day = "DAY 3";
		        	}
		            if(toTomTDA == 3) 
		        	{
		            	day = "DAY 4";
		        	}
		            if(toTomTDA == 4) 
		        	{
		            	day = "DAY 5";
		        	}
		            if(line.contains("<span class=\"cond\">")) 
		            {
		            	System.out.print(day);
		            	line = line.replaceAll("<span class=\"cond\">", "");
		            	line = line.replaceAll("</span>", "");
		            	System.out.println(line);
		            	toTomTDA++;
		            }
	        }
        }
    }
}