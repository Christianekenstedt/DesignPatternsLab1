package Model.shapes;

import javafx.scene.paint.Color;

/**
 * Created by Anton on 2017-02-21.
 */
public abstract class Shape implements Cloneable{
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

}
