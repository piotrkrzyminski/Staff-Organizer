package es.hol.piotrkrzyminski.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB = "jdbc:sqlite:workers.db";
	
	protected static Connection connection;
	protected static Statement statement;
	protected static PreparedStatement preparedStatement;
	
	public void connect() {
		/**
		 * Pobranie sterownika bazy danych
		 */
		try {
			Class.forName(DRIVER);
			System.out.println("Pobrano sterownik JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("B��d. Nie znaleziono sterownika JDBC");
			e.printStackTrace();
		}
		
		/**
		 * Po��czenie z baz� danych
		 */
		try {
			connection = DriverManager.getConnection(DB);
			statement = connection.createStatement();
			System.out.println("Po��czono z baz� danych");
		} catch (SQLException e) {
			System.out.println("B��d. Nie po��czono z baz� danych");
			e.printStackTrace();
		}
		
		createTable();
	}
	
	/**
	 * Stw�rz tabel� workers je�li nie istnieje
	 */
	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS workers (ID INTEGER PRIMARY KEY AUTOINCREMENT, IMIE VARCHAR(50) NOT NULL, NAZWISKO VARCHAR(20) NOT NULL, PLEC VARCHAR(1) NOT NULL, NR_DZIALU INTEGER NOT NULL, PLACA REAL NOT NULL, WIEK INTEGER NOT NULL, LICZBA_DZIECI INTEGER NOT NULL, STAN_CYWILNY INTEGER NOT NULL);";
		
		try {
			statement.execute(sql);
			System.out.println("Utworzono baze danych");
		} catch (SQLException e) {
			System.out.println("Nie udalo sie stworzyc tabeli workers");
			e.printStackTrace();
		}
	}
}
