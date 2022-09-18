package com.DemoSHL;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HSLFrame  extends JFrame {
	JPanel cotrolPanelMain = new JPanel();
	JPanel cotrolPanelShow = new JPanel();;
	JPanel cotrolPanelHue = new JPanel();
	JPanel cotrolPanelSat = new JPanel();
	JPanel cotrolPanelLum = new JPanel();
	JPanel cotrolPanelBin = new JPanel();
	JPanel cotrolPanelHSL = new JPanel();
	JPanel imagePanelOrg;  //@@ modified here 
	JPanel imagePanelHSL;  //@@ modified here 
	JButton btnShow;
	JSlider sliderHue;
	JSlider sliderSat;
	JSlider sliderLum;
	JLabel lbHueBeging = new JLabel("-180");
	JLabel lbHueEnd = new JLabel("180");
	JLabel lbSatBeging = new JLabel("-100(%)") ;
	JLabel lbSatEnd =  new JLabel("100(%)") ;;
	JLabel lbLumBeging  = new JLabel("-100(%)") ;;
	JLabel lbLumEnd  = new JLabel("100(%)") ;;
	JTextField tfHueValue = new JTextField(3);
	JTextField tfSatValue = new JTextField(3);
	JTextField tfLumValue = new JTextField(3);
	int[][][] data;
	int height;
	int width;
	static BufferedImage img = null;   //@@ modified here 
	static BufferedImage imgHSL = null; //@@ modified here
	
	HSLFrame() {
		setBounds(0, 0, 1500, 1500);
		getContentPane().setLayout(null);
		setTitle("HSL Demo  04/28/2022");
		btnShow = new JButton("Show Original Image");
		tfHueValue.setText("0");
		tfSatValue.setText("0");
		tfLumValue.setText("0");
		tfHueValue.setEditable(false);
		tfSatValue.setEditable(false);
		tfLumValue.setEditable(false);
		cotrolPanelMain = new JPanel();
		cotrolPanelMain.setLayout(new GridLayout(6, 1));
		sliderHue = new JSlider(-180, 180, 0);
		cotrolPanelShow.add(btnShow);
		cotrolPanelHue.add(lbHueBeging);
		cotrolPanelHue.add(sliderHue);
		cotrolPanelHue.add(lbHueEnd);
		cotrolPanelHue.add(tfHueValue);
		sliderSat = new JSlider(-100, 100, 0);
		cotrolPanelSat.add(lbSatBeging);
		cotrolPanelSat.add(sliderSat);
		cotrolPanelSat.add(lbSatEnd);
		cotrolPanelSat.add(tfSatValue);
		sliderLum = new JSlider(-100, 100, 0);
		cotrolPanelLum.add(lbLumBeging);
		cotrolPanelLum.add(sliderLum);
		cotrolPanelLum.add(lbLumEnd);
		cotrolPanelLum.add(tfLumValue);
		cotrolPanelMain.add(cotrolPanelShow);
		cotrolPanelMain.add(cotrolPanelHue);
		cotrolPanelMain.add(cotrolPanelSat);
		cotrolPanelMain.add(cotrolPanelLum);
		cotrolPanelMain.add(cotrolPanelHSL);
		cotrolPanelMain.add(cotrolPanelBin);
		cotrolPanelMain.setBounds(0, 0, 1200, 200);
		getContentPane().add(cotrolPanelMain);
		imagePanelOrg = new ImagePanelOrg();  //@@ modified here 
		imagePanelOrg.setBounds(0, 220, 700, 700); //@@ modified here 
		getContentPane().add(imagePanelOrg); //@@ modified here 
		imagePanelHSL = new ImagePanelHSL(); //@@ modified here 
		imagePanelHSL.setBounds(750, 220, 700, 700); //@@ modified here 
		getContentPane().add(imagePanelHSL); //@@ modified here 

		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadImg();
				Graphics g = imagePanelOrg.getGraphics(); //@@ modified here 
				g.drawImage(img, 0, 0, null); //@@ modified here 
			}
		});
	    
		sliderHue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tfHueValue.setText(sliderHue.getValue() + "");
				doHSL();
			}
		});

		sliderSat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tfSatValue.setText(sliderSat.getValue() + "");
				doHSL();
			}
		});

		sliderLum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tfLumValue.setText(sliderLum.getValue() + "");
				doHSL();
			}
		});
	}
     
	int [][][] hslAdjust(int[][][] data, double hueOffset, double satOffset, double lumOffset) {
		double hue;
		double sat;
		double lum;
		RGB rgb;
		int [][][] newData=  new int [height][width][3];
		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < width; jj++) {
				int r = data[ii][jj][0];
				int g = data[ii][jj][1];
				int b = data[ii][jj][2];
				HSL hsl = Util.RGB2HSL(r, g, b);
				hue = hsl.h;
				sat = hsl.s;
				lum = hsl.l;
				hue += hueOffset;
				if (satOffset > 0.0)
					sat = Util.linear(sat, 1, satOffset);
				if (satOffset < 0.0)
					sat = sat - satOffset * (0 - sat);
				if (lumOffset > 0.0)
					lum = Util.linear(lum, 1, lumOffset);
				if (lumOffset < 0.0)
					lum = lum - lumOffset * (0 - lum);
				rgb = Util.HSL2RGB(hue, sat, lum);
				newData[ii][jj][0] = rgb.r;
				newData[ii][jj][1] = rgb.g;
				newData[ii][jj][2] = rgb.b;
			}
		}
		return newData;
	}

	void doHSL() {
		double hueOffset = sliderHue.getValue();
		double satOffset = sliderSat.getValue();
		double lumOffset = sliderLum.getValue();
		int [][][] newData = hslAdjust(data, hueOffset, satOffset / 100.0, lumOffset / 100.0);
		imgHSL = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        for (int y=0; y<height; y++) {
        	for (int x=0; x<width; x++) {
        		int rgb = Util.makeColor(newData[y][x][0],
        								 newData[y][x][1], 
        								 newData[y][x][2]);
        		imgHSL.setRGB(x, y, rgb);
        	}
        }
        Graphics g = imagePanelHSL.getGraphics(); //@@ modified here 
		g.drawImage(imgHSL, 0, 0, null); //@@ modified here 
	}

	void loadImg() {
		try {
			// img = ImageIO.read(new File("file/plate.png"));
			img = ImageIO.read(new File("file/munich.png"));
		} catch (IOException e) {
			System.out.println("IO exception");
		}
		height = img.getHeight();
		width = img.getWidth();
		data = new int[height][width][3];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgb = img.getRGB(x, y);
				data[y][x][0] = Util.getR(rgb);
				data[y][x][1] = Util.getG(rgb);
				data[y][x][2] = Util.getB(rgb);
			}
		}
	}
	
	public static void main(String[] args) {
		HSLFrame frame = new HSLFrame();
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
