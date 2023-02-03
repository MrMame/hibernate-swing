package de.mme.hibswing.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.mme.hibswing.controllers.MainWindowController;
import de.mme.hibswing.dataAccess.entities.Person;
import de.mme.hibswing.dataAccess.services.PersonService;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private MainWindowController _controller = new MainWindowController();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 858);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		// Person List
		DefaultListModel<Person> personsList = new DefaultListModel<>();
		JList<Person> lstAusgabe = new JList(personsList);		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 414, 196);
		scrollPane.setViewportView(lstAusgabe);
		lstAusgabe.setLayoutOrientation(JList.VERTICAL);
		frame.getContentPane().add(scrollPane);
		
		
		
		
		// Button - GetPersons
		JButton btnGetAllPersons = new JButton("GetPersons");
		btnGetAllPersons.setBounds(10, 11, 112, 32);
		frame.getContentPane().add(btnGetAllPersons);
		btnGetAllPersons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Person p : _controller.getAllPersons()){
					personsList.addElement(p);
				}		
			}
		});

		
		// Reset DB
		JButton btnResetDb = new JButton("Rebuild_DB");
		btnResetDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_controller.rebuildDB();
			}
		});
		btnResetDb.setBounds(132, 10, 95, 33);
		frame.getContentPane().add(btnResetDb);
		
		
	}
}
