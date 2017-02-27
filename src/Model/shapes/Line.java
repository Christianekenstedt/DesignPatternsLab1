package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Line extends Shape  {

    public Line(){
        type = "Line";
    }



    @Override
    public void drawShape(GraphicsContext context) {
        super.Draw(context); //generic draw-stuff

        context.strokeLine(getX(), getY(), getX()+getWidth(), getY()+getHeight());

    }
}
