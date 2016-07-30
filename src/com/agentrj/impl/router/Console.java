package com.agentrj.impl.router;

/*
 * @(#)Console.java
 * 
 * IBM Confidential-Restricted
 * 
 * OCO Source Materials
 * 
 * 03L7246 (c) Copyright IBM Corp. 1996, 1998
 * 
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import jeans.trayicon.*;

public class Console extends Frame implements ActionListener {
	private Button _clear_button = new Button("Clear");
	private Button _close_button = new Button("Close");
	private TextArea _log_text_area = new TextArea(15, 82);
	private int _max_chars;
	private int _cur_chars;
	private WindowsTrayIcon	icon;
	private String name;
	public Console(String name) {
		super(name);
		this.name =name;
		
		redirect();
		
		try{	
			WindowsTrayIcon.initTrayIcon(name);
			icon = new WindowsTrayIcon(loadImage("resources/router.gif"),16,16);
			icon.setToolTipText(name);
			icon.setVisible(true);
			icon.setPopup(makePopup());
			icon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(!isVisible());
					if(isVisible())
						show();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
				
		add("Center", _log_text_area);
		Panel p = new Panel();

		p.setLayout(new BorderLayout());
		p.add("West", _clear_button);
		p.add("East", _close_button);
		add("South", p);
		pack();

		_clear_button.addActionListener(this);
		_close_button.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				//icon.setVisible(false);
				//System.exit(0);
				setVisible(false);
				//setVisible(false);
			} 
		});
		
		//this.setIconImage(loadImage("resources/router.gif"));
		this.setVisible(false);
		
	}
	
	
	public TrayIconPopup makePopup() {
		
		// Make new popup menu
		TrayIconPopup popup = new TrayIconPopup();
		// Add show, about, submenu, separator & exit item
		TrayIconPopupSimpleItem item = new TrayIconPopupSimpleItem("&Show");
		// Each menu item can have it's own ActionListener
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				show();
			}
		});
		popup.addMenuItem(item);
		
		// Create a submenu with title enable and items check 1 & check 2
		TrayIconPopup sub = new TrayIconPopup("&Enable");
		// Create and add two checkbox menu items
		TrayIconPopupCheckItem chk = new TrayIconPopupCheckItem("Check &1");
		sub.addMenuItem(chk);
		chk = new TrayIconPopupCheckItem("Check &2");
		sub.addMenuItem(chk);
		// Add submenu to the main menu
		popup.addMenuItem(sub);
		// Add a separator
		TrayIconPopupSeparator sep = new TrayIconPopupSeparator();
		popup.addMenuItem(sep);
		// Add exit item
		item = new TrayIconPopupSimpleItem("E&xit");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowsTrayIcon.cleanUp();
				System.exit(0);
			}
		});
		popup.addMenuItem(item);
		return popup;
	}
	
	public Image loadImage(String fileName) 
	{
		return Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(fileName));
	}
	
	
	
	public void actionPerformed(ActionEvent ev) {
		if (_close_button.getActionCommand().equals(ev.getActionCommand())) {
			icon.setVisible(false);
			System.exit(0);
			//setVisible(false);
		} else if (_clear_button.getActionCommand()
		.equals(ev.getActionCommand())) {

			// String str = _log_text_area.getText();
			_log_text_area.setText("");
		} 
	}
	public void redirect() {
		LogWriter lw = new LogWriter(_log_text_area);
		java.io.PrintStream ps = new java.io.PrintStream(lw);

		System.setOut(ps);
		System.setErr(ps);
	}
}
