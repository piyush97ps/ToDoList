package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import sample.DataModel.ToDoData;
import sample.DataModel.ToDoItem;

import java.time.LocalDate;


public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public ToDoItem processResult(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        if(!(shortDescription.equals("")&&details.equals("")&&deadline.equals(""))) {
            ToDoItem newItem = new ToDoItem(shortDescription, details, deadline);
            ToDoData.getInstance().addToDoItem(newItem);
            return newItem;
        }
        return null;
    }
}
