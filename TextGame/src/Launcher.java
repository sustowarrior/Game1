import java.awt.EventQueue;

public class Launcher {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					Principal window = new Principal();
//					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
