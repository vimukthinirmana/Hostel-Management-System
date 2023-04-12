package lk.ijse.hostelManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {
    public static Stage stage=new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/hostelManagementSystem/view/Authentication.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage = stage;
        stage.setScene(scene);
        stage.setTitle("Authentication");
        stage.centerOnScreen();

        stage.show();
    }
}
