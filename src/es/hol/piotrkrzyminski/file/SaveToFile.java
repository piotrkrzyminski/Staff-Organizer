package es.hol.piotrkrzyminski.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;
/**
 * Zapisz dane z bazy do pliku txt
 * @author Piotrek
 *
 */
public class SaveToFile implements Runnable{
	private ObservableList<Worker> workers; //Kolekcja pracownikow pobranych z bazy danych
	private File file;
	
	public SaveToFile(ObservableList<Worker> workers, File file) {
		this.workers = workers;
		this.file = file;
	}

	@Override
	public void run() {
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			for(Worker w: workers) {
				fileWriter.write(w.toString());
				fileWriter.write(System.lineSeparator());
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
