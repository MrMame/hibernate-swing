package de.mme.hibswing.views;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.mme.hibswing.controllers.MainWindowController;
import de.mme.hibswing.dataAccess.entities.Person;
import de.mme.hibswing.dataAccess.services.PersonService;
import de.mme.hibswing.model.requests.PersonsRequest;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class MainView implements IView{

	private JFrame _frame;

	JTextArea _textArea; 
	
	private MainWindowController _mainWindowController;
	private PersonsRequest _personsModel;
	
	


	/**
	 * Create the application.
	 */
	public MainView(MainWindowController mainWindowController, PersonsRequest personsModel) {
		
		_personsModel = personsModel;
		_mainWindowController = mainWindowController;
		_mainWindowController.addPersonsChangedListener(()->{
			onPersonsChangedEvent();
		});
	
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		_frame = new JFrame();
		_frame.setBounds(100, 100, 638, 407);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		_frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		_textArea = new JTextArea();
		panel.add(_textArea);
		
		JPanel panel_1 = new JPanel();
		_frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnPersonCreate = new JButton("Create Person");
		btnPersonCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonCreate);
		
		JButton btnPersonDelete = new JButton("Delete Person");
		btnPersonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonDelete);
		
		JButton btnPersonUpdate = new JButton("Update Person");
		btnPersonUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonUpdate);
		
		JButton btnPersonRequest = new JButton("Request Person");
		btnPersonRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllPersons();
			}
		});
		btnPersonRequest.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonRequest);
		
		JMenuBar menuBar = new JMenuBar();
		_frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Connection");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("DB settings...");
		mnNewMenu.add(mntmNewMenuItem);
	
		// Person List
		DefaultListModel<Person> personsList = new DefaultListModel<>();
		
		JList personList = new JList<Person>();	
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		_frame.setVisible(true);
	}
	
	
	private void onPersonsChangedEvent() {
		_textArea.setText(null);
		_textArea.setText(_personsModel.getPersons().toString());
	}
	
	
	private void getAllPersons() {
		_mainWindowController.getAllPersons();
	}
	
	
}
