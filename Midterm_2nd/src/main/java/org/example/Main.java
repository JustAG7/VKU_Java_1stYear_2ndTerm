package org.example;

public class Main {
    public static DBConnect con ;

    public static void main(String[] args)  throws Exception{
        con = new DBConnect();
        con.addname("John");


    }
}