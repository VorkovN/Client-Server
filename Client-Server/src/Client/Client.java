package Client;

import Route.MyCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {
        MyCollection myCollection = new MyCollection();



        while (true) {
            System.out.println("Enter you action, use \"help\" to get the list of all commands");
            System.out.print(">>>");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action = reader.readLine();
            if (!action.isEmpty()) {
                CommandExecutor.getCommandExecutor().execute(action, myCollection);
                System.out.println();
            }
        }
    }
}

