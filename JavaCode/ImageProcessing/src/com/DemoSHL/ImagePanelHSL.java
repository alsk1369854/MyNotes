package com.DemoSHL;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ImagePanelHSL extends JPanel{
	//@@ override paintComponent(Graphics g)
	public void paintComponent(Graphics g) { 
		if (HSLFrame.imgHSL != null) {
			g.drawImage(HSLFrame.imgHSL, 0, 0, null);
		}
	}
}
