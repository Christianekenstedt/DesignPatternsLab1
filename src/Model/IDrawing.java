package Model;

import Model.shapes.Shape;

/**
 * Created by Anton on 2017-02-21.
 */
public interface IDrawing {
    void addShape(Shape shape);
    void removeShape(Shape shape);

}
