package Model.shapes;

import javafx.scene.paint.Color;

/**
 * Created by Anton on 2017-02-27.
 */
public abstract class FillableShape extends Shape{
    private Color fillColor = Color.WHITE;
    private boolean fill;


    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
