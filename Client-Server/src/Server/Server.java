package Server;

import Commands.*;
import Route.MyCollection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MyCollection myCollection = new MyCollection();

        HelpCommand helpCommand = new HelpCommand();
        InfoCommand infoCommand = new InfoCommand();
        ShowCommand showCommand = new ShowCommand();
        SaveCommand saveCommand = new SaveCommand();
        AddCommand addCommand = new AddCommand();
        UpdateCommand updateCommand = new UpdateCommand();
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand();
        ClearCommand clearCommand = new ClearCommand();
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
        RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand();
        CountLessThanDistanceCommand countLessThanDistance = new CountLessThanDistanceCommand();
        FilterGreaterThanDistanceCommand filterGreaterThanDistance = new FilterGreaterThanDistanceCommand();
        RemoveAllByDistanceCommand removeAllByDistanceCommand = new RemoveAllByDistanceCommand();
        RemoveFirstCommand removeFirstCommand = new RemoveFirstCommand();

        ServerSocket serverSocket = new ServerSocket(3345);
        Socket socket = serverSocket.accept();
        ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
        while(true) {
            toClient.writeObject(((Command) fromClient.readObject()).execute());
        }
    }
}
