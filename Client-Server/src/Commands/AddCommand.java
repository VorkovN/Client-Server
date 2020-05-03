package Commands;
import Route.MyCollection;

public class AddCommand implements Command{

    MyCollection myCollection;
    String arg;

    public void execute() {
        myCollection.add();
        System.out.println("Route.Route was added to list");
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
