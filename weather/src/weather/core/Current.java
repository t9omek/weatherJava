package weather.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import weather.JsonSelector;

public class Current {
	
	private String _time;
	private String _temp;
	private String _tempFeel;
	private String _description;
	private String _wind;
	private String _windDir;
	private String _precip;
	private String _humidity;
	private String _visibility;
	private String _pressure;
	private String _cloudcover;
	
	
	public Current(JsonSelector js) {
		this._time = _parseTime( js.getString("localObsDateTime") );
		this._temp 		  = js.getString("temp_C");
		this._tempFeel 	  = js.getString("FeelsLikeC");
		this._wind 		  = js.getString("windspeedKmph");
		this._windDir 	  = js.getString("winddir16Point");
		this._pressure 	  = js.getString("pressure");
		this._humidity 	  = js.getString("humidity");
		this._precip 	  = js.getString("precipMM");
		this._visibility  = js.getString("visibility");
		this._cloudcover  = js.getString("cloudcover");
		this._description = js.getString("lang_pl.0.value");
	}
	
	private static String _parseTime(String t) {
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		LocalDateTime tm = null;
		try {
			tm = LocalDateTime.parse(t, fmt1);
		} catch(DateTimeParseException e) {}
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("HH:mm");
		return tm != null ? fmt2.format(tm) : "??:??";
	}
	
	public String toString() {
		String msg = 
		    "  AKTUALNA POGODA (dane z godz. "+this._time + "): "+this._description
		+ "\n  -> temperatura "+this._temp+"\u00B0C (odczuwalna "+this._tempFeel+"\u00b0C),"
		+ "\n  -> wiatr "+this._wind+" km/h z kierunku "+this._windDir
		+ "\n  -> ci�nienie "+this._pressure+" hPa, wilgotno�� "+this._humidity+"%, opady "+this._precip+" mm"
		+ "\n  -> widzialno�� "+this._visibility+" km, zachmurzenie "+this._cloudcover+"%";
		return msg;
	}
}
