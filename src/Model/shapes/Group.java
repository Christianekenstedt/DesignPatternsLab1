package Model.shapes;

/**
 * Created by Anton on 2017-02-21.
 */
public class Group extends Shape  {

    public Group(String id){
        type = "Group";
        setId(id);
    }

    @Override
    public Group clone() {
        return clone();
    }
}
