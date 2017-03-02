package Model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-02-21.
 */
public class Group extends FillableShape  {

    private ArrayList<Shape> children = new ArrayList<>();

    public Group(){
        type = "Group";
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {

        for(Shape s: children){
            s.drawShape(graphicsContext);
        }
    }

    @Override
    public boolean selectShape(double x, double y) {
        for(Shape s: children){
            if(s.selectShape(x, y))
                return true;
        }
        return false;
    }

    public void setChildren(List<Shape> nodes){
        children.addAll(nodes);
    }

    public void setColor(String color){
        for(Shape s: children)
            s.setColor(color);
    }

    public void setLineWidth(double width){
        for(Shape s: children)
            s.setLineWidth(width);
    }

    public void setFillColor(String color){
        for(Shape s: children){
            if(s instanceof FillableShape){
               ((FillableShape) s).setFillColor(color);
            }
        }
    }

    public void setFill(boolean fill){
        for(Shape s: children){
            if(s instanceof FillableShape){
                ((FillableShape) s).setFill(fill);
            }
        }
    }

    public Shape clone(){
        Group g = (Group)super.clone();
        g.children = new ArrayList<>();
        return g;
    }
}
