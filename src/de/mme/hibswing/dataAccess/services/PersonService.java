package de.mme.hibswing.dataAccess.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.mme.hibswing.dataAccess.entities.Person;
import de.mme.hibswing.dataAccess.factories.MySQLSessionFactory;

public class PersonService {

	MySQLSessionFactory _factory;
	
	
	
	public PersonService(MySQLSessionFactory factory) {
			_factory = factory;
	}
	
	public void addPerson(Person newPerson) {
		if(newPerson == null) throw new IllegalArgumentException();
		
		Session session = _factory.getPersonsSession();
		
		try {
			session.beginTransaction();
			session.save(newPerson);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		
	}
	
	
	public List<Person> getAllPersons(){
		List<Person> list = null;
		Session session = _factory.getPersonsSession();
		try {
			session.beginTransaction();
			list = session.createCriteria(Person.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
	
	
}
