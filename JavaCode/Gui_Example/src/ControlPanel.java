import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {
	JPanel panGraphic;
	JPanel panMouse = new JPanel();
	
	//graphic controls
	JRadioButton radLine, radRectangle, radEllipse, radString;
	ButtonGroup groType;
	JLabel labLeftTopX,  labLeftTopY;
	JLabel labRightBottomX,  labRightBottomY;
	JTextField tfLeftTopX,  tfLeftTopY;
	JTextField tfRightBottomX,  tfRightBottomY;
	JLabel labString;
	JTextField tfString;
	JButton btnExe;
    
	//mouse controls
	JLabel labMouseMoveX = new JLabel("Mouse move X");
	JLabel labMouseMoveY = new JLabel("Mouse move Y");;
	JLabel labMouseClickX = new JLabel("Mouse click X");;
	JLabel labMouseClickY =  new JLabel("Mouse click Y");;
	JLabel labMouseDbClickX = new JLabel("Mouse Db clicks X");
	JLabel labMouseDbClickY = new JLabel("Mouse Db clicks Y");
	JTextField tfMouseMoveX = new JTextField();
	JTextField tfMouseMoveY = new JTextField();
	JTextField tfMouseClickX = new JTextField();
	JTextField tfMouseClickY = new JTextField();
	JTextField tfMouseDbClickX = new JTextField();
	JTextField tfMouseDbClickY= new JTextField();

	//key Controls
	JPanel panKey = new JPanel();
	JLabel labShift = new JLabel(); 
	JLabel labCtrl = new JLabel();
	JLabel labAlt = new JLabel();
	JLabel labArrow = new JLabel();
	JLabel labKey = new JLabel();
	JLabel labHint = new JLabel("鍵盤輸入:");
	JTextField tfText = new JTextField();
	JButton btnReset = new JButton("Reset Graphic");
	JLabel labSelect = new JLabel("選擇模式");
	JRadioButton radGraphic = new JRadioButton ("Graphic", true);
	JRadioButton radMouse = new JRadioButton ("Mouse");
	JRadioButton radKey = new JRadioButton ("Key");
	ButtonGroup groModel = new ButtonGroup();
	
	ControlPanel(){
		tfText.setEnabled(false);
		setLayout(new GridLayout(1,4));
		panGraphic = new JPanel();
		panGraphic.setLayout(new GridLayout(14,1));
		radLine = new JRadioButton("直線", true);
		radRectangle = new JRadioButton("矩形");
		radEllipse = new JRadioButton("楕圓");
		radString = new JRadioButton("字串");
		groType = new ButtonGroup();
		labLeftTopX = new JLabel("左上 X");
		labLeftTopY = new JLabel("左上 Y");
		labRightBottomX = new JLabel("右下 X");
		labRightBottomY = new JLabel("右下 Y");
		
	    tfLeftTopX = new JTextField("0");
	    tfLeftTopY = new JTextField("0");
	    tfRightBottomX = new JTextField("100");
	    tfRightBottomY = new JTextField("100");
		labString = new JLabel("字串");
	    tfString = new JTextField(10);
	    btnExe = new JButton("Go");
	    tfString.setEnabled(false);
	    
	    //graphic panel
	    panGraphic.setLayout(new GridLayout(21,1));
	    panGraphic.add(radLine);
	    panGraphic.add(radRectangle);
	    panGraphic.add(radEllipse);
	    panGraphic.add(radString);
	    panGraphic.add(new JPanel());
	    groType.add(radLine);
	    groType.add(radRectangle);
	    groType.add(radEllipse);
	    groType.add(radString);
	    panGraphic.add(labLeftTopX);
	    panGraphic.add(tfLeftTopX);
	    panGraphic.add(new JPanel());
	    panGraphic.add(labLeftTopY);
	    panGraphic.add(tfLeftTopY);
	    panGraphic.add(new JPanel());
	    panGraphic.add(labRightBottomX);
	    panGraphic.add(tfRightBottomX);
	    panGraphic.add(new JPanel());
	    panGraphic.add(labRightBottomY);
	    panGraphic.add(tfRightBottomY);
	    panGraphic.add(new JPanel());
	    panGraphic.add(labString);
	    panGraphic.add(tfString);
	    panGraphic.add(new JPanel());
	    panGraphic.add(btnExe);
	    
	    //mouse panel
	    panMouse.setLayout(new GridLayout(12,1));
	    panMouse.add(labMouseMoveX);
	    panMouse.add(tfMouseMoveX);
	    panMouse.add(labMouseMoveY);
	    panMouse.add(tfMouseMoveY);
	    panMouse.add(labMouseClickX);
	    panMouse.add(tfMouseClickX);
	    panMouse.add(labMouseClickY);
	    panMouse.add(tfMouseClickY);
	    panMouse.add(labMouseDbClickX);
	    panMouse.add(tfMouseDbClickX);
	    panMouse.add(labMouseDbClickY);
	    panMouse.add(tfMouseDbClickY);
		 
	    //key panel
	    panKey.setLayout(new GridLayout(15,1));
	    panKey.add(labShift); 
	    panKey.add(labCtrl);
	    panKey.add(labAlt);
	    panKey.add(labArrow);
	    panKey.add(labKey);
	    panKey.add(labHint);
	    panKey.add(tfText);
	    panKey.add(new JPanel());
	    panKey.add(btnReset);
	    panKey.add(new JPanel());
	    groModel.add(radGraphic);
	    groModel.add(radMouse);
	    groModel.add(radKey);
	    panKey.add(labSelect);
	    panKey.add(radGraphic);
	    panKey.add(radMouse);
	    panKey.add(radKey);
	    
	   //add c panels
	    add(panGraphic);
	    add(new JPanel());
	    add(panMouse);
	    add(new JPanel());
	    add(panKey);
        
	    //add listeners
	    RadioGraphicListener radioGeaphicListener = new RadioGraphicListener();
	    radLine.addActionListener(radioGeaphicListener);
	    radRectangle.addActionListener(radioGeaphicListener);
	    radEllipse.addActionListener(radioGeaphicListener);
	    radString.addActionListener(radioGeaphicListener);
	    
	    tfText.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				int keyCode = arg0.getKeyCode();
				//if (keyCode >= 0x30 && keyCode<= 0x5A) //直接用 virtual key's 數字 
				if (keyCode >= KeyEvent.VK_0  && keyCode <= KeyEvent.VK_Z)
					labKey.setText((char)keyCode +"");
				else 
					labKey.setText("非數字/英母");

				if (keyCode == KeyEvent.VK_RIGHT)
					labArrow.setText("ARROW: RIGHT ");
				
				if (keyCode == KeyEvent.VK_LEFT)
					labArrow.setText("ARROW: LEFT ");
				
				if (keyCode == KeyEvent.VK_UP)
					labArrow.setText("ARROW: UP ");
				
				if (keyCode == KeyEvent.VK_DOWN)
					labArrow.setText("ARROW: DOWN ");
				
				if (keyCode == KeyEvent.VK_SHIFT)
					labShift.setText("SHIFT Pressed");
			
				
				if (keyCode == KeyEvent.VK_CONTROL)
					labCtrl.setText("CTRL Pressed");
			
				if (keyCode == KeyEvent.VK_ALT)
					labAlt.setText("ALT Pressed");
			}
			
			public void keyReleased(KeyEvent arg0) {
				
				int keyCode = arg0.getKeyCode();
				if (keyCode >= KeyEvent.VK_0  && keyCode <= KeyEvent.VK_Z)
					labKey.setText((char)keyCode +"");
				else 
					labKey.setText("");

				if (keyCode == KeyEvent.VK_RIGHT)
					labArrow.setText("");
				
				if (keyCode == KeyEvent.VK_LEFT)
					labArrow.setText("");
				
				if (keyCode == KeyEvent.VK_UP)
					labArrow.setText("");
				
				if (keyCode == KeyEvent.VK_DOWN)
					labArrow.setText("");
				
				if (keyCode == KeyEvent.VK_SHIFT)
					labShift.setText("");
			
				if (keyCode == KeyEvent.VK_CONTROL)
					labCtrl.setText("");
			
				if (keyCode == KeyEvent.VK_ALT)
					labAlt.setText("");
			}
		});
		
	}
	
	//inner class 
	class RadioGraphicListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Object srcObj = arg0.getSource(); 
			if (!(srcObj instanceof JRadioButton))
				return ;
			
			if (srcObj == radString){
				tfLeftTopX.setEnabled(true);
				tfLeftTopY.setEnabled(true);
				tfLeftTopY.setText("10");
				labLeftTopY.setText("左上 Y (>= 10)");
				tfRightBottomX.setEnabled(false);
				tfRightBottomY.setEnabled(false);
				tfString.setEnabled(true);
			}else if (srcObj == radLine || srcObj == radRectangle || srcObj == radEllipse){
				tfLeftTopX.setEnabled(true);
				tfLeftTopY.setEnabled(true);
				tfRightBottomX.setEnabled(true);
				tfRightBottomY.setEnabled(true);
				tfString.setEnabled(false);
				
				tfLeftTopX.setText("0");
				tfLeftTopY.setText("0");
				tfRightBottomX.setText("100");
				tfRightBottomY.setText("100");
				labLeftTopY.setText("左上 Y");
			}
		}
	}
}
