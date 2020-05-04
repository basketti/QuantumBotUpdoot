import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	@SuppressWarnings("serial")
	private static List<Class<? extends Formatter>> formatters = new ArrayList<Class<? extends Formatter>>() {
		{
			add(DependencyFormatter.class);
			add(InstantiationFormatter.class);
			add(ConstructorFormatter.class);
			add(ImportFormatter.class);
			add(BankLocationFormatter.class);
			add(ClientRefactorFormatter.class);
		}
	};
	
	public static void main(String[] args) {
		final String path;
		
		if (args.length > 0) {
			path = args[0];
		} else {
			System.err.println("No path given! Give a path as first argument.");
			System.exit(1);
			return;
		}
		
		File file = new File(path);
		if (!file.exists()) {
			System.err.println("Path does not exist!");
			System.exit(1);
			return;
		}
		
		scanDir(file);
	}
	
	private static void scanDir(File directory) {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				scanDir(f);
			} else if (f.getName().endsWith(".java")){
				try {
					rewriteFile(f);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void rewriteFile(File f) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SecurityException {
		try {
			String data = new String(Files.readAllBytes(f.toPath()));
			
			Set<String> modules = new HashSet<>();
			Set<String> imports = new HashSet<>();
			
			boolean wasModified = false;
			
			for (Class<? extends Formatter> formatter : formatters ) {
				Formatter instance = (Formatter) formatter.getConstructors()[0].newInstance(data);
				data = instance.format();
				if (instance.wasModified()) {
					wasModified = true;
					modules.addAll(instance.getModules());
					imports.addAll(instance.getImports());
				}
			}
			
			if (wasModified) {
				// Add in all the good bits
				Pattern pattern = Pattern.compile("^([\\s\\S]+?)("
						+"(?:[/][*][*][\\s\\S]*?[*][/]\\s*)*"
						+"(?:[@][\\s\\S]+?)?"
						+"((?:public|private|protected|abstract)\\s+)*"
						+"class[^{]+\\{)(\r?\n)");
				Matcher matcher = pattern.matcher(data);
				if (matcher.find()) {
					String lineBreak = matcher.group(4);
					String inject = matcher.group(1);
					for (String _import : imports) {
						if (!data.contains(_import))
							inject += _import+lineBreak;
					}
					inject += lineBreak+matcher.group(2)+lineBreak+lineBreak;
					for (String module : modules) {
						inject += "\t"+module+lineBreak;
					}
					String find = matcher.group();
					data = data.replace(find, inject);
					// Have to save?
					System.out.println("REWROTE " + f.getAbsolutePath());
					Files.write(f.toPath(), data.getBytes());
					//System.out.println(data);
				} else {
					System.err.println("Unable to find top of class: "+f.getAbsolutePath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
