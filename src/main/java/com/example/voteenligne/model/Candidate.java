package com.example.voteenligne.model;

public class Candidate {
    private int id;
    private String name;
    private int electionId;
    private int voteCount; // Ajouter une variable pour le nombre de votes

    public Candidate(int id, String name, int electionId) {
        this.id = id;
        this.name = name;
        this.electionId = electionId;
        this.voteCount = 0; // Initialiser le nombre de votes à 0
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getElectionId() {
        return electionId;
    }

    // Nouvelle méthode pour obtenir le nombre de votes
    public int getVoteCount() {
        return voteCount;
    }

    // Méthode pour augmenter le nombre de votes
    public void incrementVoteCount() {
        this.voteCount++;
    }

    // Si nécessaire, ajouter une méthode pour définir le nombre de votes
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
