package Model.shapes;

/**
 * Created by Anton on 2017-02-21.
 */
public class Line extends Shape  {

    public Line(String id){
        type = "Line";
        setId(id);
    }

    @Override
    public Line clone() {
        return clone();
    }
}
