package database;

import java.sql.*;
import java.util.Scanner;

import customer.Customer;

public class MySqlDatabase implements DatabaseAL {

	// init database constants
	static final String DATABASE_DRİVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/CustomerInfo";
	static final String USERNAME = "root";
	static final String PASSWORD = "";

	// init connection objects
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement statement2 = null;
	private ResultSet resultSet;

	// connect database
	@Override
	public Connection connection() {

		if (connection == null) {
			try {

				Class.forName(DATABASE_DRİVER);
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				System.out.println("Bağlantı Basarılı!");

			} catch (SQLException | ClassNotFoundException e) {

				e.printStackTrace();
			}
		}

		return connection;
	}

	// disconnect database
	@Override
	public void disconnect() {

		if (connection != null) {
			try {

				connection.close();
				connection = null;

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	// add customer to database
	public void insert(Customer customer) {

		String sql = "insert into customerinfo.customer (id,name,surname,dateOfBirth,nationalityID)"
				+ " values(?,?,?,?,?)";

		if (connection == null) {
			try {

				System.out.println("Veritabanina baglaniliyor...");
				connection();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		try {

			statement2 = connection.prepareStatement(sql);

			statement2.setInt(1, customer.getId());
			statement2.setString(2, customer.getFirstName().toLowerCase());
			statement2.setString(3, customer.getLastName().toLowerCase());
			statement2.setString(4, customer.getDateOfBirth().toLowerCase());
			statement2.setString(5, customer.getNationalityID().toLowerCase());

			statement2.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {

				statement2.close();
				disconnect();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}
	//update customer
	public void update(Customer customer) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Güncellemek istediginiz ID numarısını giriniz: ");
		int idNO = scan.nextInt();
		
		String idSQL = "SELECT id FROM customer";
		String query1 = "UPDATE customer SET name = '" + customer.getFirstName().toLowerCase() + "' WHERE id in (" + idNO + ")";
		String query2 = "UPDATE customer SET surname = '" + customer.getLastName().toLowerCase() + "' WHERE id in (" + idNO + ")";
		String query3 = "UPDATE customer SET dateOfBirth = '" + customer.getDateOfBirth().toLowerCase() + "' WHERE id in (" + idNO
				+ ")";
		String query4 = "UPDATE customer SET nationalityID = '" + customer.getNationalityID().toLowerCase() + "' WHERE id in (" + idNO
				+ ")";

		if (connection == null) {
			connection();
		}
		
			boolean flag = false;
			try {
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(idSQL);
				while(resultSet.next()) {
					if (idNO == resultSet.getInt("id")) {
						statement.executeUpdate(query1);
						statement.executeUpdate(query2);
						statement.executeUpdate(query3);
						statement.executeUpdate(query4);

						System.out.println("Güncelleme Basarılı!");
						break;
					}else {
						
						flag= true;
						break;
					}
					
				}
				
				resultSet.close();
				
				if(flag) {
					System.out.println("Bulunamadi, tekrar deneyiniz... ");
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {

				try {

					statement.close();
					disconnect();
				} catch (SQLException e) {

					e.printStackTrace();
				}

			
		}

	}

	public void remove() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Lutfen silmek istediginiz ID numarasını giriniz: ");
		int idNO = scan.nextInt();
		
		String sql ="DELETE FROM customer where id = "+idNO;
		
		if (connection == null) {
			connection();
		}
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			System.out.println("Islem Basarili!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	

	
	public void search(Customer customer) {

		String sql = "SELECT * FROM customer where name = '" + customer.getFirstName().toLowerCase() + "' && surname = '"
				+ customer.getLastName().toLowerCase() + "'";

		if (connection == null) {
			connection();
		}
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			System.out.println("ID\tName\tSurname\tDate Of Birth\tNationality ID");
			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String dateOfBirth = resultSet.getString("dateOfBirth");
				String nationalityID = resultSet.getString("nationalityID");

				System.out.println(id + "\t" + name + "\t" + surname + "\t\t" + dateOfBirth + "\t" + nationalityID);

			}

			resultSet.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				statement.close();
				disconnect();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

}
