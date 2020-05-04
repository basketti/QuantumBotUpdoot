
public class ImportFormatter extends Formatter {
	
	private static final String[][] replacements = {
			{"org.quantumbot.api.entities.GroundItem", "org.quantumbot.api.entities.grounditem.GroundItem"},
			{"org.quantumbot.api.entities.GameObject", "org.quantumbot.api.entities.object.GameObject"},
			{"org.quantumbot.api.entities.DecorativeObject", "org.quantumbot.api.entities.object.DecorativeObject"},
			{"org.quantumbot.api.entities.GroundObject", "org.quantumbot.api.entities.object.GroundObject"},
			{"org.quantumbot.api.entities.InteractableObject", "org.quantumbot.api.entities.object.InteractableObject"},
			{"org.quantumbot.api.entities.WallObject", "org.quantumbot.api.entities.object.WallObject"},
			{"org.quantumbot.api.entities.Player", "org.quantumbot.api.entities.player.Player"},
			{"org.quantumbot.api.entities.NPC", "org.quantumbot.api.entities.npc.NPC"},
			{"org.quantumbot.api.containers.Item", "org.quantumbot.api.items.Item"},
			{"org.quantumbot.api.containers.Inventory", "org.quantumbot.api.items.Inventory"},
			{"org.quantumbot.api.containers.Bank", "org.quantumbot.api.items.Bank"},
			{"org.quantumbot.api.containers.Equipment", "org.quantumbot.api.items.Equipment"},
			{"org.quantumbot.api.containers.CarriedItems", "org.quantumbot.api.items.CarriedItems"},
			{"org.quantumbot.api.containers.OwnedItems", "org.quantumbot.api.items.OwnedItems"},
			{"org.quantumbot.api.containers.TradeOffer", "org.quantumbot.api.items.TradeOpponentOffer"},
			{"org.quantumbot.api.containers.TradeInventory", "org.quantumbot.api.items.TradeYourOffer"},
			{"org.quantumbot.api.containers.StoreInventory", "org.quantumbot.api.items.ShopInventory"},

			{"org.quantumbot.api.osrsbox.AttackType", "org.quantumbot.api.definitions.AttackType"},
			{"org.quantumbot.api.equipment.AttackSpell", "org.quantumbot.enums.AttackSpell"},
			{"org.quantumbot.api.equipment.AttackStyle", "org.quantumbot.enums.AttackStyle"},
			{"org.quantumbot.api.osrsbox.EquipmentBonus", "org.quantumbot.api.definitions.EquipmentBonus"},
			{"org.quantumbot.api.osrsbox.Monster", "org.quantumbot.api.definitions.MonsterDefinition"},
			{"org.quantumbot.api.equipment.EquipmentDefinition", "org.quantumbot.api.definitions.EquipmentDefinition"},
			{"org.quantumbot.interfaces.Message", "org.quantumbot.api.chatbox.Message"},
			{"org.quantumbot.api.osrsbox.StanceType", "org.quantumbot.api.definitions.StanceType"},
			
			// Remove these
			{"org.quantumbot.api.QuantumBot"},
			{"org.quantumbot.enums.BankLocation"}
	};
	private String data;

	public ImportFormatter(String source) {
		data = source;
	}
	
	@Override
	public String format() {
		for (String[] pair : replacements) {
			if (data.contains(pair[0])) {
				modified = true;
				data = data.replaceAll("import "+pair[0]+";(\\s*)", pair.length > 1 ? "import "+pair[1]+";$1" : "");
			}
		}
		return data;
	}

}
