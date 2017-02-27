package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Circle extends FillableShape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);



        double diameter = 0;

        if (getWidth() > getHeight()){
            diameter = getWidth();
        }else{
            diameter = getHeight();
        }

        graphicsContext.strokeOval(getX(),getY(),diameter,diameter);
    }
}
