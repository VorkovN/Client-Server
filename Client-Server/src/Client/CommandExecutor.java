package Client;

import Commands.*;
import Route.MyCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static CommandExecutor commandExecutor = null;

    public static CommandExecutor getCommandExecutor() throws IOException {
        if (commandExecutor == null) {
            commandExecutor = new CommandExecutor();

            commandExecutor.addCommand("add", new AddCommand());
            commandExecutor.addCommand("clear", new ClearCommand());
            commandExecutor.addCommand("count_less_than_distance", new CountLessThanDistanceCommand());
            commandExecutor.addCommand("execute_script", new ExecuteScriptCommand());
            commandExecutor.addCommand("exit", new ExitCommand());
            commandExecutor.addCommand("filter_greater_than_distance", new FilterGreaterThanDistanceCommand());
            commandExecutor.addCommand("help", new HelpCommand());
            commandExecutor.addCommand("history", new HistoryCommand());
            commandExecutor.addCommand("info", new InfoCommand());
            commandExecutor.addCommand("remove_all_by_distance", new RemoveAllByDistanceCommand());
            commandExecutor.addCommand("remove_by_id", new RemoveByIdCommand());
            commandExecutor.addCommand("remove_first", new RemoveFirstCommand());
            commandExecutor.addCommand("remove_greater", new RemoveGreaterCommand());
            commandExecutor.addCommand("save", new SaveCommand());
            commandExecutor.addCommand("show", new ShowCommand());
            commandExecutor.addCommand("update", new UpdateCommand());
        }
        return commandExecutor;
    }



    private static Map<String, Command> commands = new HashMap<>();
    public static ArrayList<String> history = new ArrayList<>();
    private Socket socket = new Socket("localhost",3345);
    private ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
    private ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());

    public CommandExecutor() throws IOException {
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }
    public void execute(String action, MyCollection myCollection) throws IOException {

        String[] actionParts = action.split(" ");
        if (action.isEmpty()) {
            return;
        }
        if (actionParts.length == 1) {
            Command command = commands.get(actionParts[0]);
            if (command != null) {
                historyList(actionParts[0]);
                command.setMyCollection(myCollection);
                toServer.writeObject(command);
                //command.execute();
            } else {
                System.out.println("Commands.Command doesn't exist");
            }
        } else if (actionParts.length == 2) {
            Command command = commands.get(actionParts[0]);
            String arg = actionParts[1];
            if (command != null) {
                historyList(actionParts[0]);
                command.setMyCollection(myCollection);
                command.setArg(arg);
                toServer.writeObject(command);
                //command.execute();
            } else {
                System.out.println("Commands.Command doesn't exist");
            }
        } else {
            System.out.println("Wrong command input");
        }
    }

    static public void historyList(String command){
        if(history.size() > 6) {
            history.remove(0);
        }
        history.add(command);
    }
}