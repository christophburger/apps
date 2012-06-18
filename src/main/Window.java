package main;

import gui.PluginManagerWindow;

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

public class Window extends JFrame {
	
	private JMenuBar bar = new JMenuBar();
	private JMenu open = new JMenu("Öffnen");
	private JMenu plugins = new JMenu("Plugins");
	
	private JMenuItem installPlugin;
	
	private ActionListener install = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/*JFileChooser chooser = new JFileChooser();
			
			if(chooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
				
				File f = chooser.getSelectedFile();
				
				Plugin p = PluginManager.getPlugin(f);
				
				
				PluginManager.addPlugin(p);
				
			}*/
			
			new PluginManagerWindow();
			
		}
	};

	public Window() {
		
		init();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
	private void init() {
		for(Plugin p : PluginManager.availablePlugins.values()) {
			MyMenuItem item = new MyMenuItem(p);
			open.add(item);
		}
		
		installPlugin = new JMenuItem("Plug-Ins verwalten");
		installPlugin.addActionListener(install);
		
		setTitle("Tool");
		
		plugins.add(installPlugin);
		
		bar.add(open);
		bar.add(plugins);
		
		setJMenuBar(bar);
	}
	
}
