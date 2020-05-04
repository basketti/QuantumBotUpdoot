import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replaces all the old `new SomethingEvent(getBot(), ...)` with `new SomethingEvent(...)`
 * @author person
 */
public class ConstructorFormatter extends Formatter {
	
	private String data;
	
	public ConstructorFormatter(String source) {
		data = source;
	}
	
	@Override
	public String format() {
		String className;
		
		Pattern pattern = Pattern.compile(
				"^(?:(?:public|private|protected|abstract)\\s+)*"
				+"class\\s+(\\S+)", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(data);
		if (matcher.find()) {
			className = matcher.group(1);
			pattern = Pattern.compile(
					"("+className+"\\s*\\()" // group 1
					+"\\s*QuantumBot\\s*[^,)]+" // Remove QuantumBot arg
					+"(?:[,]\\s*([^)]+)|\\s*)\\)" // group 2
					+"(\\s*\\{)" // group 3
					+"(?:(\\s*(?:this|super))\\(" // Group 4
					+"([^;]*);|\\s*this\\s*[.]\\s*bot\\s*=\\s*bot\\s*;)?" // group 5 is arguments, if not empty add super back
			);
			matcher = pattern.matcher(data);
			while (matcher.find()) {
				String args = matcher.group(5);
				if (args == null)
					args = "";
				if (!args.isEmpty())
					args = matcher.group(4)+"("+args+";";
				data = data.replace(matcher.group(),
						matcher.group(1)
						+(matcher.group(2) == null ? "" : matcher.group(2))+")"
						+matcher.group(3)
						+args
				);
			}
		}
		return data;
	}
	
}
