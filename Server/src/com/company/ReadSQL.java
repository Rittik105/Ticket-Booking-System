package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadSQL {

    String adminName;
    Statement statement;
    Socket socket;
    DataOutputStream dataOutputStream;

    public ReadSQL (String name, Statement statement, Socket socket) {
        adminName = name;
        this.statement = statement;
        this.socket = socket;
        try{
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){

        }
    }

    public void readData() throws SQLException, IOException {
        String string;
        ResultSet resultSet;
        String str = "SELECT * FROM dashboard WHERE Admin = '"+adminName+"'";
        resultSet = statement.executeQuery(str);
        while (resultSet.next()) {
            string = resultSet.getString("CoachNo")+"#";
            string += resultSet.getString("JourneyDate")+"#";
            string += resultSet.getString("StartPlace")+"#";
            string += resultSet.getString("Destination")+"#";
            string += resultSet.getInt("Fare")+"#";
            string += resultSet.getInt("SeatsSold")+"#";
            string += resultSet.getInt("Revenue")+"#";
            dataOutputStream.writeUTF(string);
        }
        dataOutputStream.writeUTF("end");
    }
}
