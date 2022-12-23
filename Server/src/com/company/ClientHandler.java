package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.sql.Statement;
import java.util.Scanner;

public class ClientHandler implements Runnable{

    Socket socket;
    Statement statement;

    public ClientHandler(Socket socket, Statement st) {
        this.socket = socket;
        statement = st;
    }

    @Override
    public void run() {
        try {
            action();
        }catch (Exception e) {

        }
    }

    private void action() throws Exception{
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        while(!socket.isClosed()) {
            String input = dataInputStream.readUTF();
            String[] attribute = input.split("#");
            System.out.println(attribute[0]);
            if (attribute[0].equals("login")){

                LogIn logIn = new LogIn(attribute, dataInputStream, dataOutputStream);
                logIn.logIn();
            }
            if (attribute[0].equals("signup")){
                SignUp signUp = new SignUp(socket);
                signUp.action();
            }
            if (attribute[0].equals("setprofile")) {
                SetProfile setProfile = new SetProfile(socket, attribute[1]);
                setProfile.action();
            }
            if (attribute[0].equals("loadcombo1")) {
                File file = new File("startplaces.txt");
                Scanner sc = new Scanner(file);
                String str = "";
                while (sc.hasNext()) {
                    str += sc.next()+"#";
                }
                dataOutputStream.writeUTF(str);
            }
            if (attribute[0].equals("loadcombo2")) {
                File file = new File(attribute[1]+".txt");
                Scanner sc = new Scanner(file);
                String str = "";
                while (sc.hasNext()) {
                    str += sc.next()+"#";
                }
                dataOutputStream.writeUTF(str);
            }
            if (attribute[0].equals("search")) {
                File file = new File("saturday.txt");
                Scanner sc = new Scanner(file);
                sc.useDelimiter("\n");
                String str;
                while (sc.hasNext()){
                    str = sc.next();
                    if (str.contains(attribute[1]+"#"+attribute[2]))
                        dataOutputStream.writeUTF(str);
                }
                dataOutputStream.writeUTF("end");
            }
            if (attribute[0].equals("addbus")) {
                AddBus addBus = new AddBus(input, attribute);
                try {
                    addBus.action();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (attribute[0].equals("seat")||attribute[0].equals("adminseat")) {
                SetSeats seat = new SetSeats(attribute[1], socket);
                seat.sendToClient();
            }
            if (attribute[0].equals("book")) {
                SetSeats seat = new SetSeats(attribute[1], socket);
                seat.writeFile(attribute[2], attribute[3], attribute[4]);

                Dashboard dashboard = new Dashboard(attribute[1], attribute[4], statement);
                dashboard.writeData();
            }
            if (attribute[0].equals("home")) {
                Home home = new Home(attribute[1], socket);
                home.action();
            }
            if (attribute[0].equals("adminhome")) {
                ReadSQL readSQL = new ReadSQL(attribute[1], statement, socket);
                readSQL.readData();
            }
            if (attribute[0].equals("bok")) {
                FileWriter fileWriter = new FileWriter(attribute[1]+".txt", true);
                fileWriter.write(attribute[2]);
                fileWriter.close();
            }
            if (attribute[0].equals("mapload")) {
                File file = new File(attribute[1]);
                Scanner sc = new Scanner(file);
                String str = sc.next();
                dataOutputStream.writeUTF(str);
            }
        }

    }
}
