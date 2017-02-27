package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Rectangle extends FillableShape  {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);

        graphicsContext.strokeRect(getX(), getY(), getWidth(), getHeight());
    }
}
