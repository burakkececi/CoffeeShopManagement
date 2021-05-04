package database;

import java.sql.*;

import customer.Customer;

public interface DatabaseAL {
	Connection connection();
	void disconnect();
    void insert(Customer customer);
    public void search(Customer customer);
    public void update(Customer customer);
    public void remove();
	
}
