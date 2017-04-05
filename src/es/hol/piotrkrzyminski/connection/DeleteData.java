package es.hol.piotrkrzyminski.connection;

import java.sql.SQLException;

/**
 *
 * Klasa odpowiadajaca za usuniecie rekordu z bazy danych
 * @author Piotrek
 *
 */
public class DeleteData extends Connect implements Runnable {
	private int id;
	
	public DeleteData(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		String sql = "DELETE FROM workers WHERE ID="+this.id;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
