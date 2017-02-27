package Controller;
import Model.IDrawing;
import Model.shapes.Shape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;

public class Controller {

    private IDrawing drawing;
    private Shape selectedShape;

    private double startX, startY;



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

    @FXML
    void selectButtonClicked(ActionEvent event){
        System.out.println("Hello");
    }

    @FXML
    void drawShapeStart(MouseEvent event){
        if(selectedShape != null) {
            startX = event.getX();
            startY = event.getY();
        }else{
            System.out.println("No shape is selected...");
        }
    }

    @FXML
    void drawShapeDone(MouseEvent event){
        if(selectedShape != null){
            double width = event.getX() - startX;
            double height = event.getY() - startY;

            Shape shape = selectedShape.clone();

            shape.setX(startX);
            shape.setY(startY);
            shape.setWidth(width);
            shape.setHeight(height);

            drawing.addShape(shape);

        }else{
            System.out.println("No shape is selected...");
        }
    }


    public void setDrawing(IDrawing drawing){
        this.drawing = drawing;
    }

    /***
     *
     * @param shapeList
     */
    public void addShapesToShapeView(List<Shape> shapeList){


        Label lbl = new Label();
        lbl.setText("Shapes");
        shapeView.getChildren().add(lbl);


        for(Shape s : shapeList){
            Button btn = new Button();
            btn.setText(s.getType());
            btn.setMaxWidth(Double.MAX_VALUE); // sets the with of the button to fill the VBox width.
            shapeView.getChildren().add(btn);

            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    selectedShape = s;

                }
            });
        }
    }


    public Canvas getCanvas(){
        return canvas;
    }
}
