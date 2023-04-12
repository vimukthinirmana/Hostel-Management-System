package lk.ijse.hostelManagementSystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    public AnchorPane mainPain;
    public Label dateTxt;
    public Label timeTxt;

    public void studentBtnOnAction(ActionEvent actionEvent) {
    }

    public void roomBtnOnAction(ActionEvent actionEvent) {
    }

    public void usersBtnOnAction(ActionEvent actionEvent) {
    }

    public void reservationBtnOnAction(ActionEvent actionEvent) {
    }

    void initialize() {
        setDateAndTime();
       //
    }

    private void setDateAndTime(){
        dateTxt.setText(String.valueOf(LocalDate.now()));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeTxt.setText(LocalTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
