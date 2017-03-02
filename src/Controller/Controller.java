package Controller;
import Model.IDrawing;
import Model.shapes.FillableShape;
import Model.shapes.Rectangle;
import Model.shapes.Shape;
import com.sun.deploy.util.StringUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.Method;
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
    void onMouseDragged(MouseEvent event){
        if(selectedShape != null){
            double width = event.getX() - startX;
            double height = event.getY() - startY;

            double newX, newY, newW, newH;

            if(width < 0){
                newX = startX + width;
                newW = Math.abs(width);
                newH = height * -1;
                newY = event.getY();
                System.out.println("bajs");

            }
            else{
                newX = startX;
                newW = width;
            }

            if(height < 0){
                newY = startY + height;
                newH = Math.abs(height);
                newW = width*-1;
                newX = event.getX();
            }
            else {
                newY = startY;
                newH = height;
            }
            Shape shape = selectedShape.clone();

            shape.setX(newX);
            shape.setY(newY);
            shape.setWidth(newW);
            shape.setHeight(newH);

            drawing.render(canvas);

            shape.drawShape(canvas.getGraphicsContext2D());

        }else{
            System.out.println("No shape is selected...");
        }
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

            //TODO:do width/pos conversions here instead;
            double newX, newY, newW, newH;

             if(width < 0){
                 newX = startX + width;
                 newW = Math.abs(width);
             }
             else{
                 newX = startX;
                 newW = width;
             }

             if(height < 0){
                newY = startY + height;
                newH = Math.abs(height);
             }
             else{
                newY = startY;
                newH = height;
             }


            shape.setX(newX);
            shape.setY(newY);
            shape.setWidth(newW);
            shape.setHeight(newH);

            drawing.addShape(shape);

        }else{
            System.out.println("No shape is selected...");
        }
    }

    void viewPropertiesOf(Shape shape){

        propertiesView.getChildren().clear();

        //properties for all shapes

        HBox colorBox = new HBox();
        Label colorLabel = new Label();
        colorLabel.setText("Color");
        colorLabel.setMinWidth(propertiesView.getWidth()/2);
        ColorPicker cp = new ColorPicker();
        cp.setPromptText("Color");
        cp.setValue(Color.BLACK);
        colorBox.getChildren().add(colorLabel);
        colorBox.getChildren().add(cp);


        HBox lineBox = new HBox();
        Label lineWidthLabel = new Label();
        lineWidthLabel.setText("Line width");
        lineWidthLabel.setMinWidth(propertiesView.getWidth()/2);
        TextField lineWidthField = new TextField();
        lineWidthField.setText("1");
        lineBox.getChildren().add(lineWidthLabel);
        lineBox.getChildren().add(lineWidthField);


        propertiesView.getChildren().add(colorBox);
        propertiesView.getChildren().add(lineBox);

        //set handlers


        cp.valueProperty().addListener((observable, oldValue, newValue) -> shape.setColor(newValue));

        lineWidthField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                double val = Double.parseDouble(newValue);
                shape.setLineWidth(val);
            }catch(Exception e){
                lineWidthField.setText(oldValue);
            }
        });


        //fillable shapes

        if(shape instanceof FillableShape){
            HBox fillColorBox = new HBox();
            Label fillColorLabel = new Label();
            fillColorLabel.setText("Fill color");
            fillColorLabel.setMinWidth(propertiesView.getWidth()/2);
            ColorPicker fcp = new ColorPicker();
            fcp.setPromptText("Fill Color");
            fcp.setValue(Color.WHITE);
            fillColorBox.getChildren().add(fillColorLabel);
            fillColorBox.getChildren().add(fcp);

            HBox fillBox = new HBox();
            Label fillLabel = new Label();
            fillLabel.setText("Fill");
            fillLabel.setMinWidth(propertiesView.getWidth()/2);
            CheckBox cb = new CheckBox();
            fillBox.getChildren().add(fillLabel);
            fillBox.getChildren().add(cb);

            propertiesView.getChildren().add(fillColorBox);
            propertiesView.getChildren().add(fillBox);

            //Set handlers

            FillableShape fShape = (FillableShape) shape;

            fcp.valueProperty().addListener((observable, oldValue, newValue) -> fShape.setFillColor(newValue));
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> fShape.setFill(newValue));

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
                    viewPropertiesOf(s);
                }
            });
        }
    }


    public Canvas getCanvas(){
        return canvas;
    }
}
