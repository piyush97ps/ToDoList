package sample.DataModel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDoData {
    private static ToDoData ourInstance = new ToDoData();

    private List<ToDoItem> toDoItems  = new ArrayList<>();

    private String filename = "todoListitem.txt";
    private DateTimeFormatter formatter;

    public static ToDoData getInstance() {
        return ourInstance;
    }

    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("MM d,yyyy");
    }

    public List<ToDoItem> getList() {
        return toDoItems;
    }

//    public void setToDoItems(List<ToDoItem> toDoItems) {
//        this.toDoItems = toDoItems;
//    }

    public void addToDoItem(ToDoItem item){
        toDoItems.add(item);
    }


    public void loadToDoItems() throws IOException{
        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String input;
            while ((input = br.readLine()) != null) {
                String[] items = input.split("\t");

                String shortDescription = items[0];
                String detail = items[1];
                String dueDate = items[2];

                LocalDate date = LocalDate.parse(dueDate, formatter);
                ToDoItem toDoItem = new ToDoItem(shortDescription, detail, date);
                toDoItems.add(toDoItem);
            }
        }

    }


    public void storeTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<ToDoItem> item = toDoItems.iterator();
            while (item.hasNext()) {
                ToDoItem tempItem = item.next();
                bw.write(String.format("%s\t%s\t%s",
                        tempItem.getShortDiscription(),
                        tempItem.getDescription(),
                        tempItem.getTime().format(formatter)));
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
