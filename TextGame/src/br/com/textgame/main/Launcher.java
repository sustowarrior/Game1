package br.com.textgame.main;
import java.awt.EventQueue;

import javax.swing.UIManager;

import br.com.textgame.ui.UiPrincipal;

public class Launcher {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UiPrincipal window = new UiPrincipal();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
