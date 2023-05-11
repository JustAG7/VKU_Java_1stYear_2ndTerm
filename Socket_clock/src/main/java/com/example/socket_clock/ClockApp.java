package com.example.socket_clock;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClockApp extends Application {
    private ComboBox<String> timezoneComboBox;
    private Label timeLabel;
    private Thread clockThread;
    private Thread sendTimeThread;
    private Socket socket;
    private DataOutputStream dout;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        try{
            socket = new Socket("localhost", 7000);
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        timeLabel = new Label();
        timezoneComboBox = new ComboBox<>();
        timezoneComboBox.getItems().addAll(ZoneId.getAvailableZoneIds());
        timezoneComboBox.getSelectionModel().selectFirst();
        timezoneComboBox.setOnAction(event -> {
            updateTime();
        });

        root.setTop(timezoneComboBox);
        root.setCenter(timeLabel);

        primaryStage.setScene(scene);
        primaryStage.show();

        clockThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    updateTime();
                });
            }
        });

        clockThread.start();
    }

    private void updateTime() {
        if (timeLabel != null) {
            String timezone = timezoneComboBox.getSelectionModel().getSelectedItem();
            ZoneId zone = ZoneId.of(timezone);
            ZonedDateTime zdt = ZonedDateTime.now(zone);
            String time = zdt.toString();
            timeLabel.setText(time);
            try {
                if (socket.isClosed()) {
                    socket = new Socket("localhost", 7000);
                    dout = new DataOutputStream(socket.getOutputStream());
                }
                dout.writeBytes(time + '\n');
            } catch (SocketException e) {
                try {
                    socket = new Socket("localhost", 7000);
                    dout = new DataOutputStream(socket.getOutputStream());
                    dout.writeBytes(time + '\n');
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
