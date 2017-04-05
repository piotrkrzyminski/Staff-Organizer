package es.hol.piotrkrzyminski.connection;

import java.sql.SQLException;

/**
 * Zaktualizuj wybrany rekord
 * @author Piotrek
 *
 */
public class UpdateData extends Connect implements Runnable {
	private int id;
	private String nazwisko;
	private int nr_dzialu;
	private float placa;
	private int wiek;
	private int liczba_dzieci;
	private int stan_cywilny;
	
	public UpdateData(int id, String nazwisko, int nr_dzialu, float placa, int wiek, int liczba_dzieci, int stan_cywilny) {
		this.id = id;
		this.nazwisko = nazwisko;
		this.nr_dzialu = nr_dzialu;
		this.placa = placa;
		this.wiek = wiek;
		this.liczba_dzieci = liczba_dzieci;
		this.stan_cywilny = stan_cywilny;
	}

	@Override
	public void run() {
		String sql = "UPDATE workers SET NAZWISKO=?, NR_DZIALU=?, PLACA=?, WIEK=?, LICZBA_DZIECI=?, STAN_CYWILNY=? WHERE ID=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, this.nazwisko);
			preparedStatement.setInt(2, this.nr_dzialu);
			preparedStatement.setFloat(3, this.placa);
			preparedStatement.setInt(4, this.wiek);
			preparedStatement.setInt(5, this.liczba_dzieci);
			preparedStatement.setInt(6, this.stan_cywilny);
			preparedStatement.setInt(7, this.id);
			
			preparedStatement.executeUpdate();
			System.out.println("Akt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
