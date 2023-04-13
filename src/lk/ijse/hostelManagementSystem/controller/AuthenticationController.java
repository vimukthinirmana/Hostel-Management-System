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
        signInbtn2ID.setVisible(true);
        signinpaneID.setVisible(true);
        signupbtn2ID.setVisible(true);
        //new user details input database

    }


    @FXML
    void signupBtn2Action(ActionEvent event) {
        signupbtn2ID.setVisible(false);
        signinpaneID.setVisible(false);
        signupPane.setVisible(true);
        signInbtn2ID.setVisible(true);

    }

}
