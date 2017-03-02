package Model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Anton on 2017-02-21.
 */
public class Rectangle extends FillableShape  {

    private double width, height;



    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);

        calcDimensions();

        if(isDashed())
            graphicsContext.setLineDashes(10);
        else
            graphicsContext.setLineDashes();

        if(isFill())
            graphicsContext.fillRect(getX1(), getY1(), width, height);

        graphicsContext.strokeRect(getX1(), getY1(), width, height);
    }

    @Override
    public boolean selectShape(double x, double y) {
        if(x > getX1() && x < getX2()){
            if(y > getY1() && y < getY2()){
                return true;
            }
        }
        return false;
    }

    private void calcDimensions(){
        //code here
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

        width = Math.abs(getX2() - getX1());
        height = Math.abs(getY2() - getY1());
    }
}
