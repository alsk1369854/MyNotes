package com.NoiseRemoval;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {
	boolean readyToPaint;
	
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
	}
	
	 public void paintComponent(Graphics g, int[][][] data, int xOffset, int yOffset){
     super.paintComponent(g);//clean paint area
		 
		 for (int y=0; y<data.length; y++){
		    	for (int x=0; x< data[y].length; x++){
		    		int r = data[y][x][0];
		    		int green =  data[y][x][1];
		            int b =  data[y][x][2];
		            g.setColor(new Color(r, green, b));
		    	
		    	    double[][] matrix ={{1.0, 0.0, xOffset},
		    	    		            {0.0, 1.0, yOffset},
		    	    		            {0.0, 0.0, 1}};
		    	    double[]oldPostion = {x, y, 1.0};
		    	    double[] newPosition = Util.affine(matrix, oldPostion);		
		    	    g.drawLine((int)newPosition[0], 
		    	    		   (int)newPosition[1],
		    	    		   (int)newPosition[0],
		    	    		   (int)newPosition[1]);		
		    	} 
		  	}
	 }
	 
	 public void paintComponent(Graphics g, int data [][][]){
		 paintComponent(g, data, 0, 0);
	 }
	 
	public void paintComponent(Graphics g, int data[][][], Point originalPoints[], Point[] mappedPoints) {
		super.paintComponent(g);
		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[y].length; x++) {
				int r = data[y][x][0];
				int green = data[y][x][1];
				int b = data[y][x][2];
				g.setColor(new Color(r, green, b));
				double[][] matrix = { { 1.0, 0.0, 0 }, { 0.0, 1.0, 0 }, { 0.0, 0.0, 1 } };
				double[] oldPostion = { x, y, 1.0 };
				double[] newPosition = Util.affine(matrix, oldPostion);
				g.drawLine((int) newPosition[0], (int) newPosition[1], (int) newPosition[0], (int) newPosition[1]);
			}
		}
		paintComponent(g, originalPoints, mappedPoints);
	}

	 public void paintComponent(Graphics g, Point[] originalPoints, Point[] mappedPoints){
		 paintComponent(g, originalPoints, new Color(255, 0, 0));
		 paintComponent(g, mappedPoints, new Color(0, 255, 0));
	 }
	 
	 public void paintComponent(Graphics g, Point [] points, Color color){
		 g.setColor(color);
		 g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
		 g.drawLine(points[1].x, points[1].y, points[2].x, points[2].y);
		 g.drawLine(points[2].x, points[2].y, points[3].x, points[3].y);
		 g.drawLine(points[3].x, points[3].y, points[0].x, points[0].y);
	 }
}
