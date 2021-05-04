import java.sql.SQLException;

import customer.Customer;
import customer.CustomerManager;


public class Main {

	public static void main(String[] args){
		
		Customer yasemin = new Customer(108,"Yasemin", "Cankurtaran","1999","12312312312");
		CustomerManager customerManager = new CustomerManager();
		
		try {
			
			customerManager.add(yasemin);
			customerManager.update(yasemin);
			customerManager.search(yasemin);
			customerManager.remove();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
