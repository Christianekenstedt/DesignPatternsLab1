package Model.commands;

import Model.ICommand;
import Model.IDrawing;
import Model.shapes.Shape;

/**
 * Created by Anton on 2017-03-02.
 */
public class CmdUndo implements ICommand {
    private IDrawing drawing;
    private Shape shape;

    public CmdUndo(IDrawing drawing, Shape shape){
        this.drawing = drawing;
        this.shape = shape;
    }

    @Override
    public void execute() {
        drawing.removeShape(shape);
    }
}
