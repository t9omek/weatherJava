package weather.core;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import weather.JsonSelector;

public class Days {
	
	private List<Day> _items;
	
	public Days(JsonSelector js) {
		this._items = new ArrayList<Day>();
		this._setItems(js);
	}
	
	public List<Day> getItems() {
		return _items;
	}
	
	
	private void _setItems(JsonSelector js) {
		try {
			JSONArray arr = (JSONArray)js.get();
			for(Object obj: arr) {
				JsonSelector jsel = new JsonSelector(obj);
				Day day = new Day(jsel);
				this._items.add(day);
			}
		} catch( Exception e) {}
	}
	
	public String toString() {
		String msg = "";
		for(Day day: this._items) msg += "\n\n"+day.toString();
		return msg;
	}
	
}
