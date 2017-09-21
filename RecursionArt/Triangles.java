package RecursionArt;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Demonstrate Recursion graphically
//Eclipse JavaFX update site: http://download.eclipse.org/efxclipse/updates-released/2.3.0/site
public class Triangles extends Application {

	public static final double width = 800;
	public static final double height = 650;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Triangles");
		Group root = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void drawShapes(GraphicsContext gc) {
		gc.setStroke(Color.WHITE);
		gc.setFill(Color.BLACK);
		gc.setLineWidth(1);

		//top point, botleft, botright
		double[] xarr = new double[]{width/2.0,0.0,width};
		double[] yarr = new double[]{0.0,height,height};

		gc.strokePolygon(xarr, yarr, xarr.length);
		gc.fillPolygon(xarr, yarr, xarr.length);

		drawShapes2(gc,xarr,yarr);
	}

	private void drawShapes2(GraphicsContext gc, double[] xarr, double[] yarr) {

		if (xarr[0] - xarr[1] < 1.0)
			return;

		double[] xarr1 = new double[]{xarr[0],(xarr[0]-xarr[1])/2.0+xarr[1],(xarr[2]-xarr[0])/2.0 + xarr[0]};
		double[] yarr1 = new double[]{yarr[0],(yarr[1]-yarr[0])/2.0+yarr[0],(yarr[1]-yarr[0])/2.0+yarr[0]};

		double[] xarr2 = new double[]{xarr1[1],xarr[1],(xarr[2]-xarr[1])/2.0 + xarr[1]};
		double[] yarr2 = new double[]{yarr1[1],yarr[1],yarr[2]};

		double[] xarr3 = new double[]{xarr1[2],xarr2[2],xarr[2]};
		double[] yarr3 = new double[]{yarr1[2],yarr2[2],yarr[2]};        

		gc.strokePolygon(xarr1, yarr1, xarr1.length);
		gc.fillPolygon(xarr1, yarr1, xarr1.length);

		gc.strokePolygon(xarr2, yarr2, xarr2.length);
		gc.fillPolygon(xarr2, yarr2, xarr2.length);

		gc.strokePolygon(xarr3, yarr3, xarr3.length);
		gc.fillPolygon(xarr3, yarr3, xarr3.length); 

		drawShapes2(gc,xarr1,yarr1);
		drawShapes2(gc,xarr2,yarr2);
		drawShapes2(gc,xarr3,yarr3);
	}
}