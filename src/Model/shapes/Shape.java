package Model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.Serializable;

/**
 * Created by Anton on 2017-02-21.
 */
public abstract class Shape implements Cloneable, Serializable{

    private double X1, Y1, X2, Y2;

    private String id;
    protected String type;


    private String color = Color.BLACK.toString();
    private double lineWidth = 1;

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
        graphicsContext.setLineWidth(lineWidth);
        graphicsContext.setStroke(Paint.valueOf(color));
    }

    public abstract void drawShape(GraphicsContext graphicsContext);

    public abstract boolean selectShape(double x, double y);

    public double getX1() {
        return X1;
    }

    public void setX1(double x1) {
        X1 = x1;
    }

    public double getY1() {
        return Y1;
    }

    public void setY1(double y1) {
        Y1 = y1;
    }

    public double getX2() {
        return X2;
    }

    public void setX2(double x2) {
        X2 = x2;
    }

    public double getY2() {
        return Y2;
    }

    public void setY2(double y2) {
        Y2 = y2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }
}
