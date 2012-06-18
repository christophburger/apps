package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import data.Plugin;

public class PluginInfo extends JFrame {
	
	private String pluginName;
	private String author;

	public PluginInfo(Plugin p) {
		
		pluginName = p.getPluginName();
		author = p.getAuthor();
		
		setLayout(new GridLayout(3, 2));
		
		init();
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(400, 200);
		pack();
		setVisible(true);
	}
	
	private void init() {
		add(new JLabel("Name: "));
		add(new JLabel(pluginName));
		add(new JLabel("Autor"));
		add(new JLabel(author));
		add(new JLabel("Status"));
		add(new JLabel("active"));
	}
	
	
}
