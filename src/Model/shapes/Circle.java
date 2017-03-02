package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Circle extends FillableShape {

    private double diameter;

    public Circle(){
        type = "Circle";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);

        calcDimensions();

        if(isFill())
            graphicsContext.fillOval(getX1(),getY1(), diameter, diameter);

        graphicsContext.strokeOval(getX1(),getY1(), diameter, diameter);
    }

    @Override
    public boolean selectShape(double x, double y) {

        double centerX = getX1() + (diameter/2);
        double centerY = getY1() + (diameter/2);

        if(Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y -centerY, 2)) < diameter/2){
            return true;
        }

        return false;
    }

    private void calcDimensions(){

        if(getX2() < getX1()){
            double temp = getX2();
            setX2(getX1());
            setX1(temp);
        }

        if(getY2() < getY1()){
            double temp = getY2();
            setY2(getY1());
            setY1(temp);
        }

        this.diameter = Math.sqrt(Math.pow(getX2() - getX1(), 2) + Math.pow(getY2() - getY1(), 2));

    }
}
