
public class Main {
	public static void main(String[] args) {
		SendFlag sendFlag = new SendFlag();
		
		ClientGui cg = new ClientGui(sendFlag);
		cg.initialize();
		cg.setVisible(true);
		
		ServerGui sg = new ServerGui(sendFlag);
		sg.initialize();
		sg.setVisible(true);
		
		OpenWindowsGui owg = new OpenWindowsGui(cg,sg);
		owg.initialize();
		owg.setVisible(true);
	}
}
