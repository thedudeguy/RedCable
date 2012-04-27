package org.ccdd.redcable.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.ccdd.redcable.RedCable;
import org.getspout.spoutapi.SpoutManager;

public class ResourceManager {

	public static final List<String> textures = Arrays.asList(
			"speakerwire.png",
			"speakerwireblock.png"
			);
	
	public static void copyResources() {
		for (String texture : textures) {
			doCopy(texture, "textures");
		}
	}
	
	private static void doCopy(String filename, String pathInJar) {
		
		File dir = new File(RedCable.instance.getDataFolder(), pathInJar);
		
		if (!dir.exists()) dir.mkdirs();
		if (!dir.canWrite()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not writable");
		if (!dir.isDirectory()) Bukkit.getLogger().log(Level.WARNING, "The path "+ dir.getPath() +" is not a directory");
		
		String fileCopyRelPath = new File(pathInJar, filename).getPath();
		
		File fileCopy = new File(RedCable.instance.getDataFolder(), fileCopyRelPath);
		
		if (!fileCopy.exists()) {
			
			RedCable.instance.saveResource(fileCopyRelPath, true);
			//fileCopy.setLastModified(new Date().getTime());
		}
	}
	
	public static void preLoginCache() {
		for (String texture : textures) {
			SpoutManager.getFileManager().addToPreLoginCache(RedCable.instance, new File(RedCable.instance.getDataFolder(), new File("textures", texture).getPath()));
		}
	}
	
	public static void clearCache() {
		SpoutManager.getFileManager().removeFromCache(RedCable.instance, textures);
	}
	
	public static void addCache() {
		for (String texture : textures) {
			SpoutManager.getFileManager().addToCache(RedCable.instance, new File(RedCable.instance.getDataFolder(), new File("textures", texture).getPath()));
		}
	}
	
	public static void resetCache() {
		clearCache();
		addCache();
	}
}
