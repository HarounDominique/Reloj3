package com.example.reloj3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloController {
    private boolean relojEncendido = false;
    private boolean modo24horas = true;
    private Reloj reloj;

    @FXML
    private Label relojLabel;

    @FXML
    private Button onOffButton;
    @FXML
    private Button onChangeModoHoras;


    @FXML
    protected void onOnOffButtonClick() {
        if (relojEncendido) {
            relojEncendido = false;
            onOffButton.setText("ON");
            reloj.detener();
        } else {
            relojEncendido = true;
            onOffButton.setText("OFF");
            reloj = new Reloj();
            reloj.iniciar();
        }
    }

    @FXML
    protected void cambiarModoHora() {
        if(modo24horas){
            modo24horas = false;
            onChangeModoHoras.setText("24h");
        }else{
            modo24horas = true;
            onChangeModoHoras.setText("12h");
        }
    }

    private class Reloj extends Thread implements Serializable {
        private boolean relojFuncionando = false;

        public void iniciar() {
            relojFuncionando = true;
            this.start();
        }

        public void detener() {
            relojFuncionando = false;
        }

        @Override
        public void run() {
            while (relojFuncionando) {
                try {
                    sleep(1000);
                    Platform.runLater(() -> {
                        if(modo24horas) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            relojLabel.setText(LocalDateTime.now().format(formatter));
                        }else{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                            relojLabel.setText(LocalDateTime.now().format(formatter));
                        }
                    });
                } catch (InterruptedException e) {
                    System.out.println("El reloj se ha detenido inesperadamente.");
                    e.printStackTrace();
                }
            }
        }
    }
}