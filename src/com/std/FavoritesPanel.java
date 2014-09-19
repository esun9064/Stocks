/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.std;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Eric
 */
public class FavoritesPanel {
	
	public FavoritesPanel()
	{
		JFrame frame = new JFrame();
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		tabs.addTab("1", pnl1);
		tabs.addTab("2", pnl2);
		frame.add(tabs);
		frame.pack();
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
