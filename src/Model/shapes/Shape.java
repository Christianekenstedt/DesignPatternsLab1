package Model.shapes;

import javafx.scene.paint.Color;

/**
 * Created by Anton on 2017-02-21.
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;
    private Color color;

    public abstract Shape clone();


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
