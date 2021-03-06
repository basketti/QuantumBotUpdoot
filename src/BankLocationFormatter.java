public class BankLocationFormatter extends Formatter {
	
	private static final String[] names = {
		"VARROCK_WEST_BANK",
		"VARROCK_EAST_BANK",
		"GRAND_EXCHANGE",
		"EDGEVILLE_BANK",
		"FALADOR_WEST_BANK",
		"FALADOR_EAST_BANK",
		"DRAYNOR_VILLAGE_BANK",
		"AL_KHARID_BANK",
		"LUMBRIDGE_UPPER_BANK",
		"WARRIORS_GUILD_BANK",
		"FOSSIL_ISLAND_SEA_BANK",
		"CATHERBY_BANK",
		"SEERS_BANK",
		"FISHING_GUILD_BANK",
		"ARDOUGNE_SOUTH_BANK",
		"ARDOUNGE_NORTH_BANK",
		"YANILLE_BANK",
		"CASTLE_WARS_BANK",
		"BARBARIAN_ASSAULT_BANK",
		"GRAND_TREE_SLAYER_BANK",
		"GRAND_TREE_BANK_1",
		"GRAND_TREE_BANK_2",
		"LLETYA_BANK",
		"SHILLO_VILLAGE_BANK",
		"DUEL_ARENA_BANK",
		"NARDAH_BANK",
		"CANFIS_BANK",
		"PORT_PHASMATYS_BANK",
		"CLAN_WARS_BANK",
		"SHANTAY_PASS_BANK",
		"ETCETERIA_BANK",
		"JATIZSO_BANK",
		"NEITIZNOT_BANK",
		"LUNAR_ISLE_BANK",
		"PEST_CONTROL_BANK",
		"HOSIDIUS_HOUSE_CHARCOAL_BURNERS_BANK",
		"PISCARILLIUS_HOUSE_BANK",
		"ARCEUUS_HOUSE_BANK",
		"OUTSIDE_WINTERTODT_BANK",
		"HOSIDIUS_HOUSE_VINERY_BANK",
		"HOSIDIUS_HOUSE_MAIN_BANK",
		"HOSIDIUS_HOUSE_COOKING_BANK",
		"WOODCUTTING_GUILD_BANK",
		"LANDS_END_BANK",
		"SHAYZIEN_HOUSE_BANK",
		"LOVAKENGJ_HOUSE_BANK",
		"BLAST_MINE_BANK",
		"SULPHUR_MINE_BANK",
		"SALTPETRE_MINE_DEPOSIT_BOX",
		"LOVAKITE_MINE_BANK",
		"MOUNT_QUIDAMORTEM_BANK",
		"ZANARIS_BANK",
		"BLAST_FURANCE_BANK",
		"TZHAAR_CITY_BANK",
		"TZHAAR_CITY_EAST_BANK",
		"DORGESH_KAAN_BANK",
		"MOTHERLODE_MINE_BANK",
		"PORT_SARIM_DEPOSIT_BOX",
		"PORT_KHAZARD",
		"ROGUES_DEN"
	};

	private String data;
	
	public BankLocationFormatter(String source) {
		data = source;
	}
	
	@Override
	public String format() {
		for (String name : names) {
			if (data.contains("Bank."+name)) {
				modified = true;
				data = data.replace("Bank."+name, "BankLocation."+name);
			}
		}
		imports.add("import org.quantumbot.enums.BankLocation;");
		return data;
	}

}
