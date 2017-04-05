package es.hol.piotrkrzyminski.model;

import java.time.LocalDate;

/**
 * Klasa opsujaca dane obiektu worker
 * @author Piotrek
 *
 */
public class Worker {
	private int id;
	private String imie;
	private String nazwisko;
	private String plec;
	private int nr_dzialu;
	private float placa;
	private int wiek;
	private LocalDate birthDate;
	private int liczba_dzieci;
	private int stan_cywilny;

	public Worker(String imie, String nazwisko, String plec, int nr_dzialu, float placa, int wiek, int liczba_dzieci, int stan_cywilny) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.plec = plec;
		this.nr_dzialu = nr_dzialu;
		this.placa = placa;
		this.wiek = wiek;
		this.liczba_dzieci = liczba_dzieci;
		this.stan_cywilny = stan_cywilny;
	}
	
	/**
	 * Konstruktor wykorzystany do pobrania danych z bazy danych
	 * @param id
	 * @param imie
	 * @param nazwisko
	 * @param plec
	 * @param nr_dzialu
	 * @param placa
	 * @param wiek
	 * @param liczba_dzieci
	 * @param stan_cywilny
	 */
	public Worker(int id, String imie, String nazwisko, String plec, int nr_dzialu, float placa, int wiek, int liczba_dzieci, int stan_cywilny) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.plec = plec;
		this.nr_dzialu = nr_dzialu;
		this.placa = placa;
		this.wiek = wiek;
		this.liczba_dzieci = liczba_dzieci;
		this.setStan_cywilny(stan_cywilny);
	}
	
	public int getId() {
		return id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public int getNr_dzialu() {
		return nr_dzialu;
	}

	public void setNr_dzialu(int nr_dzialu) {
		this.nr_dzialu = nr_dzialu;
	}

	public float getPlaca() {
		return placa;
	}

	public void setPlaca(float placa) {
		this.placa = placa;
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public int getLiczba_dzieci() {
		return liczba_dzieci;
	}

	public void setLiczba_dzieci(int liczba_dzieci) {
		this.liczba_dzieci = liczba_dzieci;
	}
	
	public int getStan_cywilny() {
		return stan_cywilny;
	}

	public void setStan_cywilny(int stan_cywilny) {
		this.stan_cywilny = stan_cywilny;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return this.id+";"+this.imie+";"+this.nazwisko+";"+this.plec+";"+this.nr_dzialu+";"+this.placa+";"+this.wiek+";"+this.liczba_dzieci+";"+this.stan_cywilny;
	}
}
