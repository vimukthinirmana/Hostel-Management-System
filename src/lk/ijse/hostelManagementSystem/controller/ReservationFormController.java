package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReservationFormController {
    public JFXComboBox studentID;
    public JFXComboBox roomType;
    public CheckBox cbxStatus;
    public Label txtReservationId;
    public Label lblName;
    public Label lblQty;
    public Label lblReservationID;


    public TableView reservationDetailsTable;
    public TableColumn colReserveId;
    public TableColumn colStudentId;
    public TableColumn colDate;
    public TableColumn colRoomType;
    public TableColumn colStatus;


    public TableView keyMoneyPendinListTable;
    public TableColumn kmResiveID;
    public TableColumn kmRoomID;
    public TableColumn kmStudentID;
    public TableColumn kmStudentName;
    public TableColumn kmContact;
    public TableColumn kmDate;


    public void newReserveBtnOnAction(ActionEvent actionEvent) {
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
    }


    public void cbxStudentIdOnAction(ActionEvent actionEvent) {
    }

    public void cbxRoomTypeOnAction(ActionEvent actionEvent) {
    }

    public void cbxStatusOnAction(ActionEvent actionEvent) {
    }


    public void reserveBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
    }


}
