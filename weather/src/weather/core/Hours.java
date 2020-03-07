package weather.core;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import weather.JsonSelector;

public class Hours {
	
	private List<Hour> _items;
	
	public Hours(JsonSelector js) {
		this._items = new ArrayList<Hour>();
		this._setItems(js);
	}

	public List<Hour> getItems() {
		return _items;
	}

	private void _setItems(JsonSelector js) {
		try {
			JSONArray arr = (JSONArray)js.get();
			for(Object obj: arr) {
				JsonSelector jsh = new JsonSelector(obj);
				Hour hr = new Hour(jsh);
				this._items.add(hr);
			}
		} catch( Exception e) {}
	}
	
	public String toString() {
		String msg = "";
		msg +=   "+-------+------------+--------------------+----------+-----+--------+-------+------+";
		msg += "\n| godz. | temp czucie|  wiatr  poryw kier.|ci�nienie |wilg.|  opady | widz. |zachm.|";
		msg += "\n+-------+------------+--------------------+----------+-----+--------+-------+------+";
		for(Hour hr: this._items) msg += "\n"+hr.toString();
		msg += "\n+-------+------------+--------------------+----------+-----+--------+-------+------+";
		return msg;
	}
	
}
