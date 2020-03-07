package weather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSelector {
	
	private Object _data;
	private static final String UNDEFINED = "????????";
	
	public JsonSelector(String json) {
		this( parse(json) );
	}
	
	public JsonSelector(Object json) {
		this._data = json;
	}
	
	
	
	public static Object parse(String json) {
		Object result = "";
		JSONParser parser = new JSONParser();
		try {
			result = parser.parse(json);
		} catch(ParseException e) {
			System.out.println(">>> ERROR JsonSelector.parse: "+e.getMessage());
		}
		return result;
	}
	
	public JsonSelector copy(String path) {
		return new JsonSelector( this.get(path) );
	}
	
	public String getString(String path) {
		Object result = this.get(path);
		return result == null ? UNDEFINED : result.toString();
	}
	
	public boolean has(String path) {
		Object result = this.get(path);
		return result != null;
	}
	
	public Object get() {
		return this._data;
	}
	public Object get(String path) {
		
		if(path==null || path=="") {
			return this._data;
		}
		
		Object cursor = this._data;
		
		String[] spl = path.split("[.]");
		for( String part: spl) {
			try {
				//sprawdzenie czy part mo�e by� intem
				int index = Integer.parseInt(part);
				// jesli int, nastepuje pr�ba pobrania elementu o podanym indeksie z JsonArray
				try {
					cursor = ((JSONArray)cursor).get(index);
				} catch (Exception e) {
					cursor = null;
				}
				
			} catch( NumberFormatException e) {
				// part jest Stringiem - pr�ba pobrania JSONObject
				try {
					cursor = ((JSONObject)cursor).get(part);
				} catch (Exception e1) {
					cursor = null;
				}
			}
			
			if(cursor==null) break;
		}
		return cursor;
	}
}
