
public class ClientRefactorFormatter extends Formatter {
	
	private String data;
	
	private static final String[][] replacements = {
			{ "client.getSkillBoosted", "skills.getBoosted", "private Skills skills;", "org.quantumbot.api.Skills" },
			{ "client.getSkillReal", "skills.getReal", "private Skills skills;", "org.quantumbot.api.Skills" },
			{ "client.getSkillExperience", "skills.getExperience", "private Skills skills;", "org.quantumbot.api.Skills" },
			{ "client.getVarp", "varps.getVarp", "private Varps varps;", "org.quantumbot.api.Varps" },
			{ "client.getVarbit", "varps.getVarbit", "private Varps varps;", "org.quantumbot.api.Varps" },
	};

	public ClientRefactorFormatter(String source) {
		data = source;
	}

	@Override
	public String format() {
		for (String[] r : replacements) {
			if (data.contains(r[0])) {
				data = data.replace(r[0], r[1]);
				modified = true;
				modules.add(r[2]);
				imports.add("import "+r[3]+";");
			}
		}
		return data;
	}

}
