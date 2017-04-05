package es.hol.piotrkrzyminski.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.hol.piotrkrzyminski.connection.SelectData;
import es.hol.piotrkrzyminski.connection.UpdateData;
import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Konroler do pola edytowania pracowników
 * @author Piotrek
 *
 */
public class EditWorkerController implements Initializable {
	@FXML
	private TableView<Worker> workerTableView;
	@FXML
	private TableColumn<Worker, String> workerIDTableColumn;
	@FXML
	private TableColumn<Worker, String> workerImieTableColumn;
	@FXML
	private TableColumn<Worker, String> workerNazwiskoTableColumn;
	@FXML
	private TableColumn<Worker, String> workerNrDzialuTableColumn;
	
	@FXML
	private TextField imieTextField;
	@FXML
	private TextField nazwiskoTextField;
	@FXML
	private TextField plecTextField;
	@FXML
	private TextField nrDzialuTextField;
	@FXML
	private TextField placaTextField;
	@FXML
	private TextField wiekTextField;
	@FXML
	private TextField liczbaDzieciTextField;
	@FXML
	private TextField stanCywilnyTextField;
	
	Worker selectedWorker = null;
	boolean selected = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();
		
		workerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	selectedWorker = workerTableView.getSelectionModel().getSelectedItem();
		    	showWorkerInfo(selectedWorker);
		    }
		});
	}
	
	void updateTable() {
		SelectData selectData = new SelectData();
		Thread t = new Thread(selectData);
		t.start();
		
		ObservableList<Worker> workers = selectData.selectWorkers();

		workerTableView.setItems(workers);
		workerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		workerImieTableColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
		workerNazwiskoTableColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		workerNrDzialuTableColumn.setCellValueFactory(new PropertyValueFactory<>("nr_dzialu"));
	}
	
	void showWorkerInfo(Worker selectedWorker) {
		selectedWorker = workerTableView.getSelectionModel().getSelectedItem();
		
		if(selectedWorker!=null) {
			imieTextField.setText(selectedWorker.getImie());
			nazwiskoTextField.setText(selectedWorker.getNazwisko());
			plecTextField.setText(selectedWorker.getPlec());
			nrDzialuTextField.setText(String.valueOf(selectedWorker.getNr_dzialu()));
			placaTextField.setText(String.valueOf(selectedWorker.getPlaca()));
			wiekTextField.setText(String.valueOf(selectedWorker.getWiek()));
			liczbaDzieciTextField.setText(String.valueOf(selectedWorker.getLiczba_dzieci()));
			stanCywilnyTextField.setText(String.valueOf(selectedWorker.getStan_cywilny()));
			selected = true;
		} else {
			imieTextField.setText("");
			nazwiskoTextField.setText("");
			plecTextField.setText("");
			nrDzialuTextField.setText("");
			placaTextField.setText("");
			wiekTextField.setText("");
			liczbaDzieciTextField.setText("");
			stanCywilnyTextField.setText("");
			selected = false;
		}
	}
	
	@FXML
	void update() {
		if(selected) {
			UpdateData updateData = new UpdateData(selectedWorker.getId(), selectedWorker.getNazwisko(), selectedWorker.getNr_dzialu(), selectedWorker.getPlaca(), selectedWorker.getWiek(), selectedWorker.getLiczba_dzieci(), selectedWorker.getStan_cywilny());
			Thread t = new Thread(updateData);
			t.start();
		}
	}
}
