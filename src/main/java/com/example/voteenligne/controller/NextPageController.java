package com.example.voteenligne.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class NextPageController {
    @FXML
    protected void handleViewElections() {
        // Logique pour afficher les élections
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Affichage des élections...");
        alert.showAndWait();
    }

    @FXML
    protected void handleLogout() {
        // Logique pour déconnecter l'utilisateur
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Déconnexion de l'utilisateur...");
        alert.showAndWait();
        // Optionnellement, vous pouvez revenir à la page de connexion ici
    }
}
