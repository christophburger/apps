package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MyMenuItem extends JMenuItem implements ActionListener {

	private Plugin p;
	
	public MyMenuItem(Plugin p) {
		this.p = p;
		
		setText(p.getPluginName());
		
		addActionListener(this);
	}
	
	 @Override
	public void actionPerformed(ActionEvent e) {
		p.run();
	}
}
