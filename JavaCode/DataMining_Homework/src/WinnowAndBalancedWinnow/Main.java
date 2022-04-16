package WinnowAndBalancedWinnow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		int c = 2;
		
		float[] w = new float[12];
		for(int i=0; i<12; i++) {
			w[i] = 1;
		}
		
		Scanner sc = new Scanner(System.in);
		String address = "data\\";
		address += sc.nextLine() + ".txt";
		File file = new File(address);
		String contents = readFile(file);
		
		boolean isFirst = true;
		
		
		int cases = contents.split("\n").length; 
		
		for(int i=0; i<cases; i++) {
			int[] a = new int[13];
			for(int j=0; j<13; j++) {
				a[j] = Integer.parseInt(contents.split("\n")[i].split("\t")[j]);
			}
			if(a[12] == 0 && isFirst) {
				for(int j=0; j<12; j++) {
					if(a[j] == 1) {w[j] *= c;}
					isFirst = false;
				}
			}else if(a[12] == 0) {
				for(int j=0; j<12; j++) {
					if(a[j] == 1) {w[j] /= c;}
				}
			}
		}
		System.out.print("Winnow: ");
		for(int i=0; i<12; i++) {
			System.out.printf("a" + (i+1) + "*" + w[i]);
			if(i != 11) {
				System.out.print(" + ");
			}
		}
		
		
		//System.out.println(contents);
		//System.out.println(cases);
	}
	
	
	static String readFile(File file) {
		String contens = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				contens += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return contens;
	}
}
