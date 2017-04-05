package es.hol.piotrkrzyminski;

import java.io.IOException;

import es.hol.piotrkrzyminski.connection.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	private BorderPane mainScene;
	private Stage stage;
	private AnchorPane subScene;
	private static Connect connect;
	
	public static void main(String[] args) {
		connect = new Connect();
		connect.connect(); //Po³¹cz siê z baz¹ danych
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Staff Organizer");
		
		initRoot();
		initSubScene();
	}
	
	/**
	 * Wczytaj plik FXML g³ównego panelu
	 */
	private void initRoot() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("view/Main.fxml"));
		
		try {
			mainScene = loader.load();
		} catch (IOException e) {
			System.out.println("Nie mo¿na wczytaæ pliku fxml");
			e.printStackTrace();
		}
		
		Scene scene = new Scene(mainScene);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Wczytaj plik FXML zawartosci
	 */
	private void initSubScene() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("view/WorkerList.fxml"));
		
		try {
			subScene = loader.load();
		} catch (IOException e) {
			System.out.println("Nie mo¿na wczytaæ pliku fxml");
			e.printStackTrace();
		}
		
		mainScene.setCenter(subScene);
	}
}
