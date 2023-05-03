package com.example.reloj3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class HelloController {
    private boolean relojEncendido = false;
    private boolean modo24horas = true;
    private Reloj reloj;

    @FXML
    private Label relojLabel;

    @FXML
    private CheckBox checkBoxAlarm;

    @FXML
    private TextField minutos;

    @FXML
    private TextField hora;

    @FXML
    private Button onOffButton;
    @FXML
    private Button onChangeModoHoras;


    @FXML
    protected void onOnOffAlarmClick() {
        if (relojEncendido) {
            if (checkBoxAlarm.isSelected()) {
                System.out.println("Alarma encendida.");
                LocalTime horaAlarma = LocalTime.of(Integer.parseInt(hora.getText()), Integer.parseInt(minutos.getText()), 00);
//                System.out.println ("hora alarma"+ horaAlarma );
//                System.out.println ("hora reloj"+ LocalTime.parse(relojLabel.getText() ));
                if (horaAlarma.equals(LocalTime.parse(relojLabel.getText()))) {
                    for (int i = 0; i < 100; i++) {
                        System.out.println("ALARMA");
                    }
                }

            } else {
                System.out.println("Alarma apagada.");
            }
        }
    }

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
        if (modo24horas) {
            modo24horas = false;
            onChangeModoHoras.setText("24h");
        } else {
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
                        if (modo24horas) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            relojLabel.setText(LocalDateTime.now().format(formatter));
                            if (checkBoxAlarm.isSelected()) {
                                LocalTime horaAlarma = LocalTime.of(Integer.parseInt(hora.getText()), Integer.parseInt(minutos.getText()), 00);

                                if (horaAlarma.equals(LocalTime.parse(relojLabel.getText()))) {
                                    for (int i = 0; i < 50; i++) {
                                        System.out.println("ALARMA");
                                        /*
                                        try {
                                            TimeUnit.SECONDS.sleep(1);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                         */
                                    }
                                }
                            } else {
                            }

                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                            relojLabel.setText(LocalDateTime.now().format(formatter));
                            if (checkBoxAlarm.isSelected()) {
                                LocalTime horaAlarma = LocalTime.of(Integer.parseInt(hora.getText()), Integer.parseInt(minutos.getText()), 00);
                                if (horaAlarma.equals(LocalTime.parse(relojLabel.getText()))) {
                                    for (int i = 0; i < 50; i++) {
                                        System.out.println("ALARMA");
                                        /*
                                        try {
                                            TimeUnit.SECONDS.sleep(1);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                         */
                                    }
                                }

                            } else {
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    System.out.println("El reloj se ha detenido inesperadamente.");
                    e.printStackTrace();
                }
            }
        }

        //COnstructor vacío necesario para que pueda emplearse como un componente y reutilizarse
        public Reloj() {

        }

    }
}