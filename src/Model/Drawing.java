package Model;

import Model.shapes.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Anton on 2017-02-21.
 */
public class Drawing extends Observable implements IDrawing, Serializable{

    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("Shape added: (" + shape.getX1() + ", " + shape.getY1() + ", " + shape.getX2() + ", " + shape.getY2() +")");

        setChanged();
        notifyObservers();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);

        setChanged();
        notifyObservers();
    }

    public void removeShapes(List<Shape> shapesToRemove){
        shapes.removeAll(shapesToRemove);

        setChanged();
        notifyObservers();
    }


    public void render(Canvas canvas){
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for(Shape s: shapes){
            s.drawShape(context);
        }
    }

    public Shape selectShape(double x, double y){

        for(Shape s: shapes){
            if(s.selectShape(x, y)){

                return s;
            }
        }
        return null;
    }
}
