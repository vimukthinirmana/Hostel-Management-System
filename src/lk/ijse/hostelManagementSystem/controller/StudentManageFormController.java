package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.repository.StudentRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentManageFormController implements Initializable {

    public Label lblStudentID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtContactNo;
    public ComboBox<String> cbxGender;
    public TableView studentTableView;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colAge;
    public TableColumn colContactNo;

    public JFXButton saveBtnId;
    public JFXButton deleteBtnId;
    public JFXButton clearBtnId;
    public JFXButton canselBtnId;

    private StudentRepository studentRepository = new StudentRepository();

    public void initialize() {
        initUI();
        setCmbGender();

    }

    private void initUI() {
        lblStudentID.setText("");
        clearFiled();

        lblStudentID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtAge.setDisable(true);
        cbxGender.setDisable(true);

        saveBtnId.setDisable(true);
        deleteBtnId.setDisable(true);
        canselBtnId.setDisable(true);
        clearBtnId.setDisable(true);


    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        //new student details input database
        boolean isValidate = checkValidation();
        if (isValidate) {


            String sId = lblStudentID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contactNo = txtContactNo.getText();
            String gender = cbxGender.getSelectionModel().getSelectedItem();
            String age = txtAge.getText();


            try {
                boolean isAdded = studentRepository.addStudent(
                        new Student(
                                sId,
                                name,
                                address,
                                contactNo,
                                gender,
                                age

                        )
                );

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Added!").show();
                    initialize();
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

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFiled();
    }



    public void addStudentBtnOnAction(ActionEvent actionEvent) {
        lblStudentID.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtAge.setDisable(false);
        cbxGender.setDisable(false);

        saveBtnId.setDisable(false);
        deleteBtnId.setDisable(false);
        canselBtnId.setDisable(false);
        clearBtnId.setDisable(false);

        setGeneratedStudentId();
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        initUI();
    }


    private boolean checkValidation() {

        String name = txtName.getText();
        String address = txtAddress.getText();
        String age = txtAge.getText();
        String contactNo = txtContactNo.getText();

        if (!name.matches("[A-Za-z ]+")) {
            txtName.requestFocus();
            return false;
        } else if (!address.matches("[A-Za-z ]+")) {
            txtAddress.requestFocus();
            return false;
        } else if (!contactNo.matches(".*(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}")) {
            txtContactNo.requestFocus();
            return false;
        } else if (!age.matches("^(1[0-9]{1,2}|[1-9][0-9]|[0-9])$")) {
            txtAge.requestFocus();
        }
        return true;
    }

    private void setCmbGender() {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");


        ObservableList<String> observableList = FXCollections.observableList(genders);
        cbxGender.setItems(observableList);
    }

    private void clearFiled() {
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtAge.clear();
        cbxGender.getSelectionModel().clearSelection();
    }

    public void setGeneratedStudentId() {
        String generatedStudentId = studentRepository.generateStudentId();
        lblStudentID.setText(generatedStudentId);
    }

    public void initialize(URL location, ResourceBundle resources) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllDate();
    }

    private void loadAllDate(){
        ArrayList<Student> students = (ArrayList<Student>) studentRepository.getAll();

        ObservableList observableList= FXCollections.observableArrayList(students);
        studentTableView.setItems(observableList);
    }


}
