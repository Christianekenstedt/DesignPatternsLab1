package Model.commands;

import java.util.LinkedList;

/**
 * Created by Anton on 2017-03-02.
 */
public class CommandManager {

    private LinkedList<ICommand> commandStack;

    public CommandManager(){
        commandStack = new LinkedList<>();
    }

    public void addCommand(ICommand cmd){
        commandStack.push(cmd);
    }

    public void executeCommand(){
        if(commandStack.size()>0)
            commandStack.pop().execute();
    }
}
