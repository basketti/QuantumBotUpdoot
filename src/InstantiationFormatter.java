import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replaces all the old `new SomethingEvent(getBot(), ...)` with `new SomethingEvent(...)`
 * @author person
 */
public class InstantiationFormatter extends Formatter {
	
	private String data;
	
	public InstantiationFormatter(String source) {
		data = source;
	}
	
	@Override
	public String format() {
		// Replaces all constructors with additional parameters
		Pattern pattern = Pattern.compile("\\(\\s*(?:bot|getBot\\(\\)),\\s*");
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String search = matcher.group();
			modified = true;
			
			while (data.contains(search)) {
				data = data.replace(search, "(");
			}
		}
		
		// Replaces all constructors with only QuantumBot argument
		pattern = Pattern.compile("\\(\\s*(?:bot|getBot\\(\\))\\s*\\)");
		matcher = pattern.matcher(data);
		while (matcher.find()) {
			String search = matcher.group();
			modified = true;
			
			while (data.contains(search)) {
				data = data.replace(search, "()");
			}
		}
		return data;
	}
	
}
