package com.MotionDetection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MotionDetectionFrame extends JFrame {
    JPanel cotrolPanelMain = new JPanel();
    JPanel cotrolPanelShow = new JPanel();

    ImagePanel imagePanel;
    ImagePanel imagePanel2;
    ImagePanel imagePanel3;
    JButton btnShow = new JButton("Show");
    JButton btnMD = new JButton("移動偵測");
    int[][][] data;
    int[][][] data2;
    int[][][] data3;
    int[][] gray1, gray2;
    int height;
    int width;
    BufferedImage img = null;
    BufferedImage img2 = null;

    MotionDetectionFrame() {
        setBounds(0, 0, 1500, 1500);
        getContentPane().setLayout(null);
        setTitle("Image Move Detect Homework");
        try {
            img = ImageIO.read(new File("file/Munich_mt1.png"));
            img2 = ImageIO.read(new File("file/Munich_mt2.png"));
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        height = img.getHeight();
        width = img.getWidth();
        data = new int[height][width][3];
        data2 = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                data[y][x][0] = Util.getR(rgb);
                data[y][x][1] = Util.getG(rgb);
                data[y][x][2] = Util.getB(rgb);

                int rgb2 = img2.getRGB(x, y);
                data2[y][x][0] = Util.getR(rgb2);
                data2[y][x][1] = Util.getG(rgb2);
                data2[y][x][2] = Util.getB(rgb2);
            }
        }
        gray1 = buildGray(data);
        gray2 = buildGray(data2);

        cotrolPanelMain.setBounds(0, 0, 1200, 200);
        getContentPane().add(cotrolPanelMain);
        cotrolPanelShow.add(btnShow);
        cotrolPanelShow.add(btnMD);
        cotrolPanelMain.add(cotrolPanelShow);
        imagePanel = new ImagePanel();
        imagePanel.setBounds(20, 50, 600, 700);
        getContentPane().add(imagePanel);
        imagePanel2 = new ImagePanel();
        imagePanel2.setBounds(720, 50, 600, 700);
        getContentPane().add(imagePanel2);
        imagePanel3 = new ImagePanel();
        imagePanel3.setBounds(350, 250, 600, 700);
        getContentPane().add(imagePanel3);

        btnShow.addActionListener(e -> {
            Graphics g = imagePanel3.getGraphics();
            imagePanel3.paintComponent(g);
            Util.drawImg(imagePanel, data);
            Util.drawImg(imagePanel2, data2);
        });

        btnMD.addActionListener(e -> {
            data3 = new int[height][width][3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (Math.abs(gray1[y][x] - gray2[y][x]) <= 10) {
                        for (int i = 0; i < 3; i++) data3[y][x][i] = 0;
                    } else {
                        for (int i = 0; i < 3; i++) data3[y][x][i] = 255;
                    }
                }
            }
            Util.drawImg(imagePanel3, data3);
        });
    }// end of the constructor

    int[][] buildGray(int[][][] data) {
        int[][] grayData = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = Util.covertToGray(
                        data[y][x][0],
                        data[y][x][1],
                        data[y][x][2]);
                grayData[y][x] = gray;
            }
        }
        return grayData;
    }

    public static void main(String[] args) {
        MotionDetectionFrame frame = new MotionDetectionFrame();
        frame.setSize(1500, 1500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
//end of the class
