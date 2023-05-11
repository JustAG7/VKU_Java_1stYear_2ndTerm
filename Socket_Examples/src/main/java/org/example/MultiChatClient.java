package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MultiChatClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String sentence = scanner.nextLine();
            Socket socket = new Socket(sentence, 7000);
            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(socket.getInputStream()));
            DataOutputStream outToServer =
                    new DataOutputStream(socket.getOutputStream());

            Thread clientPrint = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String clientSentence;
                            clientSentence = inFromServer.readLine();
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
            Thread serverWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            String sentence = scanner.nextLine();
                            outToServer.writeBytes(sentence + '\n');
                            sleep(1000);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}