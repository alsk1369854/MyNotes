package com.DemoSHV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HSLConverter extends JFrame {
	JPanel cotrolPanelMain = new JPanel();
	JPanel cotrolPanelShow = new JPanel();
	JPanel cotrolPanelColor = new JPanel();
	JPanel cotrolPanelColorBack = new JPanel();
	JButton btnHSL = new JButton("To HSL");
	JButton btnRGBRecovered = new JButton("To RGB");
	JTextField tfRed = new JTextField(5);
	JTextField tfGreen = new JTextField(5);
	JTextField tfBlue = new JTextField(5);
	JTextField tfRedRecovered = new JTextField(5);
	JTextField tfGreenRecovered = new JTextField(5);
	JTextField tfBlueRecovered = new JTextField(5);
	JTextField tfHue = new JTextField(5);
	JTextField tfSat = new JTextField(5);
	JTextField tfLum = new JTextField(5);
	JLabel lbRedRecovered  = new JLabel("R (Recovered)");
	JLabel lbGreenRecovered  = new JLabel("G (Recovered)");
	JLabel lbBlueRecovered = new JLabel("B (Recovered)");
	JLabel lbRed = new JLabel("R");
	JLabel lbGreen  = new JLabel("G");
	JLabel lbBlue = new JLabel("B");
	JLabel lbHue = new JLabel("H");
	JLabel lbSat = new JLabel("S");
	JLabel lbLum = new JLabel("L");
	JLabel lbSatBeging;
	JLabel lbSatEnd;
	JLabel lbLumBeging;
	JLabel lbLumEnd;
	JLabel lbColor = new JLabel("Color Pre-view");
	JTextField tfColorView = new JTextField(10); 
	
	HSLConverter(){
		setBounds(0, 0, 1500, 1500);
		getContentPane().setLayout(null);
		tfRed.setText("255");
		tfGreen.setText("0");
		tfBlue.setText("0");
	    setTitle("Pixel-Wise Conversion (RGB <=> HSL)");
	    tfColorView.setBackground(Color.WHITE);
		cotrolPanelMain = new JPanel();
		cotrolPanelMain.setLayout(new GridLayout(1,7));
		cotrolPanelShow.add(btnHSL);
		cotrolPanelShow.add(btnRGBRecovered);
		cotrolPanelShow.add(lbColor);
		cotrolPanelShow.add(tfColorView);
		cotrolPanelMain.add(cotrolPanelShow);
		cotrolPanelMain.add(cotrolPanelColor);
		cotrolPanelMain.add(cotrolPanelColorBack);
		
		cotrolPanelColor.add(lbRed);
		cotrolPanelColor.add(tfRed);
		cotrolPanelColor.add(lbGreen);
		cotrolPanelColor.add(tfGreen);
		cotrolPanelColor.add(lbBlue);
		cotrolPanelColor.add(tfBlue);
		cotrolPanelColor.add(lbHue);
		cotrolPanelColor.add(tfHue);
		cotrolPanelColor.add(lbSat);
		cotrolPanelColor.add(tfSat);
		cotrolPanelColor.add(lbLum);
		cotrolPanelColor.add(tfLum);
		cotrolPanelColorBack.setLayout(new GridLayout(6, 1));
		cotrolPanelColorBack.add(lbRedRecovered);
		cotrolPanelColorBack.add(tfRedRecovered);
		cotrolPanelColorBack.add(lbGreenRecovered);
		cotrolPanelColorBack.add(tfGreenRecovered);
		cotrolPanelColorBack.add(lbBlueRecovered);
		cotrolPanelColorBack.add(tfBlueRecovered);
		
		cotrolPanelMain.setBounds(0, 0,1200,150);
		getContentPane().add(cotrolPanelMain);
		
		cotrolPanelMain.add(cotrolPanelColor);
		cotrolPanelMain.add(cotrolPanelColorBack);
		cotrolPanelMain.add(new JPanel());
		cotrolPanelMain.add(new JPanel());

		btnHSL.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int  r= Integer.parseInt(tfRed.getText());
				int  g= Integer.parseInt(tfGreen.getText());
				int  b= Integer.parseInt(tfBlue.getText());
				tfColorView.setBackground(new Color(r, g, b));
				HSL hsl = Util.RGB2HSL(r, g, b);
				tfHue.setText(hsl.h+"");
				tfSat.setText(hsl.s+"");
				tfLum.setText(hsl.l+"");
			}
	    });
	    
	    btnRGBRecovered.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				double  h = Double.parseDouble(tfHue.getText());
				double  s = Double.parseDouble(tfSat.getText());
				double  i = Double.parseDouble(tfLum.getText());
				RGB rgb =  Util.HSL2RGB(h, s, i);
				tfColorView.setBackground(new Color(rgb.r, rgb.g, rgb.b));
				tfRedRecovered.setText(rgb.r+"");
				tfGreenRecovered.setText(rgb.g+"");
				tfBlueRecovered.setText(rgb.b+"");
			}
	    });
	}
	
	public static void main(String[] args) {
		HSLConverter frame = new HSLConverter();
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

