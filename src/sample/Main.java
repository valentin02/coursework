package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.classes.ClockGenerator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("course work");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setResizable(false);

        primaryStage.show();
        Controller controller = new Controller();
        Thread thread = new Thread(controller);
        thread.start();
        ClockGenerator clockGenerator = new ClockGenerator();
        Thread thread1 = new Thread(clockGenerator);
        thread1.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
