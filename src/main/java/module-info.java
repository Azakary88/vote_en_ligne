module com.example.voteenligne {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.voteenligne to javafx.fxml;
    opens com.example.voteenligne.controller to javafx.fxml;
    exports com.example.voteenligne;
    exports com.example.voteenligne.controller;
}
