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

public class PersonsRequest {

	
	private List<Person> _persons = new ArrayList<>();
	private List<IPersonsChangedListener> _personsChangedListeners = new ArrayList<>();
	
	
	// Property Change Registration
	public void addPersonsChangedListener(IPersonsChangedListener l) {
		_personsChangedListeners.add(l);
	}
	public void removePersonsChangedListener(IPersonsChangedListener l) {
		_personsChangedListeners.remove(l);
	}
	
		
	
	public List<Person> getPersons() {
		return Collections.unmodifiableList(_persons);
	}
	public void setPersons(List<Person> persons) {
		// Write the new list to the instance
		_persons = persons;
		fireEventPersonsChanged();
	}
	
	
	
	
	private void fireEventPersonsChanged() {
		// fire the change event
		for(IPersonsChangedListener l:_personsChangedListeners) {
			l.onPersonsChangedEvent();
		}
	}
	
	
	
	
	
}
