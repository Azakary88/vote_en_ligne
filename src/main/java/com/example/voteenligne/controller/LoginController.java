package com.example.voteenligne.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Remplacez par votre logique de validation ici
        if ("admin".equals(username) && "password".equals(password)) {
            // Chargement de la nouvelle page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
                Parent nextPage = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(nextPage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nom d'utilisateur ou mot de passe incorrect.");
            alert.showAndWait();
        }
    }
}
