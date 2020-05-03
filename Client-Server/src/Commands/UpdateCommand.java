package Commands;
import Exceptions.NonexistentArgumentException;
import Route.MyCollection;

public class UpdateCommand implements Command {

    MyCollection myCollection;
    String arg;

    public void execute() {
        try{
            myCollection.update(arg);
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
