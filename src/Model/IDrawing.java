package Model;

import Model.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public interface IDrawing {
    void addShape(Shape shape);
    void removeShape(Shape shape);
    void render(GraphicsContext graphicsContext);
}
