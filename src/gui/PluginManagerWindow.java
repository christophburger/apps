package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.PluginManager;
import data.MyTableModel;
import data.Plugin;

public class PluginManagerWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3310644391744712449L;
	
	JMenuBar bar = new JMenuBar();
	JMenu file = new JMenu("Datei");
	
	String[] items = {"Aktiviert", "Deaktiviert"};
	
	JTable plugins;
	
	DefaultTableModel my;
	
	MouseListener l = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				JTable target = (JTable)e.getSource();
				int row = target.getSelectedRow();
				System.out.println("Double CLicked: " + row);
				String p = (String)target.getValueAt(row, 0);
				Plugin plugin = PluginManager.getPlugin(p);
				System.out.println("plugin: " + plugin.getPluginName());
				if(target.getSelectedColumn() < 2) {
					new PluginInfo(plugin);
				} else {
					//target.getCellRenderer(row, 2).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
					
				}
			}
		}
	};
	
	ActionListener add = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			
			if(chooser.showOpenDialog(PluginManagerWindow.this) == JFileChooser.APPROVE_OPTION) {
				
				File f = chooser.getSelectedFile();
				
				Plugin p = new Plugin() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public String getPluginName() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String getAuthor() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void stop() {
						// TODO Auto-generated method stub
						
					}
				};
				
				try {
					p = PluginManager.getPlugin(f);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(PluginManagerWindow.this, "Plug-In kann nicht installiert werden!");
				}
				
				
				
				try {
					PluginManager.addPlugin(p);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(PluginManagerWindow.this, "Plug-In kann nicht installiert werden!");
				}
				
				String active;
				
				if(p.getActive())
					active = items[0];
				else
					active = items[1];
				
				Object[] newp = {p.getPluginName(), p.getAuthor(), active};
				
				if(!PluginManager.availablePlugins.containsKey(p.getPluginName())) {
					PluginManagerWindow.this.my.addRow(newp);
					
					PluginManagerWindow.this.my.fireTableDataChanged();
					
					PluginManagerWindow.this.reload();
				} else {
					JOptionPane.showMessageDialog(PluginManagerWindow.this, "Plug-In schon vorhanden");
				}
				
				
			}
		}
	};

	public PluginManagerWindow() {
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//plugins = new JTable();
		
		setTitle("Plug-Ins verwalten");
		
		init();
		
		bar.add(file);
		
		JMenuItem addPlugin = new JMenuItem("Plugin hinzufügen...");
		addPlugin.addActionListener(add);
		
		file.add(addPlugin);
		
		setJMenuBar(bar);
		setSize(800, 600);
		setVisible(true);
	}
	
	private void init() {
		String[] columns = {"Name", "Herausgeber", "Status"};
		
		Object[][] plug = new Object[PluginManager.availablePlugins.size()][columns.length];
		
		
		int rows = PluginManager.availablePlugins.size();
		System.out.println("rows: " + rows);
		
		Object[] pl = PluginManager.availablePlugins.values().toArray();
		JComboBox box = new JComboBox(items);
		for(int i = 0; i < pl.length; i++) {
			plug[i][0] = ((Plugin)pl[i]).getPluginName();
			plug[i][1] = ((Plugin)pl[i]).getAuthor();
			if(((Plugin)pl[i]).getActive())
				plug[i][2] = items[0];
			else
				plug[i][2] = items[1];
		}		
		
		plugins = new JTable(rows, columns.length);
		
		my = new MyTableModel(plug, columns);
		
		plugins.setModel(my);
		
		plugins.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(box));
		
		plugins.addMouseListener(l);
		
		System.out.println("tablemodel: " + plugins.getModel());
		
		getContentPane().add(new JScrollPane(plugins));
		
	}
	
	private void reload() {
		//init();
	}
	
}
