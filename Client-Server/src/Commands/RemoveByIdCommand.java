package Commands;
import Exceptions.NonexistentArgumentException;
import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

public class RemoveByIdCommand implements Command {

    MyCollection myCollection = null;
    String arg = null;
    Route newRoute = null;

    public MessageToServer execute() throws NumberFormatException, NonexistentArgumentException {
        MessageToServer msg = new MessageToServer();
        msg.setStr(myCollection.removeById(arg));
        return msg;
        //"\nNonexistent argument id, please enter your command again!";
        //"\nWrong format of id, please enter your command again!";
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
