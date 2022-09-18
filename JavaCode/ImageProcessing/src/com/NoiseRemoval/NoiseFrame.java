package com.NoiseRemoval;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NoiseFrame extends JFrame {
    String FILE_NAME = "file/Munich_gray.png";
    //    String FILE_NAME = "file/F16.png";
    JPanel cotrolPanelMain = new JPanel();
    JPanel cotrolPanelShow = new JPanel();
    ;
    ImagePanel imagePanel_1;
    ImagePanel imagePanel_2;
    JButton btnShow = new JButton("Show Original Image");
    JButton btnNoise = new JButton("Add Noises");
    JButton btnMedian = new JButton("Remove Noises");
    JSlider slider;
    JLabel lbLess = new JLabel("Remain Less");
    JLabel lbMore = new JLabel("Remain More");

    int[][][] data;
    int[][][] noisedData;
    int height;
    int width;
    BufferedImage img = null;
    int thresh;

    NoiseFrame() {
        setBounds(0, 0, 1500, 1500);
        getContentPane().setLayout(null);
        setTitle("Removing Noises by Median Filter (Practice)");
        img = Util.loadImg(FILE_NAME);
        height = img.getHeight();
        width = img.getWidth();
        data = Util.makeRGBData(img);
        cotrolPanelMain = new JPanel();
        cotrolPanelMain.setLayout(new GridLayout(6, 1));
        cotrolPanelShow.add(btnShow);
        cotrolPanelShow.add(btnNoise);
        cotrolPanelShow.add(btnMedian);
        cotrolPanelShow.add(lbLess);
        slider = new JSlider(0, 200, 100);
        thresh = slider.getValue();
        cotrolPanelShow.add(slider);
        cotrolPanelShow.add(lbMore);
        cotrolPanelMain.add(cotrolPanelShow);
        cotrolPanelMain.setBounds(0, 0, 1200, 200);
        getContentPane().add(cotrolPanelMain);
        imagePanel_1 = new ImagePanel();
        imagePanel_1.setBounds(20, 50, 620, 450);
        getContentPane().add(imagePanel_1);
        imagePanel_2 = new ImagePanel();
        imagePanel_2.setBounds(630, 50, 1230, 450);
        getContentPane().add(imagePanel_2);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Util.drawImg(imagePanel_1, img);
            }
        });

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                thresh = slider.getValue();
            }
        });

        btnNoise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double percent = 0.01;
                noisedData = new int[height][width][3];
                for (int j = 0; j < height; j++)
                    for (int i = 0; i < width; i++)
                        for (int c = 0; c < 3; c++)
                            noisedData[j][i][c] = data[j][i][c];
                int NoOfNoises = (int) (percent * height * width);
                for (int n = 0; n < NoOfNoises; n++) {
                    int randomInt = new Random().nextInt(height * width);
                    int y = Util.checkImageBounds(randomInt / width, height);
                    int x = Util.checkImageBounds(randomInt % width, width);
                    noisedData[y][x][0] = 255;
                    noisedData[y][x][1] = 255;
                    noisedData[y][x][2] = 255;
                }
                Util.drawImg(imagePanel_1, noisedData);
            }
        });

        btnMedian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int[][][] newData = new int[height][width][3];
                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {
                        for (int c = 0; c < 3; c++) {
                            int[] pixels = surroundingPixels(noisedData, i, j, c);
                            Arrays.sort(pixels);
                            newData[i][j][c] = pixels[pixels.length / 2];
                        }
                    }
                }
                Util.drawImg(imagePanel_2, newData);
            }
        });
    }// end of the constructor

    int[] surroundingPixels(int[][][] noisedData, int x, int y, int rgb) {
        int[] pixels = new int[9];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pixels[count++] = noisedData[x - 1 + i][y - 1 + j][rgb];
            }
        }
        return pixels;
    }

    public static void main(String[] args) {
        NoiseFrame frame = new NoiseFrame();
        frame.setSize(1500, 1500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}// end of class
