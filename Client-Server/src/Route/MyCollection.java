package Route;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyCollection implements Serializable {

    private ArrayList<Route> arr = new ArrayList<Route>();

    public MyCollection() {
        Route newRoute = new Route();
        try (Reader reader = new FileReader("laba5.json")) {
            BufferedReader bufferedReader = new BufferedReader(reader);//я не хотел этого делать, меня заставилт :(
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);
            JSONObject route = (JSONObject) jsonObject.get("Route");
            newRoute.setId(0);
            newRoute.setName((String) route.get("name"));
            JSONObject coordinates = (JSONObject) route.get("Coordinates");
            newRoute.setX(((Double) coordinates.get("x")).floatValue());
            newRoute.setY((Double) coordinates.get("y"));
            JSONObject location1 = (JSONObject) route.get("Location1");
            newRoute.setXl1((Long) location1.get("xl1"));
            newRoute.setYl1((Double) location1.get("yl1"));
            newRoute.setZl1((long) location1.get("zl1"));
            JSONObject location2 = (JSONObject) route.get("Location2");
            newRoute.setXl2((int) (long) location2.get("xl2"));
            newRoute.setYl2(((Double) location2.get("yl2")).floatValue());
            newRoute.setNamel2((String) location2.get("namel2"));
            newRoute.setDistance((float) (Long) route.get("distance"));
            LocalDate date = LocalDate.now();
            newRoute.setDate(date);
            arr.add(newRoute);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public String help() {
        return "help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "add {element} : добавить новый элемент в коллекцию \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл \n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n" +
                "remove_first : удалить первый элемент из коллекции \n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный \n" +
                "history : вывести последние 7 команд (без их аргументов) \n" +
                "remove_all_by_distance distance : удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному \n" +
                "count_less_than_distance distance : вывести количество элементов, значение поля distance которых меньше заданного \n" +
                "filter_greater_than_distance distance : вывести элементы, значение поля distance которых больше заданного \n" +
                "exit : завершить программу (без сохранения в файл) \n";

    }

    public String info() throws IndexOutOfBoundsException, NoSuchElementException {
        return "type: Roue\n"
                + "Дата инициализации: " + arr.get(arr.stream().findFirst().get().getId()).getDate() + '\n'
                + "Количество элементов: " + arr.size();
    }

    public String show() throws NoSuchElementException {
        StringBuilder s = new StringBuilder();
        arr.forEach(route -> s.append(route.toString()).append("\n"));
        return s.toString();
    }

    public String add(Route newRoute) {
        newRoute.setId(arr.size());
        arr.add(newRoute);
        return "Your values saved";
    }

    public String update(Route newRoute,String arg) throws NumberFormatException{
        int id = Integer.parseInt(arg);
            arr.set(id, newRoute);
        return "Input your values";
    }

    public String removeById(String arg) throws NumberFormatException{
        int id = Integer.parseInt(arg);
        arr.remove(id);
        return "Element is removed";
    }

    public String clear() {
        arr.clear();
        return "List was cleared";
    }

    public String save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.json"))) {
            for (Route route : arr) {
                JSONObject out = new JSONObject();
                out.put("id", route.getId());
                out.put("name", route.getName());
                JSONObject coordinates = new JSONObject();
                coordinates.put("x", route.getX());
                coordinates.put("y", route.getY());
                out.put("Route.Coordinates", coordinates);
                JSONObject location1 = new JSONObject();
                location1.put("xl1", route.getXl1());
                location1.put("yl1", route.getYl1());
                location1.put("zl1", route.getZl1());
                out.put("Route.Location1", location1);
                JSONObject location2 = new JSONObject();
                location2.put("xl2", route.getXl2());
                location2.put("yl2", route.getYl2());
                location2.put("namel2", route.getNamel2());
                out.put("Route.Location2", location2);
                out.put("distance", route.getDistance());
                bufferedWriter.write(out.toJSONString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Saved";
    }

    public String removeFirst() throws NumberFormatException {
        arr.remove(arr.stream().findFirst().get().getId());
        return "First element is removed";
    }

    public String removeGreater(String arg) throws NumberFormatException{
        int id = Integer.parseInt(arg);
        arr.subList(id, arr.size()).clear();
        return "Removed";
    }


    public String removeAllByDistance(String arg) throws NumberFormatException {
        int distance = Integer.parseInt(arg);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getDistance() == distance) {
                arr.remove(i);
            }
        }
        return "Removed";
    }

    public String countLessThanDistance(String arg) throws NumberFormatException {
        int distance = Integer.parseInt(arg);
        return "Number of elements: " + arr.stream().filter(route -> route.getDistance() < distance).count();
    }

    public String filterGreaterThanDistance(String arg) throws NumberFormatException, NoSuchElementException  {
        int distance = Integer.parseInt(arg);
        StringBuilder s = new StringBuilder();
        arr.stream().filter(route -> route.getDistance() > distance).forEach(route -> s.append(route.toString()).append("\n"));
        return s.toString();
    }
}
