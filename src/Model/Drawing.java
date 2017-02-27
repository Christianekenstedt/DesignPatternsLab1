package Model;

import Model.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Anton on 2017-02-21.
 */
public class Drawing extends Observable implements IDrawing {

    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("Shape added: (" + shape.getX() + ", " + shape.getY() + ", " + shape.getWidth() + ", " + shape.getHeight() +")");

        setChanged();
        notifyObservers();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);

        setChanged();
        notifyObservers();
    }

    /***
     * Should this class be placed here?
     * @param context
     */
    public void render(GraphicsContext context){
        for(Shape s: shapes){
            s.drawShape(context);
        }
    }
}
