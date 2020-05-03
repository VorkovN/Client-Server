package Commands;
import Route.MyCollection;

public class InfoCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        try {
            myCollection.info();
        }catch (IndexOutOfBoundsException e){
            System.out.println("List doesn't have any elements");
        }
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
