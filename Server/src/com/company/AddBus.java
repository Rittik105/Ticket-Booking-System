package com.company;

import java.io.*;
import java.util.Scanner;

public class AddBus {
    String[] attribute;
    String str;
    public AddBus(String input, String[] attribute) {
        str = input.substring(7);
        this.attribute = attribute;
    }

    public void action() throws Exception{
        addToStartPlaces();
        addToEndPlaces();
        addtoVehicleList();
    }

    public void addToStartPlaces() throws IOException {
        File file = new File("startplaces.txt");
        Scanner scanner = new Scanner(file);
        Boolean check=false;
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();
            if (str.equalsIgnoreCase(attribute[1]))
                check = true;
        }
        if (!check) {

            FileWriter fileWriter = new FileWriter("startplaces.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(attribute[1]);
            printWriter.close();
        }

    }

    public void addToEndPlaces() throws IOException {

        File file = new File(attribute[1]+".txt");
        Boolean check = false;
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            String str;
            while (scanner.hasNext()) {
                str = scanner.next();
                if (str.equalsIgnoreCase(attribute[1]))
                    check = true;
            }
        }
        if (!check) {
            FileWriter fileWriter = new FileWriter(attribute[1] + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(attribute[2]);
            printWriter.close();
        }
    }

    public void addtoVehicleList() throws IOException{
        FileWriter fileWriter = new FileWriter("saturday.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        printWriter.println(str);
        printWriter.close();

    }
}
