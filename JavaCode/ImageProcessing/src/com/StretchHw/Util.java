package com.StretchHw;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Util {
	static BufferedImage imgLeft;
	static BufferedImage imgRight;
	
	final static int checkPixelBounds(int value){
		if (value >255) return 255;
		if (value <0) return 0;
		return value;
 	} 

	final static int checkImageBounds(int value, int length){
		 if (value>length-1) return length-1;
		 else if (value <0) return 0;
		 else return value;
	}

	//get red channel from colorspace (4 bytes)
	final static int getR(int rgb){
		  return checkPixelBounds((rgb & 0x00ff0000)>>>16);	
    }

	//get green channel from colorspace (4 bytes)
	final static int getG(int rgb){
	  return checkPixelBounds((rgb & 0x0000ff00)>>>8);
	}
	
	//get blue channel from colorspace (4 bytes)
	final static int getB(int rgb){
		  return  checkPixelBounds(rgb & 0x000000ff);
	}
	
	final static int makeColor(int r, int g, int b){
		return (255<< 24 | r<<16 | g<<8 | b);
		
	}
	
	final static int covertToGray(int r, int g, int b){
		return checkPixelBounds((int) (0.2126 * r + 0.7152 * g + 0.0722 * b));		
	}
	
	final static double [] affine(double[][] a, double[] b){
		int aRow = a.length;
		int bRow = b.length;
		double[] result = new double[aRow];
       	
		for (int i=0; i<aRow; i++){
			for (int j=0; j<bRow; j++){
					result[i] +=  a[i][j]*b[j]; 
			}
		}
		return result;
	}
	
	final static int bilinear(int leftTop, 
			                  int rightTop, 
			                  int leftBottom, 
			                  int rightBottom,
			                  double alpha,
			                  double beta){
		double left = linear(leftTop, leftBottom, alpha);
		double right = linear(rightTop, rightBottom, alpha);
		double value = linear(left, right, beta);
		return checkPixelBounds((int)value);
	}
			
	final static double linear(double v1, double v2, double weight){
		return v1+(v2-v1)* weight;
	}

	
	
	static BufferedImage makeImg(int[][][] newData){
		int height = newData.length;
		int width =  newData[0].length;
		BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        for (int y=0; y<height; y++) {
        	for (int x=0; x<width; x++) {
        		int rgb = Util.makeColor(newData[y][x][0],
        								 newData[y][x][1], 
        								 newData[y][x][2]);
        		newImg.setRGB(x, y, rgb);
        	}
        	
        }
        return newImg;
	}
	
	static void drawImg(ImagePanel panel, BufferedImage img) {
		Graphics g = panel.getGraphics();
		panel.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	static void drawImg(ImagePanel panel, int[][][] newData) {
		BufferedImage img = makeImg(newData);
		drawImg(panel, img);
	}
	
}