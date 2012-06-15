package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import control.PluginManager;
import data.MyTableModel;
import data.Plugin;

public class PluginManagerWindow extends JFrame {
	
	JMenuBar bar = new JMenuBar();
	JMenu file = new JMenu("Datei");
	
	JTable plugins;
	
	ActionListener add = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			
			if(chooser.showOpenDialog(PluginManagerWindow.this) == JFileChooser.APPROVE_OPTION) {
				
				File f = chooser.getSelectedFile();
				
				Plugin p = PluginManager.getPlugin(f);
				
				
				PluginManager.addPlugin(p);
				
				PluginManagerWindow.this.reload();
				
			}
		}
	};

	public PluginManagerWindow() {
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		plugins = new JTable();
		
		init();
		setJMenuBar(bar);
		setSize(800, 600);
		setVisible(true);
	}
	
	private void init() {
		Object[][] plug = new Object[PluginManager.availablePlugins.size()][1];
		
		int rows = PluginManager.availablePlugins.size();
		System.out.println("rows: " + rows);
		
		Object[] pl = PluginManager.availablePlugins.values().toArray();
		for(int i = 0; i < pl.length; i++) {
			plug[i][0] = ((Plugin)pl[i]).getPluginName();
		}
		String[] columns = {"Name"};
		
		plugins.setModel(new MyTableModel());
		plugins = new JTable(plug, columns);
		
		
		bar.add(file);
		
		JMenuItem addPlugin = new JMenuItem("Plugin hinzufügen...");
		addPlugin.addActionListener(add);
		
		file.add(addPlugin);
		
		add(plugins);
		
	}
	
	private void reload() {
		removeAll();
		//init();
	}
	
}
