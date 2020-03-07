package weather;

import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class Connector {
	
	private String _apikey;
	
	
	public Connector(String apikey) {
		this._apikey = apikey;
	}

	
	public String request(String location, String numdays, String date, String interval) {
		URL url = this._createUrl(location, numdays, date, interval);
		return _loadJson(url);
	}
	
	
	private URL _createUrl(String location, String numdays, String date, String interval) {
		URL result = null;
		String urlStr = "http://api.worldweatheronline.com/premium/v1/weather.ashx?";
		urlStr += "&format=json";
		urlStr += "&mca=no";
		urlStr += "&lang=pl";
		urlStr += "&extra=localObsTime";
		urlStr += "&fx=yes";
		urlStr += "&cc=yes";
		urlStr += "&fx24=no";
		urlStr += "&includelocation=no";
		urlStr += "&show_comments=no";
		urlStr += "&showlocaltime=no";
		
		urlStr += "&key=" 		  + this._apikey;
		urlStr += "&q=" 		  + URLEncoder.encode(location, StandardCharsets.UTF_8);
		urlStr += "&num_of_days=" + numdays;
		urlStr += "&date=" 		  + date;
		urlStr += "&tp=" 		  + interval;
		
		//System.out.println(urlStr);
		try {
			result = new URL(urlStr);
		
		} catch(MalformedURLException e1) {
			String msg = "B��d w trakcie przetwarzania adresu URL:\n"+urlStr+"\nMsg: "+e1.getMessage();
			System.out.println(msg);
			System.exit(1);
			
		} finally {
			//System.out.println(">>> Connector._createUrl ");
		}
		
		return result;
	}
	
	
	private static String _loadJson(URL url) {
		String result = "";
		try {
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream(), "UTF-8" ));
			String data;
			
			while( (data = br.readLine()) != null) 
				result += data;
			
		} catch(IOException e) {
			String msg = "B��d w podczas pobierania danych z worldweatheronline.com\nMsg: "+e.getMessage();
			System.out.println(msg);
			System.exit(1);
			
		} finally {
			//System.out.println(">>> Connector._loadJson ");
		}
		
		return result;
	}
}
