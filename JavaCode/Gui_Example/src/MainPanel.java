import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MainPanel extends JPanel {
	boolean isFirst = true;
	boolean isSelected;
	
	public void clean(Graphics g){
		super.paintComponent(g);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (isFirst){
			g.drawLine(0, 0, 600,0);
			int[] xPoints ={100,200,200,150, 100};
			int[] yPoints ={100,100,200,250, 200};
			g.setColor(new Color(0,255, 0));
			g.fillPolygon(xPoints, yPoints, xPoints.length);
			g.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
			g.setColor(new Color(255,0,0));
			g.drawString("NKNU", 50,50);
			isFirst = false;
		}
	}
	
	void paintComponent(Graphics g, String type, int leftTopX, int leftTopY, 
                        int rightBottomX, int rightBottomY){
		super.paintComponent(g);
		g.drawLine(0, 0, 600,0);
		if (type.equals("1"))
			g.drawLine(leftTopX, leftTopY, rightBottomX,rightBottomX);
		else if (type.equals("2"))
			g.drawRect(leftTopX, leftTopY, rightBottomX,rightBottomX);
		else if (type.equals("3"))
			g.drawOval(leftTopX, leftTopY, rightBottomX,rightBottomX);
		else
			g.drawString(type,leftTopX, leftTopY);
	}

}
