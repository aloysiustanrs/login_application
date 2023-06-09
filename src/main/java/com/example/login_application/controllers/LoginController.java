package com.example.login_application.controllers;


import com.example.login_application.model.User;
import com.example.login_application.repository.UserRepository;
import com.example.login_application.service.UserAuthentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public class LoginController{
    @FXML
    private TextField password_input;
    @FXML
    private TextField username_input;

    public static User currUser;

    // Create a stage object and get the mainStage from the Application.java file
    public static Stage mainStage;

    public void setStage(Stage stage) {
        mainStage = stage;
    }

    public void loginCheck() throws IOException {

        String username_check = username_input.getText().trim();
        String password_check = password_input.getText().trim();

        UserAuthentication userAuthentication = new UserAuthentication();

        ArrayList<Boolean> loginResult = userAuthentication.loginAuthentication(username_check,password_check);

        boolean loginSuccessful = loginResult.get(0);
        boolean isManager = loginResult.get(1);

        if (loginSuccessful){


            // if login successful , means the login details is correct ,currUser is updated
            UserRepository repo = new UserRepository();
            currUser = repo.getUserByUsername(username_check);


//           If the user is a manager, load manager page
            if (isManager){
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/login_application/manager.fxml"));
                Scene scene = new Scene(root, 700, 500);
                mainStage.setTitle("Manager");
                mainStage.setScene(scene);
                mainStage.show();
            }
//            else , load user page
            else{

                Parent root = FXMLLoader.load(getClass().getResource("/com/example/login_application/user.fxml"));
                Scene scene = new Scene(root, 700, 500);


                mainStage.setTitle("User");
                mainStage.setScene(scene);
                mainStage.show();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Invalid userid or password");
        }


    }


}