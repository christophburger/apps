package control;

import java.util.ArrayList;

import data.Plugin;

public class PluginManager {
	
	ArrayList<Plugin> loadedPlugins;
	
	ArrayList<Plugin> availablePlugins;
	
	public PluginManager() {
		
		loadedPlugins = new ArrayList<Plugin>();
		availablePlugins = new ArrayList<Plugin>();
		
	}
	
	public void loadPlugin(Plugin p) {
		
		if(!loadedPlugins.contains(p))
			loadedPlugins.add(p);
		else
			System.out.println("Plugin already loaded!");
		
		if(availablePlugins.contains(p))
			availablePlugins.remove(p);
		
	}
	
	public void unloadPlugin(Plugin p) {
		
	}
	
}
