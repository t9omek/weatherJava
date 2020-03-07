package weather.core;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import weather.JsonSelector;

public class Hour {
	
	private String _time;
	private String _temp;
	private String _tempFeel;
	private String _wind;
	private String _windGust;
	private String _windDir;
	private String _pressure;
	private String _humidity;
	private String _precip;
	private String _visibility;
	private String _cloudcover;
	private String _description;
	
	
	public Hour(JsonSelector js) {
		this._time 		  = _parseTime( js.getString("time") );
		this._temp 		  = js.getString("tempC");
		this._tempFeel 	  = js.getString("FeelsLikeC");
		this._wind 		  = js.getString("windspeedKmph");
		this._windGust 	  = js.getString("WindGustKmph");
		this._windDir 	  = js.getString("winddir16Point");
		this._pressure 	  = js.getString("pressure");
		this._humidity 	  = js.getString("humidity");
		this._precip 	  = js.getString("precipMM");
		this._visibility  = js.getString("visibility");
		this._cloudcover  = js.getString("cloudcover");
		this._description = js.getString("lang_pl.0.value");
	}
	
	
	private static String _parseTime(String t) {
		if(t.equals("0") || t.equals("24")) t="000";
		
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("Hmm");
		LocalTime tm = null;
		try {
			tm = LocalTime.parse(t, fmt1);
		} catch(DateTimeParseException e) {}
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("HH:mm");
		return tm != null ? fmt2.format(tm) : "??:??";
	}
	
	
	public String toString() {
		String msg = "  "
		+ String.format("%5s ", this._time)
		+ String.format(" %3.3s\u00B0C %3.3s\u00B0C ", this._temp, this._tempFeel )
		+ String.format(" %3.3s kmh %3.3s kmh %3.3s ", this._wind, this._windGust, this._windDir)
		+ String.format("  %4.4s hPa ", this._pressure)
		+ String.format(" %4.4s ", this._humidity+"%")
		+ String.format(" %4.4s mm ", this._precip)
		+ String.format(" %3.3s km ", this._visibility)
		+ String.format(" %5.5s ", this._cloudcover+"%")
		+ "  "+this._description;
		return msg;
	}
}
