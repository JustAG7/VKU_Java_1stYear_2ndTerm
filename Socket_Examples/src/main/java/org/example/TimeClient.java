package org.example;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

import static java.lang.Thread.sleep;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Socket socket = new Socket("localhost", 7000);
                        DataInputStream din = new
                                DataInputStream(socket.getInputStream());
                        String time = din.readUTF();
                        System.out.println(time);
                        sleep(1000);
                    } catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        });
        clientThread.start();
    }
}