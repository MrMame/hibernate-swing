package de.mme.hibswing.model.requests;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import de.mme.hibswing.dataAccess.entities.Person;

public class LatestPersons {

	
	private List<Person> _persons = new ArrayList<>();
	private  PropertyChangeSupport  _propertyChangeSupporter = new PropertyChangeSupport(this);
	
	
	// Property Change Registration
	public void addPropertyChangeListener(PropertyChangeListener l) {
		_propertyChangeSupporter.addPropertyChangeListener(l);
	}
	public void removePropertyChangeListener(PropertyChangeListener l) {
		_propertyChangeSupporter.removePropertyChangeListener(l);
	}
	
		
	
	public List<Person> getPersons() {
		return Collections.unmodifiableList(_persons);
	}
	public void setPersons(List<Person> persons) {
		
		// Create the Event for the listeners
		PropertyChangeEvent e = new PropertyChangeEvent(this
														,"Persons"
														,Collections.unmodifiableList(_persons)
														,Collections.unmodifiableList(persons));
		// Write the new list to the instance
		_persons = persons;
		// fire the change event
		_propertyChangeSupporter.firePropertyChange(e);	
	}
	
	
	
	
	
	
	
	
	
	
}
