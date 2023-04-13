package lk.ijse.hostelManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static lk.ijse.hostelManagementSystem.AppInitializer.stage;

public class AuthenticationController {

    public JFXButton signupbtn2ID;


    public JFXButton signInbtn2ID;


    public Pane signupPane;


    public JFXButton signupBtn1ID;


    public TextField txtName;


    public TextField txtUserName;


    public TextField txtPassword;


    public Label lblUserId;


    public TextField txtContactNo;


    public Pane signinpaneID;


    public TextField txtusernameSignInID;


    public TextField txtPasswordSignInID;


    public JFXButton signInbtn1ID;
    public AnchorPane authenticationPaneID;
    public Label signInConfarmationlbl;
    public Label signupConfarmationlbl;

    private static int nextId = 1;//

    @FXML
    void signInBtn1nAction(ActionEvent event) throws IOException {
//user login dashboard

        stage.close();

        stage=new Stage();
        Parent window = FXMLLoader.load(this.getClass().getResource("/lk/ijse/hostelManagementSystem/view/Dashboard.fxml"));
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
//        stage.setFullScreen(true);
//        stage.setFullScreenExitHint("");
        stage.show();
    }


    @FXML
    void signInBtn2Action(ActionEvent event) {
        signupPane.setVisible(false);
        signinpaneID.setVisible(true);
        signupbtn2ID.setVisible(true);
    }


    @FXML
    void signUpBtn1Action(ActionEvent event) {
        //new user details input database
        boolean isValidate = checkValidation();
        lblUserId.setText(generateId());

        if(isValidate){
            signInbtn2ID.setVisible(true);
            signinpaneID.setVisible(true);
            signupbtn2ID.setVisible(true);

            String userId =lblUserId.getText();
            String name=txtName.getText();
            String userName=txtUserName.getText();
            String contactNo = txtContactNo.getText();
            String password = txtPassword.getText();



        }else {
            signupConfarmationlbl.setText("please check your details");

        }

    }


    @FXML
    void signupBtn2Action(ActionEvent event) {
        clearFields();
        signupbtn2ID.setVisible(false);
        signinpaneID.setVisible(false);
        signupPane.setVisible(true);
        signInbtn2ID.setVisible(true);

    }

    private boolean checkValidation(){

        String name=txtName.getText();
        String userName=txtUserName.getText();
        String contactNo = txtContactNo.getText();
        String password = txtPassword.getText();

        if (!name.matches("[A-Za-z ]+")) {
            txtName.requestFocus();
            return false;
        } else if (!userName.matches("[A-Za-z ]+")) {
            txtUserName.requestFocus();
            return false;
        } else if (!contactNo.matches(".*(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}")) {
            txtContactNo.requestFocus();
            return false;
        } else if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            txtPassword.requestFocus();
        }
        return true;
    }
    private void clearFields(){
        txtName.clear();
        txtUserName.clear();
        txtContactNo.clear();
        txtPassword.clear();
        signupConfarmationlbl.setText("");
    }




        public static synchronized String generateId() {
            String id = String.format("user-%03d", nextId);
            nextId++;
            return id;
        }



}
