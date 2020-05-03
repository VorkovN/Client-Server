package Commands;
import Route.MyCollection;

public class FilterGreaterThanDistanceCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        try{
            myCollection.filterGreaterThanDistance(arg);
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of distance, please enter your command again!");
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
