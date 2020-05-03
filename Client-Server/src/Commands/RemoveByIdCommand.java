package Commands;
import Exceptions.NonexistentArgumentException;
import Route.MyCollection;

public class RemoveByIdCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        try{
            myCollection.removeById(arg);
            System.out.println("Element was removed");
        }catch (NonexistentArgumentException e){
            System.out.println("\nNonexistent argument id, please enter your command again!");
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of id, please enter your command again!");
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
