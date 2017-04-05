package es.hol.piotrkrzyminski.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;

/**
 * Wygeneruj plik html z tabel¹ zawieraj¹c¹ dane z bazy danych
 * @author Piotrek
 *
 */
public class GenerateHTML implements Runnable {
	private ObservableList<Worker> workers;
	private File file;
	
	private String htmlBegin="<html lang='pl'><head><meta charset='UTF-8'></head><body><table border='1'><tr><td>Id</td><td>Imie</td>"
			+ "<td>Nazwisko</td><td>Plec</td><td>Numer dzialu</td><td>Placa</td><td>Liczba dzieci</td><td>Stan cywilny</td>";
	private String htmlEnd="</table></body></html>";
	private FileWriter fileWriter;
	
	
	public GenerateHTML(ObservableList<Worker> workers, File file) {
		this.workers = workers;
		this.file = file;
	}

	@Override
	public void run() {
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(htmlBegin);
			
			for(Worker w: workers) {
				String htmlTableRow="<tr><td>"+w.getId()+"</td><td>"+w.getImie()+"</td><td>"+w.getNazwisko()+"</td><td>"+w.getPlec()+"</td>"
						+ "<td>"+w.getNr_dzialu()+"</td><td>"+w.getPlaca()+"</td><td>"+w.getLiczba_dzieci()+"</td><td>"+w.getStan_cywilny()+"</td></tr>";
				
				fileWriter.write(htmlTableRow);
			}
			
			fileWriter.write(htmlEnd);
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
