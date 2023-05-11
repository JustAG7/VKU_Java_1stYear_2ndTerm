package com.example.socket_clock;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClock {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Server is started");
        while (true) {
            Socket socket = server.accept();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String time = br.readLine();
                System.out.println("Client sent: " + time);
                if (!socket.isConnected()) {
                    System.out.println("Client disconnected.");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Server is stopped");
    }
}
