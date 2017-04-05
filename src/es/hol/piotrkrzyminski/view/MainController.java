package es.hol.piotrkrzyminski.view;

import java.io.File;
import java.io.IOException;

import es.hol.piotrkrzyminski.connection.SelectData;
import es.hol.piotrkrzyminski.file.GenerateHTML;
import es.hol.piotrkrzyminski.file.SaveToFile;
import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 * G³ówny kontroler odpowiadajacy za pola opcji
 * @author Piotrek
 *
 */
public class MainController {
	private AnchorPane subScene;
	
	@FXML
	private BorderPane parent;
	@FXML
	private Button ListaPracownikowButton;
	@FXML
	private Button DodawaniePracownikowButton;
	
	/**
	 * Owtórz panel wyœwietlania listy pracownikow
	 */
	@FXML
	void OpenDodajPracownika() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddWorker.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		parent.setCenter(subScene);
	}
	
	/**
	 * Otwórz panel dodawania nowych pracowników
	 */
	@FXML
	void OpenListaPracownikow() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("WorkerList.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		parent.setCenter(subScene);
	}
	
	@FXML
	void exportToTxt() {
		FileChooser fileChooser = new FileChooser(); //Okno zapisu pliku
		fileChooser.setTitle("Eksportuj dane do pliku tekstowego");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		
		SelectData selectData = new SelectData();

		Thread selectD = new Thread(selectData); //Uwtórz nowy w¹tek do pobrania danych z bazy
		selectD.start();
		
		ObservableList<Worker> workers = selectData.selectWorkers();
		
		File file = fileChooser.showSaveDialog(parent.getScene().getWindow());
		
		SaveToFile saveFile = new SaveToFile(workers, file); //Przeka¿ dane do klasy zapisuj¹cej plik
		Thread save= new Thread(saveFile);
		
		if(file!=null)
			save.start();
		
	}
	
	@FXML
	void deleteWorker() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DeleteWorker.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		parent.setCenter(subScene);
	}
	
	@FXML
	void editWorker() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EditWorker.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		parent.setCenter(subScene);
	}
	
	@FXML
	void exportHTML() {
		FileChooser fileChooser = new FileChooser(); //Okno zapisu pliku
		fileChooser.setTitle("Eksportuj dane do pliku html");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*html)", "*.html");
		fileChooser.getExtensionFilters().add(extFilter);
		
		SelectData selectData = new SelectData();

		Thread selectD = new Thread(selectData); //Uwtórz nowy w¹tek do pobrania danych z bazy
		selectD.start();
		
		ObservableList<Worker> workers = selectData.selectWorkers();
		
		File file = fileChooser.showSaveDialog(parent.getScene().getWindow());
		
		GenerateHTML generateHTML = new GenerateHTML(workers, file); //Przeka¿ dane do klasy zapisuj¹cej plik html
		Thread save= new Thread(generateHTML);
		
		if(file!=null)
			save.start();
		
	}
	
	@FXML
	void info() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Info.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		parent.setCenter(subScene);
	}
}
