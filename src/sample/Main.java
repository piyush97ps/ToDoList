package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataModel.ToDoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainToDo.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init()  {
        try{
            ToDoData.getInstance().loadToDoItems();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try{
            ToDoData.getInstance().storeTodoItems();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
