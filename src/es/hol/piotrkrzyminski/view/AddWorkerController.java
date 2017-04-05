package es.hol.piotrkrzyminski.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import es.hol.piotrkrzyminski.connection.InsertData;
import es.hol.piotrkrzyminski.model.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Konroler do pola dodawania nowych pracowników
 * @author Piotrek
 *
 */
public class AddWorkerController implements Initializable {
	int year = Calendar.getInstance().get(Calendar.YEAR);
	
	@FXML
	private TextField imieField;
	@FXML
	private TextField nazwiskoField;
	@FXML
	private TextField nrDzialuField;
	@FXML
	private TextField placaField;
	@FXML
	private TextField liczbaDzieciField;
	@FXML
	private RadioButton mezczyznaRadioButton;
	@FXML
	private RadioButton kobietaRadioButton;
	@FXML
	private CheckBox stanCheckBox;
	@FXML
	private DatePicker birthDatePicker;
	@FXML
	private Label info;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		info.setText("");
	}
	
	/**
	 * Pobierz dane z formularza, sprawdz czy dane zosta³y wypelnione poprawnie i dodaj je do bazy danych
	 */
	@FXML
	void addWorkerButton() {
		if(isFieldProper()) {
			String imie = imieField.getText();
			String nazwisko = nazwiskoField.getText();
			int nrDzialu = Integer.parseInt(nrDzialuField.getText());
			float placa = Float.parseFloat(placaField.getText());
			int liczbaDzieci = Integer.parseInt(liczbaDzieciField.getText());
			String plec;
			
			if(mezczyznaRadioButton.isSelected())
				plec = "M";
			else
				plec = "K";
			
			int stan;
			
			if(stanCheckBox.isSelected())
				stan=1;
			else
				stan=0;
			
			LocalDate birthDate = birthDatePicker.getValue();
			int wiek = year - birthDate.getYear();
			
			Worker worker = new Worker(imie, nazwisko, plec, nrDzialu, placa, wiek, liczbaDzieci, stan);
			
			InsertData insertData = new InsertData(worker);
			Thread t = new Thread(insertData);
			t.start();
		} else {
			info.setText("B³¹d. Dane s¹ niepoprawne");
		}
	}
	
	private boolean isFieldProper() {
		if(!imieField.getText().matches("^[A-¯]{1}[a-¿]{2,48}$")) return false;
		if(!nazwiskoField.getText().matches("^[A-¯]{1}[a-¿]{2,48}$")) return false;
		if(nrDzialuField.getText()==null) return false;
		if(Float.parseFloat(placaField.getText())<=0) return false;
		if(Integer.parseInt(liczbaDzieciField.getText())<0) return false;
		return true;
	}
}
