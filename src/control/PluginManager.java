package control;

import gui.PluginManagerWindow;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import resource.Resource;

import data.Plugin;

public class PluginManager {
	
	public static HashMap<String, Plugin> loadedPlugins;
	
	public static HashMap<String, Plugin> availablePlugins;
	
	private final String PLUGINDIR = "plugins";
	
	public PluginManager() {
		
		loadedPlugins = new HashMap<String, Plugin>();
		availablePlugins = new HashMap<String, Plugin>();
		
		try {
			getAvailablePlugins();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Resource.availablePlugins = availablePlugins;
		
		System.out.println("av: " + availablePlugins);
		
	}
	
	public void getAvailablePlugins() throws ClassNotFoundException {
		File plugindir = new File(PLUGINDIR);
		
		if(plugindir.exists()) {
			File[] plugins = plugindir.listFiles();
			for(File f : plugins) {
				URL[] u = new URL[1];
				if(f.exists()) {
					if(f.getName().endsWith(".jar")) {
						try {
							u[0] = f.toURI().toURL();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						URLClassLoader loader = new URLClassLoader(u);
							
						String classtoload = "AppsPlugin";
						Class loadedClass = loader.loadClass(classtoload);
						
						try {
							Plugin p = (Plugin)loadedClass.newInstance();
							availablePlugins.put(p.getPluginName(), p);
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("available: " + f.getName());
						
					}
				}
			}
		}
	}
	
	public static void addPlugin(Plugin p) throws ClassNotFoundException {
		Boolean contains = availablePlugins.containsKey(p.getPluginName());
		if(!contains) {
			availablePlugins.put(p.getPluginName(), p);
			System.out.println("Plugin " + p.getPluginName() + " added");
		}
		
		
		//if(Resource.availablePlugins.contains((Object)p))
			//p.run();
		//else
			//System.out.println("no");
		
		/*if(!loadedPlugins.contains(p))
			loadedPlugins.add(p);
		else
			System.out.println("Plugin already loaded!");
		
		if(availablePlugins.contains(p))
			availablePlugins.remove(p);*/
		
	}
	
	public static void loadPlugin(String name) {
		if(availablePlugins.containsKey(name) && !loadedPlugins.containsKey(name)) {
			Plugin p = (Plugin)availablePlugins.get(name);
			
			loadedPlugins.put(name, p);
			
			//availablePlugins.remove(name);
			
			p.run();
			p.setActive(true);
			System.out.println("Plugin loaded: " + name);
		} else {
			System.out.println("kein Plugin mit dem Name '"+ name +"' gefunden");
		}
			
		//if(Resource.availablePlugins.contains((Object)p))
			//p.run();
		//else
			//System.out.println("no");
		
		/*if(!loadedPlugins.contains(p))
			loadedPlugins.add(p);
		else
			System.out.println("Plugin already loaded!");
		
		if(availablePlugins.contains(p))
			availablePlugins.remove(p);*/
		
	}
	
	public static void unloadPlugin(Plugin p) {
		
	}
	
	public static void unloadPlugin(String p) {
		
	}
	
	public static Plugin getPlugin(File f) throws ClassNotFoundException {
		
		URL[] u = new URL[1];
		if(f.exists()) {
			if(f.getName().endsWith(".jar")) {
				try {
					u[0] = f.toURI().toURL();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				URLClassLoader loader = new URLClassLoader(u);
					
				String classtoload = "AppsPlugin";
				Class loadedClass = loader.loadClass(classtoload);
				
				try {
					Plugin p = (Plugin)loadedClass.newInstance();
					return p;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("available: " + f.getName());
				
			}
		}
		return null;
	}
	
	public static Plugin getPlugin(String name) {
		return availablePlugins.get(name);
	}
	
}
