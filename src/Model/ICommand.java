package Model;

import Model.shapes.Shape;

/**
 * Created by Anton on 2017-02-21.
 */
public interface ICommand {

    void undo(Drawing drawing, Shape shape);

    void redo(Drawing drawing, Shape shape);

}
