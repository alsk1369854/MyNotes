package com.DemoSHV;

import javax.swing.*;
import java.awt.*;
public class ImagePanelOrg extends JPanel{
	//@@ override paintComponent(Graphics g)
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		if (HSLFrame.img != null) {
			g.drawImage(HSLFrame.img, 0, 0, null);
	}
	}
}
