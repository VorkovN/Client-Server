package Commands;
import Exceptions.NonexistentArgumentException;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

public class RemoveFirstCommand implements Command {

    MyCollection myCollection;
    String arg;
    Route newRoute;

    public MessageToServer execute() throws NonexistentArgumentException {
        MessageToServer msg = new MessageToServer();
        msg.setStr(myCollection.removeFirst());
        return msg;
        //e.getMessage();
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
