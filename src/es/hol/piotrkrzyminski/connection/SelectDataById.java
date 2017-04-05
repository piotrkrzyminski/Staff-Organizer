package es.hol.piotrkrzyminski.connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.hol.piotrkrzyminski.model.Worker;

/**
*
* Pobranie z bazy danych konkretnego rekordu wskazanego przez uzytkownika
* @author Piotrek
*
*/
public class SelectDataById extends Connect implements Runnable {
	private int id;
	private Worker worker;
	
	public SelectDataById(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM workers WHERE ID="+id);
					
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
						
				worker = new Worker(id, imie, nazwisko, plec, nr_dzialu, placa, wiek, liczba_dzieci, stan_cywilny);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Worker getWorker() {
		return worker;
	}

}
