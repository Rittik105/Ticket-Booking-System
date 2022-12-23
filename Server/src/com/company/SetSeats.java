package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class SetSeats {
    File file;
    Integer[] book = new Integer[40];
    Socket socket;
    DataOutputStream dataOutputStream;


    public SetSeats(String filename, Socket socket) throws IOException {
        Arrays.fill(book, 0);
        file = new File(filename+".txt");
        if(!file.exists()) {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(filename + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for (int i:
                 book) {
                printWriter.println(i);
            }
            printWriter.close();
        }
        this.socket = socket;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void sendToClient() throws IOException{
        Scanner sc = new Scanner(file);
        String str = "";
        while (sc.hasNext()) {
            str += sc.next()+"#";
        }
        dataOutputStream.writeUTF(str);
    }

    public void writeFile(String seatCode, String userName, String bookingInfo) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(seatCode);
        fileWriter.close();

        file = new File(userName+"prevPurchase.txt");
        fileWriter = new FileWriter(file, true);
        fileWriter.write(bookingInfo);
        fileWriter.close();
    }
}
