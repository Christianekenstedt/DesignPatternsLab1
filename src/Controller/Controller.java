package Controller;
import Model.Drawing;
import Model.IDrawing;
import Model.shapes.FillableShape;
import Model.shapes.Group;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Stage stage;

    private Tools selectedTool;

    private IDrawing drawing;
    private Shape selectedShape;

    private ArrayList<Shape> selectedShapes = new ArrayList<>();

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
        selectedTool = Tools.SELECT;
        System.out.println("Tool: SELECT");
    }

    @FXML
    void saveDrawing(){
        System.out.println("Saving image...");
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Painter-Pr0 File", "*.ppr0");
        fc.getExtensionFilters().add(extensionFilter);
        File file = fc.showSaveDialog(stage);

        if(file==null)
            return;

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(drawing);
            objectOutputStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openDrawing(){
        System.out.println("Loading image...");
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Painter-Pr0 File", "*.ppr0");
        fc.getExtensionFilters().add(extensionFilter);
        File file = fc.showOpenDialog(stage);

        if(file==null)
            return;

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            drawing = (Drawing)in.readObject();

            drawing.render(canvas);

        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseDragged(MouseEvent event){
        switch(selectedTool){
            case SELECT:
                break;
            case SHAPE:
                drawShapeDragging(event);
                break;
            case GROUP:
                break;
            default:
                break;
        }
    }

    /***
     * Canvas was clicked.
     * @param event
     */
    @FXML
    void canvasClicked(MouseEvent event){
        switch(selectedTool){
            case SELECT:
                selectShape(event);
                break;
            case SHAPE:
                drawShapeStart(event);
                break;
            case GROUP:
                break;
            default:
                break;
        }
    }

    /***
     * Mouse was released from canvas
     * @param event
     */
    @FXML
    void canvasReleased(MouseEvent event){
        switch(selectedTool){
            case SELECT:
                break;
            case SHAPE:
                drawShapeDone();
                break;
            case GROUP:
                break;
            default:
                break;
        }
    }

    private void selectShape(MouseEvent event){

        Shape s = drawing.selectShape(event.getX(), event.getY());

        if(s==null)
            return;

        System.out.println("We selected a " + s.getType());

        if(event.isControlDown()){
            selectedShapes.add(s);

        }else{
            selectedShapes.clear();
            selectedShape = s;
            viewPropertiesOf(s);
        }



    }

    private void drawShapeStart(MouseEvent event){
        if(selectedShape != null) {
            startX = event.getX();
            startY = event.getY();
        }else{
            System.out.println("No shape is selected...");
        }
    }

    private void drawShapeDragging(MouseEvent event){
        if(selectedShape != null){
            selectedShape.setX1(startX);
            selectedShape.setY1(startY);
            selectedShape.setX2(event.getX());
            selectedShape.setY2(event.getY());

            drawing.render(canvas);

            selectedShape.drawShape(canvas.getGraphicsContext2D());
        }
    }


    private void drawShapeDone(){
        if(selectedShape != null){

            Shape shape = selectedShape.clone();

            drawing.addShape(shape);
        }
    }

    private void createGroup(Shape s){
        Group grp = (Group)s;

        for(Shape sh: selectedShapes){
            System.out.println(sh.getType());
        }

        drawing.removeShapes(selectedShapes);

        grp.setChildren(selectedShapes);

        drawing.addShape(grp);

        selectedShapes.clear();
    }

    private void viewPropertiesOf(Shape shape){

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


        cp.valueProperty().addListener((observable, oldValue, newValue) -> {
            shape.setColor(newValue.toString());
            drawing.render(canvas);
        });

        lineWidthField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                double val = Double.parseDouble(newValue);
                shape.setLineWidth(val);
                drawing.render(canvas);
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

            fcp.valueProperty().addListener((observable, oldValue, newValue) -> {
                fShape.setFillColor(newValue.toString());
                drawing.render(canvas);
            });
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                fShape.setFill(newValue);
                drawing.render(canvas);
            });

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

                    if(s.getType().equals("Group")){
                        createGroup(s.clone());
                        selectedTool = Tools.GROUP;
                    }else{
                        System.out.println("Tool: SHAPE");
                        selectedTool = Tools.SHAPE;
                        selectedShape = s;
                    }

                    viewPropertiesOf(s);
                }
            });
        }
    }


    public Canvas getCanvas(){
        return canvas;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


    public enum Tools{
        SELECT,
        SHAPE,
        GROUP
    }
}
