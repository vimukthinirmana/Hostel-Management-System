package lk.ijse.hostelManagementSystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case AUTHENTICATION:
                window.setTitle("Authentication Form");
                initUI("Authentication.fxml");
                break;
            case DASHBOARD:
                window.setTitle("Dashboard");
                initUI("MainForm.fxml");
                break;
            case STUDENT:
                window.setTitle("Student Manage");
                initUI("StudentManageForm.fxml");
                break;
            case ROOM:
                window.setTitle("Room Manage");
                initUI("RoomManageForm.fxml");
                break;
            case RESERVATION:
                window.setTitle("Reservation");
                initUI("ReservationForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("lk/ijse/hostelManagementSystem/view/" + location)));
    }
}
