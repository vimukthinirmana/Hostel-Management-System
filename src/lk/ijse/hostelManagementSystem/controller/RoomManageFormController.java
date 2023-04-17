package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.repository.RoomRepository;
import lk.ijse.hostelManagementSystem.repository.StudentRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomManageFormController  implements Initializable {
    public Label lblRoomID;
    public TextField txtQty;
    public TextField txtKeyMoney;


    public TableView roomTableView;
    public TableColumn colRoomID;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXButton addRoomBtnId;
    public JFXButton canselBtnId;
    public JFXButton saveBtnId;
    public JFXButton updateBtnId;
    public JFXButton deleteBtnId;
    public JFXButton clearBtnId;
    public TextField txtRoomType;

    private RoomRepository roomRepository = new RoomRepository();

    public void initialize() {
        initUI();
        initialize();
        loadAllDate();
    }

    private void initUI() {
        lblRoomID.setText("");
        clearFiled();

        lblRoomID.setDisable(true);
        txtRoomType.setDisable(true);
        txtQty.setDisable(true);
        txtKeyMoney.setDisable(true);
        roomTableView.setDisable(true);

        saveBtnId.setDisable(true);
        deleteBtnId.setDisable(true);
        updateBtnId.setDisable(true);
        canselBtnId.setDisable(true);
        clearBtnId.setDisable(true);


    }
    private void clearFiled() {
        lblRoomID.setText("");
        txtQty.clear();
        txtKeyMoney.clear();
        txtRoomType.clear();
    }



    public void saveBtnOnAction(ActionEvent actionEvent) {
        //new room details input database
        boolean isValidate = checkValidation();
        if (isValidate) {


            String rId = lblRoomID.getText();
            String type = txtRoomType.getText();
            String keyMoney = txtKeyMoney.getText();
            int qty = Integer.parseInt(txtQty.getText());



            try {
                boolean isAdded = roomRepository.addRoom(
                        new Room(
                                rId,
                                type,
                                keyMoney,
                                qty
                        )
                );

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Added!").show();
                    clearFiled();
                    loadAllDate();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else {
            new Alert(Alert.AlertType.WARNING, "please check your details").show();

        }

    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void addRoomBtnOnAction(ActionEvent actionEvent) {
        txtRoomType.requestFocus();

        lblRoomID.setDisable(false);
        txtRoomType.setDisable(false);
        txtQty.setDisable(false);
        txtKeyMoney.setDisable(false);
        roomTableView.setDisable(false);


        saveBtnId.setDisable(false);
        deleteBtnId.setDisable(false);
        updateBtnId.setDisable(false);
        canselBtnId.setDisable(false);
        clearBtnId.setDisable(false);

        setGeneratedRoomId();
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        initUI();
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Object selectedRoom = roomTableView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            boolean deleted = roomRepository.deleteRoom(selectedRoom);
            if (deleted) {
                roomTableView.getItems().remove(selectedRoom);
            } else {
                new Alert(Alert.AlertType.ERROR, "not deleted").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Select error").show();
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        boolean isValidate = checkValidation();
        if (isValidate) {


            String rId = lblRoomID.getText();
            String type = txtRoomType.getText();
            String keyMoney = txtKeyMoney.getText();
            int qty = Integer.parseInt(txtQty.getText());



            try {
                boolean isUpdated = roomRepository.updateRoom(
                        new Room(
                                rId,
                                type,
                                keyMoney,
                                qty
                        )
                );

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                    clearFiled();
                    loadAllDate();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else {
            new Alert(Alert.AlertType.WARNING, "please check your details").show();

        }
    }



    private boolean checkValidation() {
        String type = txtRoomType.getText();
        String qty = txtQty.getText();
        String keyMoney = txtKeyMoney.getText();

        if (!keyMoney.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid keymoney value").show();
            txtKeyMoney.requestFocus();
            return false;
        }else if (!qty.matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty value").show();
            txtQty.requestFocus();
            return false;
        }else if (!type.matches("^[a-zA-Z\\-\\/\\s]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid roomtype value").show();
            txtRoomType.requestFocus();
            return false;
        }
        return true;
    }

    public void setGeneratedRoomId() {
        String generatedRoomId = roomRepository.generateRoomId();
        lblRoomID.setText(generatedRoomId);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("rId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        // Load data from the selected row into the labels, text fields, and combo box
        roomTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadSelectedRowData();
        });

        loadAllDate();
    }

    private void loadAllDate(){
        ArrayList<Room> rooms = (ArrayList<Room>) roomRepository.getAll();

        ObservableList observableList= FXCollections.observableArrayList(rooms);
        roomTableView.setItems(observableList);
    }

    public void loadSelectedRowData() {
        // Get the selected row from the table
        Object selectedRow = roomTableView.getSelectionModel().getSelectedItem();

        // Cast the selected row to room type
        if(selectedRow instanceof Room) {
            Room selectedRoom = (Room) selectedRow;

            // Load data from the selected row into the labels, text fields, and combo box
            lblRoomID.setText(selectedRoom.getRId());
            txtRoomType.setText(selectedRoom.getType());
            txtKeyMoney.setText(selectedRoom.getKeyMoney());
            txtQty.setText(String.valueOf(selectedRoom.getQty()));
        }
    }


}
