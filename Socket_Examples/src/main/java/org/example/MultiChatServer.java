package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MultiChatServer{


    public static void main(String[] args) {
        ServerSocket serverSocket;
        Thread thread1;
        try {
            serverSocket = new ServerSocket(7000);
            thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Socket socket = serverSocket.accept();
                            System.out.println("Client connected");
                            BufferedReader inFromClient =
                                    new BufferedReader(new
                                            InputStreamReader(socket.getInputStream()));
                            DataOutputStream outToClient =
                                    new DataOutputStream(socket.getOutputStream());

                            Thread clientPrint = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            String clientSentence;
                                            clientSentence = inFromClient.readLine();
                                            System.out.println("Client: " + clientSentence);
                                            sleep(1000);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            });
                            clientPrint.start();
                            Thread clientWrite = new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            while (true) {
                                                Scanner scanner = new Scanner(System.in);
                                                String sentence = scanner.nextLine();
                                                outToClient.writeBytes(sentence + '\n');
                                                sleep(1000);
                                            }
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            });
                            clientWrite.start();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            thread1.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}