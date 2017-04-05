package es.hol.piotrkrzyminski.connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Pobieranie rekordow z bazy danych i utworzenie nowego obiektu worker
 * @author Piotrek
 *
 */
public class SelectData extends Connect implements Runnable {
	ObservableList<Worker> workers = FXCollections.observableArrayList();
	
	@Override
	public void run() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM workers");
					
			String imie, nazwisko, plec;
			int id, nr_dzialu, wiek, liczba_dzieci, stan_cywilny;
			float placa;
		
			while(resultSet.next()) {
				id = resultSet.getInt("ID");
				imie = resultSet.getString("IMIE");
				nazwisko = resultSet.getString("NAZWISKO");
				plec = resultSet.getString("PLEC");
				nr_dzialu = resultSet.getInt("NR_DZIALU");
				placa = resultSet.getFloat("PLACA");
				wiek = resultSet.getInt("WIEK");
				liczba_dzieci = resultSet.getInt("LICZBA_DZIECI");
				stan_cywilny = resultSet.getInt("STAN_CYWILNY");
						
				workers.add(new Worker(id, imie, nazwisko, plec, nr_dzialu, placa, wiek, liczba_dzieci, stan_cywilny));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Worker> selectWorkers() {
		return workers;
	}
}
