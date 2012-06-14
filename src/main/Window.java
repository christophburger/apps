package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.PluginManager;

import data.MyMenuItem;
import data.Plugin;

import resource.Resource;

public class Window extends JFrame {
	
	private JMenuBar bar = new JMenuBar();
	private JMenu open = new JMenu("Öffnen");
	private JMenu plugins = new JMenu("Plugins");
	
	private JMenuItem installPlugin;
	
	private ActionListener install = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser chooser = new JFileChooser();
			
			if(chooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
				
				File f = chooser.getSelectedFile();
				
				Plugin p = PluginManager.getPlugin(f);
				
				PluginManager.loadPlugin(p);
				
			}
			
		}
	};

	public Window() {
		
		System.out.println("available Plugins: " + Resource.availablePlugins.toString());
		
		for(int i = 0; i < Resource.availablePlugins.size(); i++) {
			MyMenuItem item = new MyMenuItem(Resource.availablePlugins.get(i));
			open.add(item);
		}
		
		installPlugin = new JMenuItem("Plugin installieren");
		installPlugin.addActionListener(install);
		
		plugins.add(installPlugin);
		
		bar.add(open);
		bar.add(plugins);
		
		setJMenuBar(bar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
}
