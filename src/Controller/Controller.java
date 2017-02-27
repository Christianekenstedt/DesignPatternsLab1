package Controller;
import Model.shapes.Shape;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.util.List;

public class Controller {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu editMenu;

    @FXML
    private Canvas canvas;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem quitMenuItem;

    @FXML
    private MenuItem undoMenuItem;

    @FXML
    private VBox shapeView;

    @FXML
    private VBox propertiesView;

    @FXML
    private Menu fileMenu;


    public void addShapesToShapeView(List<Shape> shapeList){
        for(Shape s : shapeList){
                Button btn = new Button();
                btn.setText(s.getType());
                shapeView.getChildren().add(btn);



            }
    }

}
