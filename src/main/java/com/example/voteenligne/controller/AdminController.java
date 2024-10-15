package com.example.voteenligne.controller;

import com.example.voteenligne.manager.DataManager;
import com.example.voteenligne.model.Election;
import com.example.voteenligne.model.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private TextField electionNameField;
    @FXML
    private TextField candidateNameField;
    @FXML
    private ComboBox<Election> electionComboBox;

    private ObservableList<Election> elections = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidates = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        electionComboBox.setItems(elections);
    }

    private void loadPage(String fxmlFile, String title, javafx.event.ActionEvent event) {
        try {
            System.out.println("Chargement de la page : " + fxmlFile); // Debug
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
            System.out.println("Page chargée avec succès : " + title); // Debug
        } catch (IOException e) {
            e.printStackTrace(); // Affiche la trace de la pile
            showAlert("Erreur lors du chargement de la page : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur inattendue : " + e.getMessage());
        }
    }



    @FXML
    private void handleAddElection() {
        String electionName = electionNameField.getText();
        if (electionName.isEmpty()) {
            showAlert("Election name cannot be empty!");
            return;
        }

        Election newElection = new Election(getNextElectionId(), electionName);
        DataManager.getInstance().getElections().add(newElection);
        electionComboBox.setItems(DataManager.getInstance().getElections()); // Mettre à jour la ComboBox
        electionNameField.clear();
    }

    @FXML
    private void handleAddCandidate() {
        String candidateName = candidateNameField.getText();
        Election selectedElection = electionComboBox.getSelectionModel().getSelectedItem();

        if (candidateName.isEmpty()) {
            showAlert("Candidate name cannot be empty!");
            return;
        }

        if (selectedElection == null) {
            showAlert("Please select an election for the candidate.");
            return;
        }

        Candidate newCandidate = new Candidate(getNextCandidateId(), candidateName, selectedElection.getId());
        DataManager.getInstance().getCandidates().add(newCandidate);
        candidateNameField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToUserPage(javafx.event.ActionEvent event) {
        loadPage("/view/User.fxml", "Page Utilisateur", event);
    }


    private int getNextElectionId() {
        return elections.stream().mapToInt(Election::getId).max().orElse(0) + 1;
    }

    private int getNextCandidateId() {
        return candidates.stream().mapToInt(Candidate::getId).max().orElse(0) + 1;
    }
}
