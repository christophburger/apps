package control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import resource.Resource;

import data.Plugin;

public class PluginManager {
	
	static ArrayList<Plugin> loadedPlugins;
	
	static ArrayList<Plugin> availablePlugins;
	
	private final String PLUGINDIR = "plugins";
	
	public PluginManager() {
		
		loadedPlugins = new ArrayList<Plugin>();
		availablePlugins = new ArrayList<Plugin>();
		
		getAvailablePlugins();
		
		Resource.availablePlugins = availablePlugins;
		
		System.out.println("av: " + availablePlugins);
		
	}
	
	public void getAvailablePlugins() {
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
						try {
							
							String classtoload = "AppsPlugin";
							Class loadedClass = loader.loadClass(classtoload);
							
							try {
								Plugin p = (Plugin)loadedClass.newInstance();
								availablePlugins.add(p);
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("available: " + f.getName());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}
	
	public static void loadPlugin(Plugin p) {
		
		//if(Resource.availablePlugins.contains((Object)p))
			p.run();
		//else
			//System.out.println("no");
		
		if(!loadedPlugins.contains(p))
			loadedPlugins.add(p);
		else
			System.out.println("Plugin already loaded!");
		
		if(availablePlugins.contains(p))
			availablePlugins.remove(p);
		
	}
	
	public void unloadPlugin(Plugin p) {
		
	}
	
	public static Plugin getPlugin(File f) {
		
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
				try {
					
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
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}
	
}
