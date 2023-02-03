package de.mme.hibswing.dataAccess.factories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.mme.hibswing.dataAccess.entities.Person;

public class MySQLSessionFactory {

	SessionFactory _personsTablefactory;

	
	public MySQLSessionFactory() {
		// Create Session
		_personsTablefactory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Person.class)
								.buildSessionFactory(); 	
	}
	
	
	public Session getPersonsSession() {
		return _personsTablefactory.getCurrentSession();
	}
	
}
