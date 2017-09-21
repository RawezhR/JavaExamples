import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This JavaFX application flips through names and randomly selects
 * a contestant. Click the window to repeat.
 */
public class NextContestant extends Application {

	private BorderPane borderPane;
	private Text name;
	private Random rnd = new Random();
	private String[] fnames;
	private Task<Void> task;
	private static final int durationsecs = 5;
	private static final int changemsecs = 100;

	@Override
	public void start(Stage primaryStage) throws Exception {

		//create the main pane
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background: #FFFFFF;");
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setOnMouseClicked(e -> { 
			spin(durationsecs);
		});

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		name = new Text("");
		name.setEffect(ds);
		name.setCache(true);
		name.setX(10.0f);
		name.setY(270.0f);
		name.setFill(Color.BLUE);
		name.setFont(Font.font(null, FontWeight.BOLD, 200));

		borderPane.setCenter(name);

		fnames = new String[] {
				"John",
				"Paul",
				"George",
				"Ringo",
				"Batman",
				"Robin",
				"Superman"
		};		

		//set the scene
		Scene scene = new Scene(borderPane,1000,500);
		primaryStage.setTitle("Next Contestant");
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setOnCloseRequest(event -> {
			task.cancel();
			Platform.exit();
		});
		primaryStage.show();

		//click it on startup
		spin(durationsecs);
	}

	public void spin(int seconds) {
		task = new Task<Void>() {
			@Override 
			public Void call() throws Exception {
				for (int i=1; i<=(seconds*10); i++) {
					int index = rnd.nextInt(fnames.length);					
					updateMessage(fnames[index]);
					Thread.sleep(changemsecs);
				}
				return null ;
			}
		};
		task.messageProperty().addListener((obs, oldMessage, newMessage) -> name.setText(newMessage));
		new Thread(task).start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
