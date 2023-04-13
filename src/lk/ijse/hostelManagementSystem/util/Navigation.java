package lk.ijse.hostelManagementSystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane anchorPane;
    public static Stage stage;


    public enum Routes {
        AUTHENTICATION,ROOM,STUDENT,USER,RESERVATION,DASHBOARD
    }

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.anchorPane = pane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();

        switch (route) {
            case AUTHENTICATION:
                window.setTitle("Authentication Form");
                initUI("/lk/ijse/hostelManagementSystem/view/Authentication.fxml");
                break;
            case DASHBOARD:
                window.setTitle("Dashboard");
                initUI("/lk/ijse/hostelManagementSystem/view/Dashboard.fxml");
                break;
            case STUDENT:
                window.setTitle("Student Manage");
                initUI("/lk/ijse/hostelManagementSystem/view/StudentManageForm.fxml");
                break;
            case ROOM:
                window.setTitle("Room Manage");
                initUI("/lk/ijse/hostelManagementSystem/view/RoomManageForm.fxml");
                break;
            case RESERVATION:
                window.setTitle("Reservation");
                initUI("/lk/ijse/hostelManagementSystem/view/ReservationForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource(location)));
    }


}
