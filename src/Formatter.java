import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Formatter {
	
	protected boolean modified;
	protected Set<String> modules = new LinkedHashSet<>();
	protected Set<String> imports = new LinkedHashSet<>();
	
	public abstract String format();
	
	public boolean wasModified() {
		return modified;
	}
	
	public Set<String> getModules() {
		return modules;
	}
	
	public Set<String> getImports() {
		return imports;
	}

}
