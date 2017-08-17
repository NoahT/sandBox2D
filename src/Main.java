import javafx.application.Application;
import javafx.stage.Stage;
import processing.core.PApplet;

public class Main extends Application { //extends PApplet
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PApplet.main("Startup");
	}
}
