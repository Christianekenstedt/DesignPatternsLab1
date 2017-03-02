package Model.commands;

import Model.ICommand;
import Model.IDrawing;
import Model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2017-03-02.
 */
public class CmdRedo implements ICommand {
    private IDrawing drawing;
    private ArrayList<Shape> shapes = new ArrayList<>();

    public CmdRedo(IDrawing drawing, Shape shape){
        this.drawing = drawing;
        this.shapes.add(shape);
    }

    public CmdRedo(IDrawing drawing, List<Shape> shapes){
        this.drawing = drawing;
        this.shapes.addAll(shapes);
    }

    @Override
    public void execute() {
        System.out.println("Re-doing shape ");
        for(Shape s: shapes)
            System.out.println("dddd  " + s.getType());
        this.drawing.addShapes(shapes);
    }
}
