package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.hostelManagementSystem.repository.ReservationRepository;

import java.util.ArrayList;

public class ReservationFormController {

    public Label lblName;
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

    public JFXButton newReservationBtnID;
    public JFXButton canselBtnID;
    public JFXButton reserveBtnID;
    public JFXButton clearBtnID;
    public Label lblRoomQty;

    public JFXComboBox <String> cmbStudentID;
    public JFXComboBox <String> cmbRoomType;
    public JFXComboBox <String> cmbStatusID;

    private ReservationRepository reservationRepository = new ReservationRepository();

    public void initialize() {
        initUI();
        setCmbStatus();
//        initialize();
//        loadAllDate();

    }

    private void initUI() {
        lblReservationID.setText("");
        clearFiled();

        lblReservationID.setDisable(true);
        lblRoomQty.setDisable(true);
        cmbRoomType.setDisable(true);
        cmbStudentID.setDisable(true);
        cmbStatusID.setDisable(true);
        reserveBtnID.setDisable(true);
        canselBtnID.setDisable(true);
        clearBtnID.setDisable(true);
        reservationDetailsTable.setDisable(true);
        keyMoneyPendinListTable.setDisable(true);

    }

    public void newReserveBtnOnAction(ActionEvent actionEvent) {
        lblReservationID.setDisable(false);
        lblRoomQty.setDisable(false);
        reserveBtnID.setDisable(false);
        canselBtnID.setDisable(false);
        clearBtnID.setDisable(false);
        cmbRoomType.setDisable(false);
        cmbStudentID.setDisable(false);
        reservationDetailsTable.setDisable(false);
        keyMoneyPendinListTable.setDisable(false);
        cmbStatusID.setDisable(false);
        setGeneratedReservationId();
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        initUI();
    }



    public void reserveBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    private void clearFiled() {
        lblReservationID.setText("");
        lblRoomQty.setText("");
        lblName.setText("");
        cmbStudentID.getSelectionModel().clearSelection();
        cmbRoomType.getSelectionModel().clearSelection();
        cmbStatusID.getSelectionModel().clearSelection();
    }

    private void setCmbStatus() {
        ArrayList<String> status = new ArrayList<>();
        status.add("Paid");
        status.add("Non Paid");

        ObservableList<String> observableList = FXCollections.observableList(status);
        cmbStatusID.setItems(observableList);
    }

    public void setGeneratedReservationId() {
        String generatedReservationId = reservationRepository.generateReservationId();
        lblReservationID.setText(generatedReservationId);
    }


}
