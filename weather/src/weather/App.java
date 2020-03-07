package weather;


public class App {
		
	private static final String APPNAME = "Prognoza pogody";
	private static final String VERSION = "1"; 
	private static final String DOC = 
		  "\n-------------------------------------------------"
	    + "\n  "+APPNAME+" ("+VERSION+")"
	    + "\n  
	    + "\n-------------------------------------------------"
	    + "\n"
	    
	    + "\n  Usage:"
	    + "\n    weather.App LOCATION... [options]"
	    + "\n    weather.App (-h | --help)"
	    + "\n"
	    
		+ "\n  Options:"
	    + "\n    -n --numdays=<n>"
	    + "\n               Liczba dni prognozy [default: 5]"
	    + "\n               Warto�� pomijana gdy jest ustawiony parametr -d."
	    + "\n    -d --date=<d>"
	    + "\n               Data prognozy spo�r�d kolejnych 15 dni (w formacie YYYY-MM-DD)."
	    + "\n    -i --interval=<i>"
	    + "\n               Przedzia�y czasowe prognozy (1,3,6,12,24 godzin) [default: 3]"
	    + "\n    -h --help  Wy�wietla ten ekran."
	    + "\n    --version  Wy�wietla nr wersji programu."
	    + "\n"
	    
		+ "\n    LOCATION:  Nazwa miejscowo�ci i/lub kraju (np. swinoujscie lub swinoujscie,poland)"
		+ "\n         lub   Adres IP (XXX.XXX.XXX.XXX)"
		+ "\n         lub   Szeroko�� i d�ugo�� geograficzna (XX.XXX,XX.XXX)"
		+ "\n";
		
//	private static final String APIKEY = "27fe0a8d46cb4527acc213835201401"; 
	
	private static final String APIKEY = "78b175d42111479c813173844202802"; 
	
	
	public static void main(String[] args) {
		
		// parsowanie przekazanych argument�w
		ArgsParser ap = new ArgsParser(args, DOC, VERSION);
		
		// pobieranie jsona
	    String json = new Connector(APIKEY).request(ap.getLocation(), ap.getNumDays(), ap.getDate(), ap.getInterval());
	    
	    // parsowanie jsona, wybierak
	    JsonSelector js = new JsonSelector(json);
	    
	    
	    String head = "\n\n"+APPNAME+" ("+VERSION+") - szukana lokalizacja: "+ap.getLocation();
	    System.out.println(head);
	    
	    if(js.has("data.error.0.msg")) {
	    	String err = "\nNie mo�na znale�� lokalizacji '"+ap.getLocation()+"'\n";
	    	System.out.println(err);
	    
	    } else {
	    	Weather wt = new Weather(js);
		    System.out.println(wt);
	    }
	    
	    System.out.println(APIKEY);
	    
	}
	
	
	
}
