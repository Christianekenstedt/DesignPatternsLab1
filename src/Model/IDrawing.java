package Model;

import Model.shapes.Shape;
import javafx.scene.canvas.Canvas;

import java.util.List;

/**
 * Created by Anton on 2017-02-21.
 */
public interface IDrawing {
    void addShape(Shape shape);
    void removeShape(Shape shape);
    void removeShapes(List<Shape> shapes);
    void render(Canvas canvas);
    Shape selectShape(double x, double y);
}
