package Commands;
import Route.MyCollection;

public class ShowCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        myCollection.show();
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
}
