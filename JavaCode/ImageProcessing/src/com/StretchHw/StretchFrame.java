package com.StretchHw;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StretchFrame extends JFrame {
	JPanel cotrolPanelMain = new JPanel();
	JPanel cotrolPanelShow = new JPanel();;
	ImagePanel imagePanel;
	ImagePanel imagePanel2;
	JButton btnShow = new JButton("Show Original Image");
	JButton btnStretch1 = new JButton("Stretch 1 (min-max)");
	JButton btnStretch2 = new JButton("Stretch 2 (histogram)");
	String filename = "file/Munich_gray_dark.png";
//	String filename = "file/Munich_gray_dark_white_noised.png";
	int[][][] data;
	int[][][] newData;
	int height;
	int width;
	BufferedImage img = null;

	StretchFrame() {
		setBounds(0, 0, 1500, 1500);
		getContentPane().setLayout(null);
		setTitle("Image Stretching");
		cotrolPanelMain = new JPanel();
		cotrolPanelMain.setLayout(new GridLayout(6, 1));
		cotrolPanelShow.add(btnShow);
		cotrolPanelShow.add(btnStretch1);
		cotrolPanelShow.add(btnStretch2);
		cotrolPanelMain.add(cotrolPanelShow);
		cotrolPanelMain.setBounds(0, 0, 1200, 200);
		getContentPane().add(cotrolPanelMain);
		imagePanel = new ImagePanel();
		imagePanel.setBounds(20, 220, 720, 620);
		getContentPane().add(imagePanel);
		imagePanel2 = new ImagePanel();
		imagePanel2.setBounds(750, 220, 720, 520);
		getContentPane().add(imagePanel2);

		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				makeData();
				Util.drawImg(imagePanel, img);
			}
		});

		btnStretch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int min = data[0][0][0];
				int max = data[0][0][0];
				newData = new int[height][width][3];
				for (int j = 0; j < height; j++) {
					for (int i = 0; i < width; i++) {
						if (data[j][i][0] < min)
							min = data[j][i][0];
						if (data[j][i][0] > max)
							max = data[j][i][0];
					}
				}
				System.out.println("min = " + min + "  max = " + max);
				for (int j = 0; j < height; j++) {
					for (int i = 0; i < width; i++) {
						double tmp = 255.0 * (1.0 * data[j][i][0] - min) / (max - min);
						int newValue = Util.checkPixelBounds((int) Math.round(tmp));
						for (int c = 0; c < 3; c++) {
							newData[j][i][c] = newValue;
						}
					}
				}
				Util.drawImg(imagePanel2, newData);
			}
		});

		btnStretch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//put your code here
				int[] hist = histogram();
				int[] newRGB = new int[256];
				int currentSum = 0;
//				System.out.println(height + "\t" + width);
				double totalPix = height * width;
				for(int i=0; i<hist.length; i++){
					currentSum += hist[i];
//					System.out.println(currentSum + "\t" + totalPix);
					newRGB[i] = (int)(currentSum / totalPix * 255);
				}
//				System.out.println(Arrays.toString(hist));
//				System.out.println(Arrays.toString(newRGB));
				newData = new int[height][width][3];
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						int newValue = Util.checkPixelBounds(newRGB[data[i][j][0]]);
						for (int c = 0; c < 3; c++) {
//							System.out.println(data[i][j][0] + "\t" + newRGB[data[i][j][0]]);
							newData[i][j][c] = newValue;
						}
					}
				}
				Util.drawImg(imagePanel2, newData);
			}
			int[] histogram() {
				int[] histogram = new int[256];
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						histogram[data[i][j][0]]++;
					}
				}
				return histogram;
			}
		});

	}// end of the constructor

	void makeData() {
		try {
			img = ImageIO.read(new File(filename));
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
		JFrame frame = new StretchFrame();
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
