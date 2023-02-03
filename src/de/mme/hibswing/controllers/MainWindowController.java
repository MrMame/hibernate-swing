package de.mme.hibswing.controllers;

import java.util.Collection;


import de.mme.hibswing.dataAccess.entities.Person;
import de.mme.hibswing.dataAccess.services.DbSchemaService;
import de.mme.hibswing.dataAccess.factories.MySQLSessionFactory;
import de.mme.hibswing.dataAccess.services.PersonService;

public class MainWindowController {

	private PersonService 		_personService;
	private DbSchemaService 	_dbSchemaService;
	private MySQLSessionFactory 	_sessionFactory;
	
	
	public MainWindowController() {
		_sessionFactory = new MySQLSessionFactory();
		_personService = new PersonService(_sessionFactory);
		_dbSchemaService = new DbSchemaService(_sessionFactory);
	}
	
	
	public Collection<Person> getAllPersons(){
		return _personService.getAllPersons();
	}
	
	public void rebuildDB() {
		_dbSchemaService.rebuildPersonDB();
	}
		
	
}
