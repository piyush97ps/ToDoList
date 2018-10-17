package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import sample.DataModel.ToDoData;
import sample.DataModel.ToDoItem;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class Controller {

    private List<ToDoItem> todoItems;
    @FXML
    ListView<ToDoItem> toDoListView;
    @FXML
    TextArea detail;
    @FXML
    Label DueDate;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize(){

        toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
                if(newValue != null) {
                    ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
                    detail.setText(item.getDescription());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    DueDate.setText(df.format(item.getTime()));
                }
            }
        });

        toDoListView.getItems().setAll(ToDoData.getInstance().getList());
        toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Adding New TODO Item");
        dialog.setHeaderText("Use this dialog to create new Tdo item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("DialogPaneTodoList.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Problem Loading the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            ToDoItem item = controller.processResult();
            if(item != null) {
                toDoListView.getItems().setAll(ToDoData.getInstance().getList());
                toDoListView.getSelectionModel().select(item);
            }
            System.out.println("OK Pressed");
        }else{
            System.out.println("Canceled");
        }
    }


    @FXML
    public void handleMouseClicked(){
        ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
        detail.setText(item.getDescription());
        DueDate.setText(item.getTime().toString());
    }

}
