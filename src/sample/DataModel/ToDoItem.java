package sample.DataModel;

import java.time.LocalDate;

public class ToDoItem {
    private String ShortDescription;
    private String description;
    private LocalDate deadLine;

    public ToDoItem(String shortDescription, String description, LocalDate deadLine) {
        this.ShortDescription = shortDescription;
        this.description = description;
        this.deadLine = deadLine;
    }

    public String getShortDiscription() {
        return ShortDescription;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTime() {
        return deadLine;
    }

    @Override
    public String toString() {
        return ShortDescription;
    }

}
