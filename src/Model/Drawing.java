package Model;

import Model.shapes.Shape;
import java.util.ArrayList;

/**
 * Created by Anton on 2017-02-21.
 */
public class Drawing implements IDrawing{

    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }
}
