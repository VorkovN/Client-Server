package Commands;

import Client.CommandExecutor;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

public class HistoryCommand implements Command {

    MyCollection myCollection;
    String arg;
    Route newRoute;

    public MessageToServer execute(){
        for (int i = 0; i < CommandExecutor.history.size(); i++) {
            System.out.println(CommandExecutor.history.get(i));
        }
        return null;
    }

    public void setMyCollection(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    public MyCollection getMyCollection() {
        return myCollection;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setNewRoute(Route newRoute) {
        this.newRoute = newRoute;
    }

    public Route getNewRoute() {
        return newRoute;
    }

}
