package co.edu.uniquindio.programacionIII.laboratoriohilos.controllers;

import java.io.IOException;

import co.edu.uniquindio.programacionIII.laboratoriohilos.app.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}