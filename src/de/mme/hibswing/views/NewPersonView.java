package de.mme.hibswing.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import de.mme.hibswing.dataAccess.entities.Person;

public class NewPersonView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEMail;

	private Person _person;
	
	
	public Person getPerson() {
		return _person;
	}
	
	
	/**
	 * Create the dialog.
	 */
	public NewPersonView() {
		setBounds(100, 100, 571, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("First Name:");
			lblNewLabel.setBounds(20, 24, 102, 22);
			panel.add(lblNewLabel);
			
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setBounds(20, 66, 102, 22);
			panel.add(lblLastName);
			
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(20, 116, 102, 22);
			panel.add(lblEmail);
			
			txtFirstName = new JTextField();
			txtFirstName.setBounds(180, 25, 277, 20);
			panel.add(txtFirstName);
			txtFirstName.setColumns(10);
			
			txtLastName = new JTextField();
			txtLastName.setColumns(10);
			txtLastName.setBounds(180, 67, 277, 20);
			panel.add(txtLastName);
			
			txtEMail = new JTextField();
			txtEMail.setColumns(10);
			txtEMail.setBounds(180, 117, 277, 20);
			panel.add(txtEMail);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						_person = new Person(txtFirstName.getText(),
											txtLastName.getText(),
											txtEMail.getText());
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						_person = null;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
		
}
