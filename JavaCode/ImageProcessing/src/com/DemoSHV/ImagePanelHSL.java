package com.DemoSHV;

import javax.swing.*;
import java.awt.*;

public class ImagePanelHSL extends JPanel{
	//@@ override paintComponent(Graphics g)
	public void paintComponent(Graphics g) { 
		if (HSLFrame.imgHSL != null) {
			g.drawImage(HSLFrame.imgHSL, 0, 0, null);
		}
	}
}
