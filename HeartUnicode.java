import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
 *  Description: This program shows how to display the unicode character for
 *               a heart in a JavaFX panel. 
 */
public class HeartUnicode extends Application {

	public static final int WIDTH = 190;
	public static final int HEIGHT = 290;

	private Pane pane;
	private Label label; //\u2665

	@Override
	public void start(Stage primaryStage) throws Exception {

		pane = new Pane();	
		label = new Label();
		label.setText("\u2665");
		label.setTextFill(Color.RED);
		label.setFont(new Font(275));

		pane.getChildren().add(label);

		Scene scene = new Scene(pane,WIDTH,HEIGHT);
		primaryStage.setTitle("HeartUnicode");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				//stage is closing
				System.out.println("Bye.");
				Platform.exit();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
