package de.mme.hibswing.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.mme.hibswing.dataAccess.entities.Person;
import de.mme.hibswing.dataAccess.services.DbSchemaService;
import de.mme.hibswing.dataAccess.factories.MySQLSessionFactory;
import de.mme.hibswing.dataAccess.services.PersonService;
import de.mme.hibswing.model.requests.IPersonsChangedListener;
import de.mme.hibswing.model.requests.PersonsRequest;
import de.mme.hibswing.views.MainView;

public class MainWindowController {

	private PersonService 		_personService;
	private DbSchemaService 	_dbSchemaService;
	private MySQLSessionFactory _sessionFactory;
		
	
	private MainWindowController _mainWindowController;
	private PersonsRequest _personsModel;
		
	private List<IPersonsChangedListener> _personsChangedListeners = new ArrayList<>();
	
	
	public MainWindowController(PersonsRequest personsModel) {
		_sessionFactory = new MySQLSessionFactory();
		_personService = new PersonService(_sessionFactory);
		_dbSchemaService = new DbSchemaService(_sessionFactory);
		
		_personsModel = personsModel;
	
	}
	
	
	// Property Change Registration
	public void addPersonsChangedListener(IPersonsChangedListener l) {
		_personsModel.addPersonsChangedListener(l);
	}
	public void removePersonsChangedListener(IPersonsChangedListener l) {
		_personsModel.removePersonsChangedListener(l);;
	}
	
	
	
	public Collection<Person> getAllPersons(){
		_personsModel.setPersons(_personService.getAllPersons());
		return _personsModel.getPersons();
	}
	
	public void ResetDB() {
		_dbSchemaService.rebuildPersonDB();
	}
		
	
	
	
	
	
	
}
