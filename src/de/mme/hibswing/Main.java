package de.mme.hibswing;

import java.awt.EventQueue;

import de.mme.hibswing.controllers.MainWindowController;
import de.mme.hibswing.model.requests.PersonsRequest;
import de.mme.hibswing.views.MainView;

public class Main {

	private static MainWindowController _mainWindowController;
	private static PersonsRequest _personsRequestModel;
	private static MainView _mainView;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					_personsRequestModel = new PersonsRequest();
					_mainWindowController = new MainWindowController(_personsRequestModel);
					_mainView = new MainView(_mainWindowController,_personsRequestModel);
										
					_mainView.show();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
