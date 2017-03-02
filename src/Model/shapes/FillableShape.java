package Model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by Anton on 2017-02-27.
 */
public abstract class FillableShape extends Shape{
    private String fillColor = Color.WHITE.toString();
    private boolean fill;


    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void Draw(GraphicsContext gc){
        super.Draw(gc);

        gc.setFill(Paint.valueOf(fillColor));
    }
}
