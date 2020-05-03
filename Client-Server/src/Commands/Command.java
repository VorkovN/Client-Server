package Commands;

import Msg.MessageToServer;
import Route.MyCollection;
import Route.Route;

import java.io.Serializable;

public interface Command extends Serializable {
    String getArg();
    Route getNewRoute();
    MyCollection getMyCollection();
    void setArg(String Arg);
    void setNewRoute(Route newRoute);
    void setMyCollection(MyCollection myCollection);
    MessageToServer execute();

}
