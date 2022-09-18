package com.Filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FilterFrame extends JFrame {
    JPanel cotrolPanelMain = new JPanel();
    JPanel cotrolPanelShow = new JPanel();
    ;
    JPanel cotrolPanelLP = new JPanel();
    JPanel cotrolPanelHP = new JPanel();
    ImagePanel imagePanel;
    ImagePanel imagePanel2;
    JButton btnShow;
    JButton btnLP = new JButton("Low-Pass(Blur)");
    JButton btnHP = new JButton("High-Pass(Sharp)");
    int[][][] data;
    int[][][] newData;
    int height;
    int width;
    BufferedImage img = null;

    FilterFrame() {
        setBounds(0, 0, 1500, 1500);
        getContentPane().setLayout(null);
        setTitle("Image Filters Homework");
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
        btnShow = new JButton("Show");
        cotrolPanelMain = new JPanel();
        cotrolPanelMain.setBounds(0, 0, 1200, 200);
        getContentPane().add(cotrolPanelMain);
        cotrolPanelShow.add(btnShow);
        cotrolPanelShow.add(btnLP);
        cotrolPanelShow.add(btnHP);
        cotrolPanelMain.add(cotrolPanelShow);
        imagePanel = new ImagePanel();
        imagePanel.setBounds(20, 220, 700, 700);
        getContentPane().add(imagePanel);
        imagePanel2 = new ImagePanel();
        imagePanel2.setBounds(720, 220, 700, 700);
        getContentPane().add(imagePanel2);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Util.drawImg(imagePanel, img);
            }
        });

        btnLP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                newData = new int[height][width][3];
                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {
                        for (int k = 0; k < 3; k++) {
                            int[][] pixels = surroundingPixels(i, j, k);
                            newData[i][j][k] = doLP(pixels);
                        }
                    }
                }
                Util.drawImg(imagePanel2, newData);
                //put your code here
            }
        });


        btnHP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                newData = new int[height][width][3];
                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {
                        for (int k = 0; k < 3; k++) {
                            int[][] pixels = surroundingPixels(i, j, k);
                            newData[i][j][k] = Util.checkPixelBounds(doHP(pixels) + data[i][j][k]);
                        }
                    }
                }
                Util.drawImg(imagePanel2, newData);
                //put your code here
            }
        });
    }// end of the constructor

    int doLP(int[][] pixels) {
        double result = 0.0;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                result += 1.0 * pixels[i][j] / 9;
            }
        }
        return (int) result;
    }

    int doHP(int[][] pixels) {
        double result = 0.0;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                result += (i == 1 && j == 1) ? (8 * pixels[i][j] / 9) : -(1 * pixels[i][j] / 9);
            }
        }
        return (int) result;
    }

    int[][] surroundingPixels(int x, int y, int rgb) {
        int[][] pixels = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pixels[i][j] = data[x - 1 + i][y - 1 + j][rgb];
            }
        }
        return pixels;
    }

    public static void main(String[] args) {
        FilterFrame frame = new FilterFrame();
        frame.setSize(1500, 1500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
//end of the class
