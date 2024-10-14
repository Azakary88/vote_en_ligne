package com.example.voteenligne.controller;

import com.example.voteenligne.manager.DataManager;
import com.example.voteenligne.model.Candidate;
import com.example.voteenligne.model.Election;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    @FXML
    private ComboBox<Election> userElectionComboBox;
    @FXML
    private ComboBox<Candidate> userCandidateComboBox;
    @FXML
    private Button backButton; // Bouton pour revenir à la page d'administration
    @FXML
    private Button showResultsButton; // Bouton pour afficher les résultats

    private ObservableList<Election> elections = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidates = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Remplir les élections à partir du DataManager
        elections.setAll(DataManager.getInstance().getElections());
        candidates.setAll(DataManager.getInstance().getCandidates());

        // Configurer le ComboBox pour afficher le nom des élections
        userElectionComboBox.setItems(elections);
        userElectionComboBox.setCellFactory(new Callback<ListView<Election>, ListCell<Election>>() {
            @Override
            public ListCell<Election> call(ListView<Election> param) {
                return new ListCell<Election>() {
                    @Override
                    protected void updateItem(Election item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        // Listener pour mettre à jour les candidats en fonction de l'élection sélectionnée
        userElectionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Filtrer les candidats par l'ID de l'élection sélectionnée
                userCandidateComboBox.setItems(FXCollections.observableArrayList(
                        candidates.filtered(candidate -> candidate.getElectionId() == newValue.getId())
                ));
            } else {
                userCandidateComboBox.setItems(FXCollections.observableArrayList()); // Réinitialiser si aucune élection sélectionnée
            }
        });
    }

    @FXML
    private void handleVote() {
        Election selectedElection = userElectionComboBox.getSelectionModel().getSelectedItem();
        Candidate selectedCandidate = userCandidateComboBox.getSelectionModel().getSelectedItem();

        if (selectedElection == null || selectedCandidate == null) {
            showAlert("Veuillez sélectionner une élection et un candidat avant de voter.");
            return;
        }

        // Enregistrer le vote
        selectedCandidate.incrementVoteCount();

        System.out.println("Vote enregistré pour " + selectedCandidate.getName() + " dans l'élection " + selectedElection.getName());
        showAlert("Vote enregistré avec succès pour " + selectedCandidate.getName() + ".");

        // Réinitialiser les sélections
        userElectionComboBox.getSelectionModel().clearSelection();
        userCandidateComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBack() {
        // Code pour retourner à la page d'administration
        // Exemple : charger la scène d'administration et l'afficher
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene adminScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/voteenligne/view/Admin.fxml")));
            stage.setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowResults() {
        // Code pour afficher les résultats par candidat et par élection
        StringBuilder results = new StringBuilder();
        for (Election election : elections) {
            results.append("Résultats pour ").append(election.getName()).append(":\n");
            for (Candidate candidate : candidates.filtered(c -> c.getElectionId() == election.getId())) {
                // Ajouter les résultats des votes pour chaque candidat
                results.append(candidate.getName()).append(": ").append(candidate.getVoteCount()).append(" votes\n");
            }
            results.append("\n");
        }

        showAlert(results.toString());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
