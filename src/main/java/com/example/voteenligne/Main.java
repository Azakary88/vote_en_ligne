package com.example.voteenligne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

    public class Main extends Application {
        public static Stage primaryStage; // Stocker une référence au primaryStage

        @Override
        public void start(Stage primaryStage) throws Exception {
            Main.primaryStage = primaryStage; // Initialiser la référence
            loadMainPage(); // Charger la page principale
        }

        public static void loadMainPage() throws IOException {
            Parent root = FXMLLoader.load(Main.class.getResource("/view/Main.fxml"));
            primaryStage.setTitle("Vote en Ligne");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

