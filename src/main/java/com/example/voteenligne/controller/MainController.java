package com.example.voteenligne.controller;
import com.example.voteenligne.Main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;



public class MainController {

    // Méthode pour gérer le passage à la page administrateur
    public void handleAdminPage() {
        loadPage("Admin.fxml"); // Assurez-vous que le fichier Admin.fxml existe dans le bon répertoire
    }

    // Méthode pour gérer le passage à la page utilisateur
    public void handleUserPage() {
        loadPage("User.fxml"); // Assurez-vous que le fichier User.fxml existe dans le bon répertoire
    }

    // Méthode générique pour charger une page
    private void loadPage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + fxml));
            Main.primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace(); // Pour le débogage
        }
    }

}
