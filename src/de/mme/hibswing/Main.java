package de.mme.hibswing;

import java.awt.EventQueue;

import de.mme.hibswing.views.MainView;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
