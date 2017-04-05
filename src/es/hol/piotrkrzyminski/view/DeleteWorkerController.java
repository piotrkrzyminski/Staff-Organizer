package es.hol.piotrkrzyminski.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.hol.piotrkrzyminski.connection.DeleteData;
import es.hol.piotrkrzyminski.connection.SelectData;
import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Konroler do pola usuwania pracowników
 * @author Piotrek
 *
 */
public class DeleteWorkerController implements Initializable {
	
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
	private Label nameLabel;
	@FXML
	private Label nazwiskoLabel;
	@FXML
	private Label plecLabel;
	@FXML
	private Label numerDzialuLabel;
	@FXML
	private Label placaLabel;
	@FXML
	private Label dataUrodzeniaLabel;
	@FXML
	private Label liczbaDzieciLabel;
	@FXML
	private Label stanCywilnyLabel;
	
	@FXML
	private Button deleteButton;
	
	Worker selectedWorker = null;
	
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
			nameLabel.setText(selectedWorker.getImie());
			nazwiskoLabel.setText(selectedWorker.getNazwisko());
			plecLabel.setText(selectedWorker.getPlec());
			numerDzialuLabel.setText(String.valueOf(selectedWorker.getNr_dzialu()));
			placaLabel.setText(String.valueOf(selectedWorker.getPlaca()));
			dataUrodzeniaLabel.setText(String.valueOf(selectedWorker.getWiek()));
			liczbaDzieciLabel.setText(String.valueOf(selectedWorker.getLiczba_dzieci()));
			stanCywilnyLabel.setText(String.valueOf(selectedWorker.getStan_cywilny()));
		} else {
			nameLabel.setText("");
			nazwiskoLabel.setText("");
			plecLabel.setText("");
			numerDzialuLabel.setText("");
			placaLabel.setText("");
			dataUrodzeniaLabel.setText("");
			liczbaDzieciLabel.setText("");
			stanCywilnyLabel.setText("");
		}
	}
	
	@FXML
	void delete() {
		DeleteData deleteData = new DeleteData(selectedWorker.getId());
		Thread t = new Thread(deleteData);
		t.start();
	}
}
