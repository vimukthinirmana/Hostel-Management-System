package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.repository.ReservationRepository;
import lk.ijse.hostelManagementSystem.repository.RoomRepository;
import lk.ijse.hostelManagementSystem.repository.StudentRepository;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {

    public Label lblName;
    public Label lblReservationID;


    public TableView reservationDetailsTable;
    public TableColumn colReserveId;
    public TableColumn colStudentId;
    public TableColumn colDate;
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
    public Label lblRoomId;
    public Label lblDate;
    public TableColumn colRoomId;

    private StudentRepository studentRepository = new StudentRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private ReservationRepository reservationRepository = new ReservationRepository();

//    private Date localDate;

    public void initialize() {
        initUI();
        setCmbStatus();
        loadStudentIds();
        loadRoomTypes();
        loadResiveDate();
        initialize();
        loadAllDate();

    }

    private void initUI() {
        lblReservationID.setText("");
        clearFiled();

        lblReservationID.setDisable(true);
        lblRoomQty.setDisable(true);
        lblDate.setDisable(true);
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
        lblDate.setDisable(false);
        reservationDetailsTable.setDisable(false);
        keyMoneyPendinListTable.setDisable(false);
        cmbStatusID.setDisable(false);
        setGeneratedReservationId();
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        initUI();
    }



    public void reserveBtnOnAction(ActionEvent actionEvent) {
        String resId = lblReservationID.getText();
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String status = cmbStatusID.getSelectionModel().getSelectedItem();
        Student student = studentRepository.getStudentById(cmbStudentID.getSelectionModel().getSelectedItem());
        Room room = roomRepository.getRoomById(lblRoomId.getText());

        try {
            boolean isAdded = reservationRepository.saveReservation(
                    new Reservation(
                            resId,
                            date,
                            status,
                            room,
                            student
                    )
            );
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation Saved!").show();
                // Update the room quantity
                int currentQty = room.getQty();
                if (currentQty > 0) {
                    room.setQty(currentQty - 1);
                    roomRepository.updateRoom(room);
                } else {
                    // Handle case where room quantity is already 0
                    System.out.println("No available rooms");
                    return;
                }

            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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


    private void loadStudentIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = (ArrayList<String>) studentRepository.getIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbStudentID.setItems(observableList);
            // Add listener to combo box selection property
            cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    String selectedId = newValue.toString();
                    Student selectedStudent = studentRepository.getStudentById(selectedId);

                    if (selectedStudent != null) {
                        lblName.setText( selectedStudent.getName());
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void loadRoomTypes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = (ArrayList<String>) roomRepository.getRoomTypes();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbRoomType.setItems(observableList);
            // Add event handler for combo box selection change
            cmbRoomType.setOnAction(event -> {
                String selectedRoomType = cmbRoomType.getValue();

                // Retrieve Room object based on selected room type
                Room room = roomRepository.getRoomByType(selectedRoomType);

                // Set roomId and qty values to labels
                if (room != null) {
                    lblRoomId.setText(room.getRId());
                    lblRoomQty.setText(Integer.toString(room.getQty()));
                } else {
                    lblRoomId.setText("");
                    lblRoomQty.setText("");
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void loadResiveDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colReserveId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room"));

        // Load data from the selected row into the labels, text fields, and combo box
        reservationDetailsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
        setCmbStatus();
        loadStudentIds();
        loadRoomTypes();
        loadAllDate();
    }

    private void loadAllDate(){
        ArrayList<Reservation>  reservations = (ArrayList<Reservation>) reservationRepository.getAll();

        ObservableList observableList= FXCollections.observableArrayList(reservations);
        reservationDetailsTable.setItems(observableList);
    }


}
