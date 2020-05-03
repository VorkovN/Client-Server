package Client;

import Commands.*;
import Exceptions.UnacceptableNumberException;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor<InputStreamWriter> {
    private static CommandExecutor commandExecutor = null;

    public static CommandExecutor getCommandExecutor() throws IOException {
        if (commandExecutor == null) {
            commandExecutor = new CommandExecutor();

            commandExecutor.addCommand("add", new AddCommand());
            commandExecutor.addCommand("clear", new ClearCommand());
            commandExecutor.addCommand("count_less_than_distance", new CountLessThanDistanceCommand());
            commandExecutor.addCommand("execute_script", new ExecuteScriptCommand());
            commandExecutor.addCommand("filter_greater_than_distance", new FilterGreaterThanDistanceCommand());
            commandExecutor.addCommand("help", new HelpCommand());
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
    private ObjectInputStream fromServer =new ObjectInputStream(socket.getInputStream());

    public CommandExecutor() throws IOException {
    }

    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }
    public void execute(String action, MyCollection myCollection) throws IOException, ClassNotFoundException {

        String[] actionParts = action.split(" ");
        if (action.isEmpty()) {
            return;
        }
        if (actionParts.length == 1) {
            switch (actionParts[0]) {
                case "history":
                    history();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    Command command = commands.get(actionParts[0]);
                    if (command != null) {
                        historyList(actionParts[0]);
                        command.setMyCollection(myCollection);
                        if (actionParts[0].equals("add")){
                            Route newRoute = null;
                            try {
                                newRoute = initialization();
                            } catch (NumberFormatException e) {
                                System.out.println("\nWrong input, please enter your values again!");
                            }
                            command.setNewRoute(newRoute);
                        }
                        toServer.writeObject(command);
                        System.out.println(((MessageToServer)fromServer.readObject()).getStr());
                        //command.execute();
                    } else {
                        System.out.println("Commands.Command doesn't exist");
                    }
            }
        } else if (actionParts.length == 2) {
            Command command = commands.get(actionParts[0]);
            String arg = actionParts[1];
            if (command != null) {
                historyList(actionParts[0]);
                command.setMyCollection(myCollection);
                command.setArg(arg);
                if (actionParts[0].equals("update")){
                    Route newRoute = null;
                    try {
                        newRoute = initialization();
                    } catch (NumberFormatException e) {
                        System.out.println("\nWrong input, please enter your values again!");
                    }
                    command.setNewRoute(newRoute);
                }
                toServer.writeObject(command);
                command.setArg(null);
                System.out.println(((MessageToServer)fromServer.readObject()).getStr());
                //command.execute();
            } else {
                System.out.println("Commands.Command doesn't exist");
            }
        } else {
            System.out.println("Wrong command input");
        }
        System.out.println("done");
    }





    static public void historyList(String command){
        if(history.size() > 6) {
            history.remove(0);
        }
        history.add(command);
    }

    public void history() {
        for (int i = 0; i < CommandExecutor.history.size(); i++) {
            System.out.println(CommandExecutor.history.get(i));
        }
    }

    public void exit() {
        System.exit(0);
    }

    public Route initialization() {
        Route newRoute = new Route();
        Scanner sc = new Scanner(System.in);

        date(newRoute);
        name(newRoute, sc);
        x(newRoute, sc);
        y(newRoute, sc);
        xl1(newRoute, sc);
        yl1(newRoute, sc);
        zl1(newRoute, sc);
        xl2(newRoute, sc);
        yl2(newRoute, sc);
        namel2(newRoute, sc);
        distance(newRoute, sc);
        return newRoute;
    }

    public void name(Route newRoute, Scanner sc) {
        try {
            System.out.print("(String) name = ");
            newRoute.setName(sc.nextLine());
        } catch (NumberFormatException e) {
            name(newRoute, sc);
        }
    }

    public void x(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Float) x = ");
            float x = Float.parseFloat(sc.nextLine());
            if (!((Float.MIN_VALUE < Math.abs(x)) && (Math.abs(x) < Float.MAX_VALUE))) {
                x(newRoute, sc);
                System.out.println("Wrong input");
            } else {
                newRoute.setX(x);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            x(newRoute, sc);
        }
    }

    public void y(Route newRoute, Scanner sc){
        try{
            System.out.print("(Double) y = ");
            double y = Double.parseDouble(sc.nextLine());
            if (!((Double.MIN_VALUE < Math.abs(y)) && (Math.abs(y) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setY(y);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            y(newRoute, sc);
        }
    }

    public void date(Route newRoute) {
        LocalDate date = LocalDate.now();
        newRoute.setDate(date);
        System.out.println("(LocalDatedate) = " + date);
    }

    public void xl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Long) xl1 = ");
            long xl1 = Long.parseLong(sc.nextLine());
            if (!((Long.MIN_VALUE < xl1) && (xl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl1(xl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            xl1(newRoute, sc);
        }
    }

    public void yl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Double) yl1 = ");
            double yl1 = Double.parseDouble(sc.nextLine());
            if (!((Double.MIN_VALUE < Math.abs(yl1)) && (Math.abs(yl1) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl1(yl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            yl1(newRoute, sc);
        }
    }

    public void zl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(long) zl1 = ");
            long zl1 = Long.parseLong(sc.nextLine());
            if (!((Long.MIN_VALUE < zl1) && (zl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setZl1(zl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            zl1(newRoute, sc);
        }
    }

    public void xl2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(int) xl2 = ");
            int xl2 = Integer.parseInt(sc.nextLine());
            if (!((Integer.MIN_VALUE < xl2) && (xl2 < Integer.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl2(xl2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            xl2(newRoute, sc);
        }
    }

    public void yl2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Float) yl2 = ");
            float yl2 = Float.parseFloat(sc.nextLine());
            if (!((Float.MIN_VALUE < Math.abs(yl2)) && (Math.abs(yl2) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl2(yl2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            yl2(newRoute, sc);
        }
    }

    public void namel2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(String) namel2 = ");
            String name = sc.nextLine();
            if (name.length() > 968) {
                throw new UnacceptableNumberException();
            } else {
                System.out.println(name);
                newRoute.setNamel2(name);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            namel2(newRoute, sc);
        }
    }

    public void distance(Route newRoute, Scanner sc) {
        try{
            System.out.print("(float) distance = ");
            float dist = (Float.parseFloat(sc.nextLine()));
            if (!((1 < Math.abs(dist)) && (Math.abs(dist) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setDistance(dist);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            distance(newRoute, sc);
        }
    }
}