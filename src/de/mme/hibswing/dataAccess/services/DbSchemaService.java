package de.mme.hibswing.dataAccess.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.xdevapi.SessionFactory;

import de.mme.hibswing.dataAccess.factories.MySQLSessionFactory;

public class DbSchemaService {

	private static final String SQLQUERY_RECREATE_MY_PERSON_DB 
			= "CREATE DATABASE IF NOT EXISTS `my_person_db`";
	
	private static final String SQLQUERY_DROP_PERSON_TABLE
			= "DROP TABLE IF EXISTS `my_person_db`.`person`;";
	
	private static final String SQLQUERY_RECREATE_PERSON_TABLE 
			= "CREATE TABLE `my_person_db`.`person` ("
			+ "  `id` INT NOT NULL AUTO_INCREMENT,"
			+ "  `first_name` VARCHAR(45) NULL DEFAULT NULL,"
			+ "  `last_name` VARCHAR(45) NULL DEFAULT NULL,"
			+ "  `email` VARCHAR(45) NULL DEFAULT NULL,"
			+ "   PRIMARY KEY (`id`)"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;"
			+ ""; 
	
	
	MySQLSessionFactory _factory;
	
	
	public DbSchemaService(MySQLSessionFactory factory) {
		_factory = factory;
	}
	
	public void rebuildPersonDB() {
		
		Session session = _factory.getPersonsSession();
		
		try {
			System.out.println("Begin rebuilding PersonDB...");
			session.beginTransaction();
			session.createNativeQuery(SQLQUERY_RECREATE_MY_PERSON_DB).executeUpdate();
			session.createNativeQuery(SQLQUERY_DROP_PERSON_TABLE).executeUpdate();
			session.createNativeQuery(SQLQUERY_RECREATE_PERSON_TABLE).executeUpdate();
			session.getTransaction().commit();	// Ends transaction (Necessary)
			System.out.println("End of rebuilding PersonDB. No Errors");
			
		} catch (Exception e) {
			System.out.println("End of rebuilding PersonDB. Error !!");
			session.getTransaction().rollback();	// Back to DB state before transaction begins
			throw e;
		}
		
	}
	
	
	
}



