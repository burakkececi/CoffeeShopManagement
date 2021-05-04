package customer;

import java.sql.SQLException;

import database.DatabaseAL;
import database.MySqlDatabase;

public class CustomerManager {
	
	public void add(Customer customer) throws SQLException {
		DatabaseAL database = new MySqlDatabase();
		database.connection();
		database.insert(customer);
	}
	public void remove() {
		
		DatabaseAL database = new MySqlDatabase();
		database.connection();
		database.remove();
		
	}
	public void search(Customer customer) {
		DatabaseAL database = new MySqlDatabase();
		database.connection();
		database.search(customer);
	}
	public void update(Customer customer) {
		DatabaseAL database = new MySqlDatabase();
		database.connection();
		database.update(customer);
	}
	
}
