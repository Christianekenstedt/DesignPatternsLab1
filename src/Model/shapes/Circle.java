package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);


        double newX, newY, newW, newH;

        if(getWidth() < 0){
            newX = getX() + getWidth();
            newW = Math.abs(getWidth());
        }
        else{
            newX = getX();
            newW = getWidth();
        }

        if(getHeight() < 0){
            newY = getY() + getHeight();
            newH = Math.abs(getHeight());
        }
        else{
            newY = getY();
            newH = getHeight();
        }


        double diameter = 0;

        if (newW > newH){
            diameter = newW;
        }else{
            diameter = newH;
        }

        graphicsContext.strokeOval(newX,newY,diameter,diameter);
    }
}
