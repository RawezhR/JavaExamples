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
public class Squares extends Application {

	public static final double width = 630;
	public static final double height = 630;    

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Squares");
		Group root = new Group();
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	private void drawShapes(GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(0.75);

		//draw first rectangle
		//points: top-left, bot-lef, top-right, bot-right
		double[] xarr = new double[]{0.0, 0.0,    width,  width};
		double[] yarr = new double[]{0.0, height, height, 0.0};
		gc.strokePolygon(xarr, yarr, xarr.length);

		drawShapes2(gc,xarr,yarr);
	}

	private void drawShapes2(GraphicsContext gc, double[] xarr, double[] yarr) {

		//divide the incoming square into 9 equal squares
		//outline the center square
		//recurse for the other 8 squares

		//layout
		//SQ1  SQ2  SQ3
		//SQ4  SQ5  SQ6
		//SQ7  SQ8  SQ9        

		if (xarr[3] - xarr[0] < 4.0)
			return;

		//for convenience
		double tl_x = xarr[0];
		double tl_y = yarr[0];
		double tr_x = xarr[3];

		//find and outline the center square
		double len = (tr_x-tl_x)/3.0;
		double[] xarr5 = new double[]{tl_x+len,tl_x+len,tl_x+len*2,tl_x+len*2};
		double[] yarr5 = new double[]{tl_y+len,tl_y+len*2,tl_y+len*2,tl_y+len};

		//draw the center square
		gc.strokePolygon(xarr5, yarr5, xarr5.length);

		double[] xarr1 = new double[]{tl_x, tl_x, tl_x+len, tl_x+len};
		double[] yarr1 = new double[]{tl_y, tl_y+len, tl_y+len,tl_y};

		double[] xarr2 = new double[]{xarr1[3], xarr1[3], xarr1[3]+len, xarr1[3]+len};
		double[] yarr2 = new double[]{yarr1[0],yarr1[1],yarr1[2],yarr1[3]};

		double[] xarr3 = new double[]{xarr2[3], xarr2[3], xarr2[3]+len, xarr2[3]+len};
		double[] yarr3 = new double[]{yarr1[0],yarr1[1],yarr1[2],yarr1[3]};

		double[] xarr4 = new double[]{tl_x, tl_x, tl_x+len, tl_x+len};
		double[] yarr4 = new double[]{yarr1[0]+len,yarr1[1]+len,yarr1[2]+len,yarr1[3]+len};

		double[] xarr6 = new double[]{xarr2[3], xarr2[3], xarr2[3]+len, xarr2[3]+len};
		double[] yarr6 = new double[]{yarr1[0]+len,yarr1[1]+len,yarr1[2]+len,yarr1[3]+len};

		double[] xarr7 = new double[]{tl_x, tl_x, tl_x+len, tl_x+len};
		double[] yarr7 = new double[]{yarr4[0]+len,yarr4[1]+len,yarr4[2]+len,yarr4[3]+len};

		double[] xarr8 = new double[]{xarr1[3], xarr1[3], xarr1[3]+len, xarr1[3]+len};
		double[] yarr8 = new double[]{yarr4[0]+len,yarr4[1]+len,yarr4[2]+len,yarr4[3]+len};

		double[] xarr9 = new double[]{xarr2[3], xarr2[3], xarr2[3]+len, xarr2[3]+len};
		double[] yarr9 = new double[]{yarr4[0]+len,yarr4[1]+len,yarr4[2]+len,yarr4[3]+len};

		//recursively call on other 8 squares (not the center square)
		drawShapes2(gc,xarr1,yarr1);
		drawShapes2(gc,xarr2,yarr2);
		drawShapes2(gc,xarr3,yarr3);
		drawShapes2(gc,xarr4,yarr4);
		drawShapes2(gc,xarr6,yarr6);
		drawShapes2(gc,xarr7,yarr7);
		drawShapes2(gc,xarr8,yarr8);
		drawShapes2(gc,xarr9,yarr9);        
	}
}