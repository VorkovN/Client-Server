package Commands;
import Exceptions.NonexistentArgumentException;
import Route.MyCollection;

public class RemoveFirstCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        try {
            myCollection.removeFirst();
        }catch (NonexistentArgumentException e){
          e.getMessage();
        }
        System.out.println("First element was removed");
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
