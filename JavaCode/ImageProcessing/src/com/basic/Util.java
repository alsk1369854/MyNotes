package com.basic;

import java.awt.image.BufferedImage;
// start Main add code =======================
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
// end Main add code =========================
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	// start Main add code ====================
	final static BufferedImage deepCopyImg(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	// end Main add code ====================




	final static int checkPixelBounds(int value){
		if (value >255) return 255;
		if (value <0) return 0;
		return value;
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
	
	final static int checkImageBounds(int value, int length){
		 if (value>length-1) return length-1;
		 else if (value <0) return 0;
		 else return value;
	}
	
	final static double getHueFromRGB(int r, int g, int b){
		 
		
		double num = 0.5*((r-g)+(r-b));
		double den = Math.sqrt((r-g)*(r-g) + (r-b)*(g-b));
		if (den == 0) return 0;
		double theta = 180*Math.acos(num/den)/Math.PI;
		
		if (g <= b)
			theta = 360 - theta;
		return theta;
	}
	final static double getIntFromRGB(int r, int g, int b){
		return (r+g+b)/(3.0*255.0);
	}
	final static double getSatFromRGB(int r, int g, int b){
		double min = r;
		if (g<=b && g<=r)
			min = g;
		if (b<=r && b<=g)
			min = b;
		
		return 1.0 - (3*min/(r+g+b));
	}
	
	
	final static void makeFile(int [][][] writedData, BufferedImage  img, String filename){
		for (int y=0; y<img.getHeight(); y++){
			for (int x=0; x<img.getWidth(); x++){
				 int rgb = Util.makeColor(writedData[y][x][0], writedData[y][x][1], writedData[y][x][2]);
	             img.setRGB(x,y,rgb);
			}
		}
   
   try {
	    File outputfile = new File(filename);
	    ImageIO.write(img, "png", outputfile);
	} catch (IOException e) {
	
	}
	
	}
	
	}