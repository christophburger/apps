package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import data.MyMenuItem;

import resource.Resource;

public class Window extends JFrame {
	
	private JMenuBar bar = new JMenuBar();
	private JMenu open = new JMenu("Öffnen");
	
	private ActionListener lis = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Resource.availablePlugins.get(0).run();
			System.out.println("hi");
		}
	};

	public Window() {
		
		System.out.println("available Plugins: " + Resource.availablePlugins.toString());
		
		for(int i = 0; i < Resource.availablePlugins.size(); i++) {
			MyMenuItem item = new MyMenuItem(Resource.availablePlugins.get(i));
			open.add(item);
		}
		
		bar.add(open);
		
		setJMenuBar(bar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
}
