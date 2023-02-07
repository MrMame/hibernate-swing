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
import java.util.ArrayList;
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

import antlr.collections.impl.Vector;

import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.AbstractListModel;

public class MainView implements IView{

	private JFrame _frame;
	
	private MainWindowController _mainWindowController;
	private PersonsRequest _personsModel;
	
	private JList _outputList;


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
		panel.setLayout(new BorderLayout());
		
	
		
		JPanel panel_1 = new JPanel();
		_frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		
		
		createOutputList(panel);
		
		
		JButton btnPersonCreate = new JButton("Create Person");
		btnPersonCreate.setMaximumSize(new Dimension(150, 40));
		btnPersonCreate.setSize(new Dimension(60, 35));
		btnPersonCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonCreate);
		
		JButton btnPersonDelete = new JButton("Delete Person");
		btnPersonDelete.setMaximumSize(new Dimension(150, 40));
		btnPersonDelete.setSize(new Dimension(60, 35));
		btnPersonDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPersonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandDeleteSelectedPersons();
			}
		});
		panel_1.add(btnPersonDelete);
		
		JButton btnPersonUpdate = new JButton("Update Person");
		btnPersonUpdate.setMaximumSize(new Dimension(150, 40));
		btnPersonUpdate.setSize(new Dimension(60, 35));
		btnPersonUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnPersonUpdate);
		
		JButton btnPersonRequest = new JButton("Request Person");
		btnPersonRequest.setMaximumSize(new Dimension(150, 40));
		btnPersonRequest.setSize(new Dimension(60, 35));
		btnPersonRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandGetAllPersons();
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
		
		JMenu mnNewMenu_1 = new JMenu("Database");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reset");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandResetDB();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
	
	
		
	}

	
	
	
	
	private void createOutputList(JPanel panel) {
		// Create new JList
		_outputList = new JList();
		// Create scrollable container that contains the OutputList
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(_outputList);
		_outputList.setLayoutOrientation(JList.VERTICAL);
		// Add the scrollcontainer to the panel
		panel.add(scrollPane,BorderLayout.CENTER);
	}

	/*
	 * ########################################## Interfaces and Overrides ###########################################################
	 * */
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		_frame.setVisible(true);
	}
	
	
	/*
	 * ########################################## EventHandler ###########################################################
	 * */

	
	private void onPersonsChangedEvent() {
		DefaultListModel<Person> model = new DefaultListModel<>();
		for(Person p: _personsModel.getPersons()) {
			model.addElement(p);
		}
		_outputList.setModel(model);
	}
	
	
	/*
	 * ########################################## COMMANDS ###########################################################
	 * */
	
	private void commandResetDB() {
		_mainWindowController.ResetDB();
	}
	private void commandGetAllPersons() {
		_mainWindowController.getAllPersons();
	}
	private void commandDeleteSelectedPersons() {
		
		ArrayList<Person> persons = new ArrayList<>();
		int[] indc = _outputList.getSelectedIndices();
		for(int idx:indc) {
			persons.add((Person)_outputList.getModel().getElementAt(idx));
		}
		_mainWindowController.deletePersons(persons);
	}
	
	
}
