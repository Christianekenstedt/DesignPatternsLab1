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

        context.strokeLine(getX1(), getY1(), getX2(), getY2());
    }

    @Override
    public boolean selectShape(double x, double y) {

        double temp_x1, temp_x2, temp_y1, temp_y2;

        if(getX2() < getX1()){
            temp_x1 = getX2();
            temp_x2 = getX1();
        }else{
            temp_x1 = getX1();
            temp_x2 = getX2();
        }

        if(getY2() < getY1()){
            temp_y1 = getY2();
            temp_y2 = getY1();
        }else{
            temp_y1 = getY1();
            temp_y2 = getY2();
        }

        if(x > temp_x1 - 5 && x < temp_x2 + 5){
            if(y > temp_y1 - 5 && y < temp_y2 + 5){
                return true;
            }
        }
        return false;
    }
}
