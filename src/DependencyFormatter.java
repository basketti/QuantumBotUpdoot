import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Takes in source code and replaces QuantumBot 0.3 style "getBot().get______"
 * with 0.4's DependencyInjection.
 * 
 * @author person
 */
public class DependencyFormatter extends Formatter {
	
	private static Map<String, String[]> mappins = new HashMap<String, String[]>() {
		
		private static final long serialVersionUID = 4486604239167882738L;

		{
			put("getDialogues", new String[] { "dialogues", "Dialogues" });
			put("getTabs", new String[] { "tabs", "Tabs" });
			put("getEquipment", new String[] { "equipment", "Equipment", "org.quantumbot.api.items." });
			put("getKeyboard", new String[] { "keyboard", "Keyboard", "org.quantumbot.api.inputs." });
			put("getWorlds", new String[] { "worlds", "Worlds", "org.quantumbot.api.worlds." });
			put("getStoreInventory", new String[] { "shopInventory", "ShopInventory", "org.quantumbot.api.items." });
			put("getTradeOffer", new String[] { "tradeOpponentOffer", "TradeOpponentOffer", "org.quantumbot.api.items." });
			put("getTradeInventory", new String[] { "tradeYourOffer", "TradeYourOffer", "org.quantumbot.api.items." });
			put("getQuests", new String[] { "quests", "Quests" });
			put("getSettings", new String[] { "settings", "Settings" });
			put("getGrandExchange", new String[] { "grandExchange", "GrandExchange", "org.quantumbot.api.ge." });
			put("getInventory", new String[] { "inventory", "Inventory", "org.quantumbot.api.items." });
			put("getCombat", new String[] { "combat", "Combat" });
			put("getNPCs", new String[] { "npcs", "NPCs", "org.quantumbot.api.entities.npc." });
			put("getGroundItems", new String[] { "groundItems", "GroundItems", "org.quantumbot.api.entities.grounditem." });
			put("getBank", new String[] { "bank", "Bank", "org.quantumbot.api.items." });
			put("getWidgets", new String[] { "widgets", "Widgets", "org.quantumbot.api.widgets." });
			put("getGameObjects", new String[] { "gameObjects", "GameObjects", "org.quantumbot.api.entities.object." });
			put("getPlayers", new String[] { "players", "Players", "org.quantumbot.api.entities.player." });
			put("getCamera", new String[] { "camera", "Camera" });
			put("getChatBox", new String[] { "chatBox", "ChatBox", "org.quantumbot.api.chatbox." });
			put("getProjectiles", new String[] { "projectiles", "Projectiles", "org.quantumbot.api.entities.projectile." });
			put("getMouse", new String[] { "mouse", "Mouse", "org.quantumbot.api.inputs." });
			put("getClient", new String[] { "client", "Client" });
			put("getRegion", new String[] { "region", "Region", "org.quantumbot.api.map." });
			put("getOwnedItems", new String[] { "ownedItems", "OwnedItems", "org.quantumbot.api.items." });
			put("getCarriedItems", new String[] { "carriedItems", "CarriedItems", "org.quantumbot.api.items." });
			put("getGraphics", new String[] { "graphics", "GameGraphics" });
			put("getLocalWeb", new String[] { "localWeb", "LocalWeb", "org.quantumbot.api.map.local." });
			put("getGlobalWeb", new String[] { "globalWeb", "GlobalWeb", "org.quantumbot.api.map.global." });
			put("getEventManager", new String[] { "eventManager", "EventManager" });
			put("getPriceGrabber", new String[] { "priceGrabber", "PriceGrabber" });
			put("getVarps", new String[] { "varps", "Varps" });
			put("getBulletinBoard", new String[] { "bulletinBoard", "BulletinBoard" });
			put("getPersona", new String[] { "persona", "Persona" });
		}
	};
	
	private String data;
	
	public DependencyFormatter(String source) {
		data = source;
	}
	
	@Override
	public String format() {
		Pattern pattern = Pattern.compile("(?:bot|getBot\\(\\))\\s*[.](get[A-Za-z]+)\\(\\)");
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String search = matcher.group();
			String method = matcher.group(1);
			if (mappins.containsKey(method)) {
				modified = true;
				
				String[] meta = mappins.get(method);
				modules.add("private "+meta[1]+" "+meta[0]+";");
				imports.add("import "+(meta.length > 2?meta[2]:"org.quantumbot.api.")+meta[1]+";");
				while (data.contains(search)) {
					data = data.replace(search, meta[0]);
				}
			} else {
				System.out.println("Unknown method "+method);
			}
		}
		return data;
	}
	
}
