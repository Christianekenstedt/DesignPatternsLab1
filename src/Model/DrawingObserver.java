package Model;

import javafx.scene.canvas.Canvas;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Anton on 2017-02-27.
 */
public class DrawingObserver implements Observer {

    IDrawing drawing = null;
    Canvas canvas = null;

    public DrawingObserver(IDrawing drawing, Canvas canvas){
        this.drawing = drawing;
        this.canvas = canvas;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == drawing){
            drawing.render(canvas.getGraphicsContext2D());
        }
    }
}
