package com.example.reloj3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloController {
    private boolean relojEncendido = false;
    private Reloj reloj;

    @FXML
    private Label relojLabel;

    @FXML
    private Button onOffButton;

    @FXML
    protected void onHelloButtonClick() {
        if (relojEncendido) {
            relojEncendido = false;
            onOffButton.setText("Encender");
            reloj.detener();
        } else {
            relojEncendido = true;
            onOffButton.setText("Apagar");
            reloj = new Reloj();
            reloj.iniciar();
        }
    }

    private class Reloj extends Thread {
        private boolean ejecutando = false;

        public void iniciar() {
            ejecutando = true;
            this.start();
        }

        public void detener() {
            ejecutando = false;
        }

        @Override
        public void run() {
            while (ejecutando) {
                try {
                    sleep(1000);
                    Platform.runLater(() -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        relojLabel.setText(LocalDateTime.now().format(formatter));
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}