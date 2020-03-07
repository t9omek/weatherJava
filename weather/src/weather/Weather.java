package weather;

import weather.core.Current;
import weather.core.Days;

public class Weather {
	
	private String _location;  
	private Current _current;
	private Days _days;
	
	public Weather(JsonSelector js) {
		this._location = js.getString("data.request.0.query");
		this._current = new Current( js.copy("data.current_condition.0") );
		this._days = new Days( js.copy("data.weather") );
	}
	
	
	public String toString() {
		String msg = 
				    "------------------------------------------------------------------------------------"
				+ "\n"
				+ "\n  ZNALEZIONA LOKALIZACJA: " + this._location
				+ "\n===================================================================================="
				+ "\n" + this._current
				+ "\n" + this._days;
		return msg;
	}
}
