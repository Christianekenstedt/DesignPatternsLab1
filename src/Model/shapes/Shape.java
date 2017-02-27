package Model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Anton on 2017-02-21.
 */
public abstract class Shape implements Cloneable{

    private double X, Y, width, height;

    private String id;
    protected String type;
    private Color color;

    public Shape clone(){
        try {
            return (Shape)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getType(){
        return this.type;
    }


    public void Draw(GraphicsContext graphicsContext){
        //generic draw stuff
    }

    public abstract void drawShape(GraphicsContext graphicsContext);

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
