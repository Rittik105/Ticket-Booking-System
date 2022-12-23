package com.company;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Home {
    File file;
    Socket socket;
    DataOutputStream dataOutputStream;
    public Home(String filename, Socket socket) throws IOException{
        file = new File(filename+"prevPurchase.txt");
        this.socket = socket;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void action() throws IOException {
        if(file.length()!=0) {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\n");
            String str;
            while (sc.hasNext()) {
                str = sc.next();
                dataOutputStream.writeUTF(str);
            }
            dataOutputStream.writeUTF("end");
        }
        else
            dataOutputStream.writeUTF("end");
    }
}
