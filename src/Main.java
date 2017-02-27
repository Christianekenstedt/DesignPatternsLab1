import Controller.Controller;
import Model.Drawing;
import Model.DrawingObserver;
import Model.IDrawing;
import Model.shapes.Shape;
import Model.shapes.ShapeCache;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/View/main.fxml"));

        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();


        primaryStage.setTitle("Painter pr0 v0.4");
        primaryStage.setScene(new Scene(root, 1200, 800));

        //Models

        ShapeCache shapeCache = new ShapeCache();
        Drawing d = new Drawing();
        IDrawing drawing = d;

        //Initiate view-observer

        DrawingObserver drawingObserver = new DrawingObserver(drawing, controller.getCanvas());
        d.addObserver(drawingObserver);

        //Initialize controller

        controller.setDrawing(drawing);
        controller.addShapesToShapeView(shapeCache.getShapes());


        //Start
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
