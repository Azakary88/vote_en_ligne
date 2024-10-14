package com.example.voteenligne.manager;

import com.example.voteenligne.model.Election;
import com.example.voteenligne.model.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManager {
    private static DataManager instance;

    private ObservableList<Election> elections = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidates = FXCollections.observableArrayList();

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ObservableList<Election> getElections() {
        return elections;
    }

    public ObservableList<Candidate> getCandidates() {
        return candidates;
    }
}
