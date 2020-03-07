package weather;

import java.util.List;
import java.util.Map;
import org.docopt.Docopt;


public class ArgsParser {
	
	private Map<String, Object> _arguments; 
	
	
	public ArgsParser(String[] args, String doc, String version) {
		this._arguments = new Docopt(doc).withVersion(version).parse(args);
	}
	
	
	public String getLocation() {
		@SuppressWarnings("unchecked")
		List<String> arr = (List<String>)this._arguments.get("LOCATION");
		return String.join(" ", arr);
	}
	
	
	public String getNumDays() {
		return (String)this._arguments.getOrDefault("--numdays", "");
	}
	
	
	public String getDate() {
		return (String)this._arguments.getOrDefault("--date", "");
	}
	
	
	public String getInterval() {
		return (String)this._arguments.getOrDefault("--interval", "");
	}
	
	
	@Override
	public String toString() {
		return this._arguments.toString();
	}
	
}
