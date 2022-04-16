import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Frame extends JFrame {
	private ControlPanel controlPanel;
	private MainPanel mainPanel;
	enum model {GRAPHIC, MOUSE, KEY};
	model modelState; 
	Frame(){
		init();
	}
	
	void init(){
		//main layout structure 
		modelState = model.GRAPHIC;
		mainPanel = new MainPanel();
		controlPanel = new ControlPanel();
		setLayout (new GridLayout(2,1));
		add(controlPanel);
		add(mainPanel);
		
		//add listeners
		RadioModelListener radioModelListener = new RadioModelListener();
		controlPanel.radGraphic.addActionListener(radioModelListener);
		controlPanel.radMouse.addActionListener(radioModelListener);
		controlPanel.radKey.addActionListener(radioModelListener);
		
		controlPanel.btnExe.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (modelState != model.GRAPHIC)
						return;
					int leftTopX = Integer.parseInt(controlPanel.tfLeftTopX.getText());
					int leftTopY = Integer.parseInt(controlPanel.tfLeftTopY.getText());
					int rightBottomX = Integer.parseInt(controlPanel.tfRightBottomX.getText());
					int rightBottomY = Integer.parseInt(controlPanel.tfRightBottomY.getText());
					Graphics g = mainPanel.getGraphics();
					String type = null;
					if (controlPanel.radLine.isSelected())
						type = "1";
					else if ((controlPanel.radRectangle.isSelected()))
						type = "2";
					else if ((controlPanel.radEllipse.isSelected()))
						type = "3";
					else if ((controlPanel.radString.isSelected()))
						type = controlPanel.tfString.getText();
						
					mainPanel.paintComponent(g, type, leftTopX, leftTopY, 
								                       rightBottomX, rightBottomY);
				}
		    });
		
		
		controlPanel.btnReset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.isFirst = true;
				mainPanel.repaint();
				
			}});    
		
		mainPanel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (modelState != model.MOUSE)
					return;
				int clickCount = arg0.getClickCount();
				if (clickCount== 1){
					controlPanel.tfMouseClickX.setText (arg0.getX() +"");
					controlPanel.tfMouseClickY.setText (arg0.getY() +"");
					controlPanel.tfMouseDbClickX.setText ("");
					controlPanel.tfMouseDbClickY.setText ("");
				}else if (clickCount== 2){
					controlPanel.tfMouseDbClickX.setText (arg0.getX() +"");
					controlPanel.tfMouseDbClickY.setText (arg0.getY() +"");
					controlPanel.tfMouseClickX.setText ("");
					controlPanel.tfMouseClickY.setText ("");
				}
			}
			public void mouseEntered(MouseEvent arg0) {
				if (modelState != model.MOUSE)
					return;
				mainPanel.setBackground(new Color(0, 255, 0));        
			}
			public void mouseExited(MouseEvent arg0) {
				if (modelState != model.MOUSE)
					return;
				mainPanel.setBackground(new Color(255, 255, 255));
			    controlPanel.tfMouseMoveX.setText("");
				controlPanel.tfMouseMoveY.setText("");
			}
		});
		
		mainPanel.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent arg0) {
				if (modelState != model.MOUSE)
					return;
				controlPanel.tfMouseMoveX.setText (arg0.getX() +"");
				controlPanel.tfMouseMoveY.setText (arg0.getY() +"");
			}	
		});
	}
	
	//inner class
	class RadioModelListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Object srcObj = arg0.getSource(); 
			if (!(srcObj instanceof JRadioButton))
				return ;
			
			if (srcObj == controlPanel.radGraphic){
				modelState = model.GRAPHIC;
				cleanMouseItems();
				cleanKeyItems();
			}
			
			if (srcObj == controlPanel.radMouse){
				modelState = model.MOUSE;
				cleanGraphicItems();
				cleanKeyItems();
			}
			if (srcObj == controlPanel.radKey){
				modelState = model.KEY;
				controlPanel.tfText.setEnabled(true);
				cleanGraphicItems();
				cleanMouseItems();
				controlPanel.tfText.requestFocus();
			}
		}
		
		void cleanMouseItems(){
			controlPanel.tfMouseMoveX.setText("");
			controlPanel.tfMouseMoveY.setText("");
			controlPanel.tfMouseClickX.setText("");
			controlPanel.tfMouseClickY.setText(""); 
			controlPanel.tfMouseDbClickX.setText("");
			controlPanel.tfMouseDbClickY.setText("");
		}
		
		void cleanKeyItems(){
			controlPanel.tfText.setText("");
			controlPanel.tfText.setEnabled(false);
		}
		
		void cleanGraphicItems(){
			mainPanel.clean(mainPanel.getGraphics());
		}
	}
}
