package es.hol.piotrkrzyminski.connection;

import java.sql.SQLException;

import es.hol.piotrkrzyminski.model.Worker;

/**
 * Dodaj nowy obiekto worker do bazy danych
 * @author Piotrek
 *
 */
public class InsertData extends Connect implements Runnable {
	Worker worker;
	boolean isDone = false;
	
	public InsertData(Worker worker) {
		this.worker = worker;
	}
	
	@Override
	public void run() {
			try {
				preparedStatement = connection.prepareStatement("INSERT INTO workers VALUES (NULL,?,?,?,?,?,?,?,?)");
					
				preparedStatement.setString(1, worker.getImie());
				preparedStatement.setString(2, worker.getNazwisko());
				preparedStatement.setString(3, String.valueOf(worker.getPlec()));
				preparedStatement.setInt(4, worker.getNr_dzialu());
				preparedStatement.setFloat(5, worker.getPlaca());
				preparedStatement.setInt(6, worker.getWiek());
				preparedStatement.setInt(7, worker.getLiczba_dzieci());
				preparedStatement.setInt(8, worker.getStan_cywilny());
				preparedStatement.executeUpdate();
				System.out.println("Dodano nowego czytelnika");
				isDone = true;
			} catch (SQLException e) {
				System.out.println("B³¹d. Nie dodano nowego pracownika");
				e.printStackTrace();
			}
	}
	
	public boolean isFinishedProperly() {
		return isDone;
	}
}
