package com.example.voteenligne.model;

public class Election {
    private String name;
    private int id;

    public Election(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // Utilisé pour afficher le nom dans la liste déroulante
    }
}
