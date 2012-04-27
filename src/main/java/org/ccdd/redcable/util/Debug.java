package org.ccdd.redcable.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.ccdd.redcable.RedCable;

public class Debug {
	
	public static final String tag = "RedCable";
	
	public static void debug(String debugText) {
		if (RedCable.instance.getConfig().getBoolean("debug")) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD +"["+tag+"] " + debugText);
		}
	}
	
	public static void debug(Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(allText);
	}
	
	public static void debug(Player player, String debugText) {
		if (RedCable.instance.getConfig().getBoolean("debug")) {
			player.sendMessage("&8["+tag + "] &7"+debugText);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD +"["+tag+"] " + player.getName() + ": " + debugText);
		}
	}
	
	public static void debug(Player player, Object... debugTexts) {
		String allText = "";
		for (Object debugText : debugTexts) {
			allText = allText + debugText.toString();
		}
		debug(player, allText);
	}
	
}
