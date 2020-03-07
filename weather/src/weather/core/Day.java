package weather.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import weather.JsonSelector;

public class Day {
	
	private String _date;
	private String _tempMin;
	private String _tempMax;
	private String _snow;
	private String _sunrise;
	private String _sunset;
	private String _sunHour;
	
	private Hours _hours;
	
	
	public Day(JsonSelector js) {
		this._date 	  	= _parseDate( js.getString("date") );
		this._tempMin 	= js.getString("mintempC");
		this._tempMax 	= js.getString("maxtempC");
		this._snow 		= js.getString("totalSnow_cm");
		this._sunrise 	= _parseTime( js.getString("astronomy.0.sunrise") );
		this._sunset 	= _parseTime( js.getString("astronomy.0.sunset") );
		this._sunHour 	= js.getString("sunHour");
		this._hours 	= new Hours( js.copy("hourly") );
	}
	
	private static String _parseDate(String t) {
		LocalDate date = null;
		try {
			date = LocalDate.parse(t);
		} catch(DateTimeParseException e) {}
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE", Locale.forLanguageTag("pl-PL"));
		return date != null ? fmt.format(date) : "????-??-??";
	}
	
	private static String _parseTime(String t) {
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("hh:mm a");
		LocalTime tm = null;
		try {
			tm = LocalTime.parse(t, fmt1);
		} catch(DateTimeParseException e) {}
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("HH:mm");
		return tm != null ? fmt2.format(tm) : "??:??";
	}
	
	public String toString() {
		String msg =  
			  "  "+this._date + ":\n"
			+ "  wsch�d "+this._sunrise+", zach�d "+this._sunset+","
			+ " temperatura "+this._tempMin+"\u00B0C do "+this._tempMax+"\u00b0C,"
			+ " �nieg "+this._snow+" cm,"
			+ " us�onecznienie "+this._sunHour
			+ "\n"+this._hours;
		return msg;
	}
	
}
