package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        ResultSet rs;
        Statement st;
        Connection c;
        Class.forName ("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/adminDashboard", "root", "");
        st=c.createStatement ();
        ServerSocket serverSocket = new ServerSocket(1233);

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
            ClientHandler clientHandler = new ClientHandler(socket, st);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }


    }
}
