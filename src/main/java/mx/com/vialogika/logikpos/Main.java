package mx.com.vialogika.logikpos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("logikpos.fxml"));
        primaryStage.setScene(new Scene(parent,800,600));
        primaryStage.show();
    }
}
