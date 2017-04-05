package es.hol.piotrkrzyminski.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.hol.piotrkrzyminski.connection.SelectData;
import es.hol.piotrkrzyminski.model.Worker;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Konroler do wyswietlania pracownikow z bazy danych
 * @author Piotrek
 *
 */
public class WorkerListController implements Initializable {
	@FXML
	private TableView<Worker> workerTableView;
	@FXML
	private TableColumn<Worker, String> workerIDTableColumn;
	@FXML
	private TableColumn<Worker, String> workerImieTableColumn;
	@FXML
	private TableColumn<Worker, String> workerNazwiskoTableColumn;
	@FXML
	private TableColumn<Worker, String> workerPlecTableColumn;
	@FXML
	private TableColumn<Worker, String> workerNrDzialuTableColumn;
	@FXML
	private TableColumn<Worker, String> workerPlacaTableColumn;
	@FXML
	private TableColumn<Worker, String> workerWiekTableColumn;
	@FXML
	private TableColumn<Worker, String> workerLiczbaDzieciTableColumn;
	@FXML
	private TableColumn<Worker, String> workerStanCywilnyTableColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();
	}
	
	/**
	 * Wype³nij tabelê danymi pobranymi z bazy danych
	 */
	void updateTable() {
		SelectData selectData = new SelectData();
		Thread t = new Thread(selectData);
		t.start();
		
		ObservableList<Worker> workers = selectData.selectWorkers();

		workerTableView.setItems(workers);
		workerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		workerImieTableColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
		workerNazwiskoTableColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		workerPlecTableColumn.setCellValueFactory(new PropertyValueFactory<>("plec"));
		workerNrDzialuTableColumn.setCellValueFactory(new PropertyValueFactory<>("nr_dzialu"));
		workerPlacaTableColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));
		workerWiekTableColumn.setCellValueFactory(new PropertyValueFactory<>("wiek"));
		workerLiczbaDzieciTableColumn.setCellValueFactory(new PropertyValueFactory<>("liczba_dzieci"));
		workerStanCywilnyTableColumn.setCellValueFactory(new PropertyValueFactory<>("stan_cywilny"));
	}
}
