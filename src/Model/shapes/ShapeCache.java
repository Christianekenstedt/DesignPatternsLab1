package Model.shapes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Anton on 2017-02-21.
 */
public class ShapeCache {

    Hashtable<String, Shape> shapeCache  = new Hashtable<String, Shape>();

    public ShapeCache(){
        Circle circle = new Circle("1");
        shapeCache.put("1", circle);

        Rectangle rect = new Rectangle("2");
        shapeCache.put("2", rect);

        Line line = new Line("3");
        shapeCache.put("3", line);

        Group group = new Group("4");
        shapeCache.put("4", group);
    }

    public ArrayList<Shape> getShapes(){
        ArrayList<Shape> shapes = new ArrayList<>();

        for(Shape s : shapeCache.values()){
            shapes.add(s.clone());
        }

        return shapes;
    }

    public Shape getShape(String id){
        Shape shape = shapeCache.get(id);
        return shape.clone();
    }
}
