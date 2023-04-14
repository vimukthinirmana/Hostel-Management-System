package lk.ijse.hostelManagementSystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostelManagementSystem.util.Navigation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static lk.ijse.hostelManagementSystem.AppInitializer.stage;

public class DashboardController {
    public AnchorPane mainPain;
    public Label dateTxt;
    public Label timeTxt;



    public void studentBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Navigation.Routes.STUDENT,mainPain);
    }

    public void roomBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Navigation.Routes.ROOM,mainPain);
    }


    public void reservationBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Navigation.Routes.RESERVATION,mainPain);
    }

    public void initialize(){

        LocalDate date = LocalDate.now();
        dateTxt.setText(String.valueOf(date));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            DateTimeFormatter dt=DateTimeFormatter.ofPattern("HH:mm:ss");
            timeTxt.setText(LocalTime.now().format(dt));
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) throws IOException {
        stage.close();

        Stage stage=new Stage();
        Parent window = FXMLLoader.load(this.getClass().getResource("/lk/ijse/hostelManagementSystem/view/Authentication.fxml"));
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setTitle("Authentication");
        stage.centerOnScreen();
        stage.show();
    }
}
