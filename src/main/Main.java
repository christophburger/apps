package main;

import javax.swing.UIManager;

import control.PluginManager;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		new PluginManager();
		
		new Window();
	}
	
}
