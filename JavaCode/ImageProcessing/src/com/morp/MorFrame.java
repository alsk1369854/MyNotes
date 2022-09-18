package com.morp;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MorFrame extends JFrame {
    private final String string = "file/mor_1.png";
//    private final String string = "file/mor_2.png";
//    private final String string = "file/mor_3.png";
    String filename = string;
    int count;
    JPanel cotrolPanel;
    JPanel imagePanelLeft;
    JPanel imagePanelRight;
    int[][] binary;
    JButton btnShow;
    JButton btnDilate;
    JButton btnErode;
    JButton btnOpen;
    JButton btnClose;
    JButton btnReset;
    JLabel lbCount;
    JTextField tfCount;
    JLabel lbOpenCount;
    JTextField tfOpenCount;
    JLabel lbCloseCount;
    JTextField tfCloseCount;
    int[][][] data;
    int height;
    int width;
    static BufferedImage img = null;
    static BufferedImage imgMor = null;

    MorFrame() {
        setTitle("Morphological Image Processing (Homework)");
        loadImg();
        setLayout(null);
        btnShow = new JButton("Show Original Image");
        btnDilate = new JButton("Dilate");
        btnErode = new JButton("Erode");
        lbCount = new JLabel("Count");
        tfCount = new JTextField(5);
        tfCount.setEditable(false);
        btnOpen = new JButton("Open");
        lbOpenCount = new JLabel("Times (Open)");
        tfOpenCount = new JTextField(5);
        btnClose = new JButton("Close");
        lbCloseCount = new JLabel("Times (Close)");
        tfCloseCount = new JTextField(5);
        btnReset = new JButton("Reset");
        cotrolPanel = new JPanel();
        cotrolPanel.setBounds(0, 0, 1500, 200);
        getContentPane().add(cotrolPanel);
        cotrolPanel.add(btnShow);
        cotrolPanel.add(btnDilate);
        cotrolPanel.add(btnErode);
        cotrolPanel.add(lbCount);
        cotrolPanel.add(tfCount);
        cotrolPanel.add(btnOpen);
        cotrolPanel.add(lbOpenCount);
        cotrolPanel.add(tfOpenCount);
        cotrolPanel.add(btnClose);
        cotrolPanel.add(lbCloseCount);
        cotrolPanel.add(tfCloseCount);
        cotrolPanel.add(btnReset);
        imagePanelLeft = new ImagePanel();
        imagePanelLeft.setBounds(0, 120, 700, 700);
        getContentPane().add(imagePanelLeft);
        imagePanelRight = new ImagePanel();
        imagePanelRight.setBounds(750, 120, 700, 700);
        getContentPane().add(imagePanelRight);
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                loadImg();
                Graphics g = imagePanelLeft.getGraphics();
                g.drawImage(img, 0, 0, null);
                binary = makeBinary(0, 0, 0);
                tfCount.setText(count + "");
            }
        });

        btnDilate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dilate();
                imgMor = makeImgFromBin();
                Graphics g = imagePanelRight.getGraphics();
                g.drawImage(imgMor, 0, 0, null);
                tfCount.setText(++count + "");
            }

        });

        btnErode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                erode();
                imgMor = makeImgFromBin();
                Graphics g = imagePanelRight.getGraphics();
                g.drawImage(imgMor, 0, 0, null);
                tfCount.setText(--count + "");
            }
        });

        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int times = Integer.parseInt(tfOpenCount.getText());
                for (int i = 0; i < times; i++)
                    erode();
                for (int i = 0; i < times; i++)
                    dilate();
                imgMor = makeImgFromBin();
                Graphics g = imagePanelRight.getGraphics();
                g.drawImage(imgMor, 0, 0, null);
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int times = Integer.parseInt(tfCloseCount.getText());
                for (int i = 0; i < times; i++)
                    dilate();
                for (int i = 0; i < times; i++)
                    erode();
                imgMor = makeImgFromBin();
                Graphics g = imagePanelRight.getGraphics();
                g.drawImage(imgMor, 0, 0, null);
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                count = 0;
                tfCount.setText(count + "");
                tfOpenCount.setText("");
                tfCloseCount.setText("");
                img = null;
                imgMor = null;
                loadImg();
                imagePanelLeft.repaint();
                imagePanelRight.repaint();
            }
        });
    }

    int[][] makeBinary(int r, int g, int b) {
        int[][] binary = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (data[i][j][0] == r && data[i][j][1] == g && data[i][j][2] == b)
                    binary[i][j] = 1;
                else
                    binary[i][j] = 0;
            }
        }
        return binary;
    }

    //    [.-1,-1    .0,-1   .1,-1]
    //    [.-1,0             .1,0]
    //    [.-1,1     .0,1    .1,1]
    static int[] block = {1, 0, 1, 1, -1, 0, -1, -1, 1};

    void dilate() {
        //put your code here
        int[][] newBinary = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (binary[i][j] == 1) {
                    for(int k=1; k<block.length; k++){
                        int y = Util.checkImageBounds(i+block[k-1],height);
                        int x = Util.checkImageBounds(j+block[k],width);
                        newBinary[y][x] = 1;
                    }
                }
            }
        }
        binary = newBinary;
    }
    void erode() {
        //put your code here
        int[][] newBinary = new int[height][width];
        for(int[] item:newBinary) Arrays.fill(item, 1);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (binary[i][j] == 0) {
                    for(int k=1; k<block.length; k++){
                        int y = Util.checkImageBounds(i+block[k-1],height);
                        int x = Util.checkImageBounds(j+block[k],width);
                        newBinary[y][x] = 0;
                    }
                }
            }
        }
        binary = newBinary;
    }

    BufferedImage makeImgFromBin() {
        int[][][] newData = new int[height][width][3];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (binary[i][j] == 0)
                    newData[i][j][0] = newData[i][j][1] = newData[i][j][2] = 255;
                if (binary[i][j] == 2)
                    newData[i][j][0] = 255;
            }
        }
        return Util.makeImg(newData);
    }

    void loadImg() {
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        height = img.getHeight();
        width = img.getWidth();
        data = new int[height][width][3];
        // newData = new int[height][width][3]; //@@ to be clear 04/30
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
        MorFrame frame = new MorFrame();
        frame.setSize(1500, 1500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
