package com.edge;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EdgeFrame extends JFrame {
	JPanel cotrolPanelMain = new JPanel();
	JPanel cotrolPanelShow = new JPanel();;
	JPanel cotrolPanelSlider = new JPanel();
	JPanel cotrolPanelEdgeEnh = new JPanel();
	ImagePanel imagePanel;
	ImagePanel imagePanel2;
	JLabel lbOp = new JLabel("                        Option: ");
	JRadioButton radDet = new JRadioButton("Edge Detection", true);
	JRadioButton radEnh = new JRadioButton("Edge Enhancement");
	ButtonGroup radGroup = new ButtonGroup();
	JButton btnShow;
	JSlider sliderThresh;
	JTextField tfThresh = new JTextField(5);
	JLabel labThreshLow = new JLabel("Detection (Threshold): Low");
	JLabel labThreshHigh = new JLabel("High");
	JSlider sliderThreshEnh;
	JTextField tfThreshEnh = new JTextField(5);
	JLabel labThreshLowEnh = new JLabel("               Enhancement (Threshold): Low");
	JLabel labThreshHighEnh = new JLabel("High");
	final int[][][] data;
	int height;
	int width;
	BufferedImage img = null;

	EdgeFrame() {
		setBounds(0, 0, 1500, 1500);
		getContentPane().setLayout(null);
		setTitle("Edge Detection and Enhancement (Homework)");
		try {
			img = ImageIO.read(new File("file/Munich.png"));
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
		btnShow = new JButton("Show Original");
		cotrolPanelMain = new JPanel();
		cotrolPanelMain.setLayout(new GridLayout(6, 1));
		cotrolPanelShow.add(btnShow);
		radGroup.add(radDet);
		radGroup.add(radEnh);
		cotrolPanelShow.add(lbOp);
		cotrolPanelShow.add(radDet);
		cotrolPanelShow.add(radEnh);
		cotrolPanelMain.add(cotrolPanelShow);
		sliderThresh = new JSlider(0, 10000, 1000);
		cotrolPanelSlider.add(labThreshLow);
		cotrolPanelSlider.add(sliderThresh);
		cotrolPanelSlider.add(labThreshHigh);
		tfThresh.setText("1000");
		cotrolPanelSlider.add(tfThresh);
		sliderThreshEnh = new JSlider(0, 10, 2);
		cotrolPanelSlider.add(labThreshLowEnh);
		cotrolPanelSlider.add(sliderThreshEnh);
		cotrolPanelSlider.add(labThreshHighEnh);
		tfThreshEnh.setText("2");
		cotrolPanelSlider.add(tfThreshEnh);
		cotrolPanelMain.add(cotrolPanelSlider);
		cotrolPanelMain.setBounds(0, 0, 1200, 200);
		getContentPane().add(cotrolPanelMain);
		imagePanel = new ImagePanel();
		imagePanel.setBounds(20, 220, 720, 620);
		getContentPane().add(imagePanel);
		imagePanel2 = new ImagePanel();
		imagePanel2.setBounds(750, 220, 720, 620);
		getContentPane().add(imagePanel2);

		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Util.drawImg(imagePanel, img);
			}
		});

		radEnh.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() == radEnh && radEnh.isSelected()) {
					doEnhance();
				}
			}
		});

		radDet.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() == radDet && radDet.isSelected()) {
					doDetect();
				}
			}
		});

		sliderThresh.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (radDet.isSelected() == false)
					return;
				doDetect();
			}
		});

		sliderThreshEnh.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (radEnh.isSelected() == false)
					return;
				doEnhance();
			}
		});
	} // end of the constructor

	int dif(int currentPos, int nextPos) {
		return nextPos - currentPos;
	}

	void doDetect() {
		// put your code here
		tfThresh.setText(sliderThresh.getValue()+"");
		int thr = Integer.parseInt(tfThresh.getText());
		int[][][] newData = new int[height][width][3];
		int[][] gray = new int[height][width];
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				gray[y][x] = Util.covertToGray(data[y][x][0],data[y][x][1],data[y][x][2]);
			}
		}
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				int tempX = dif(gray[y][x], gray[y][Util.checkImageBounds(x+1,width)]);
				int tempY = dif(gray[y][x], gray[Util.checkImageBounds(y+1,height)][x]);
				if(tempX * tempX + tempY*tempY >= thr){
					for(int i=0;i <3; i++) newData[y][x][i] = 255;
				}
			}
		}
		Util.drawImg(imagePanel2,newData);
	}

	void doEnhance() {
		// put your code here
		tfThreshEnh.setText(sliderThreshEnh.getValue()+"");
		int thrEnh = Integer.parseInt(tfThreshEnh.getText());
		int[][] mask = {{ 0, -1, 0},
						{ -1, 4, -1},
						{ 0, -1, 0}};
		int[][][] newData = new int[height][width][3];
		for(int x=1; x<width-1; x++){
			for(int y=1; y<height-1; y++){
				for(int i=0; i< 3; i++){
					int sum = mask[0][0] * data[y-1][x-1][i] + mask[0][1] * data[y-1][x][i];
					sum += mask[0][2] * data[y+1][x+1][i] + mask[1][0] * data[y][x-1][i];
					sum += mask[1][1] * data[y][x][i] + mask[1][2] * data[y][x+1][i];
					sum += mask[2][0] * data[y+1][x-1][i] + mask[2][1] * data[y+1][x][i];
					sum += mask[2][2] * data[y+1][x-1][i];
					newData[y][x][i] = Util.checkPixelBounds((thrEnh * sum + data[y][x][i]));
				}
			}
		}
		Util.drawImg(imagePanel2, newData);
	}

	public static void main(String[] args) {
		EdgeFrame frame = new EdgeFrame();
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}// end of the class
