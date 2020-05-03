package Commands;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

public class ClearCommand implements Command {

    MyCollection myCollection;
    String arg;
    Route newRoute;

    public MessageToServer execute() {
        MessageToServer msg = new MessageToServer();
        msg.setStr(myCollection.clear());
        return msg;
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