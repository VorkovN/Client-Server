package Commands;

import Route.MyCollection;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute();
    void setArg(String Arg);
    void setMyCollection(MyCollection myCollection);
    String getArg();
    MyCollection getMyCollection();
}
