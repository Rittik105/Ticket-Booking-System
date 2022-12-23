package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Dashboard {

    String coachNo;
    LocalDate date;
    String startPlace;
    String destination;
    Integer fare;
    Integer seatsSold;
    Integer revenue;
    String AdminName;

    Statement statement;

    public Dashboard(String coachNo, String info, Statement statement){
        String[] str = coachNo.split("_");
        this.coachNo = str[0];
        this.date = LocalDate.parse(str[1]);
        String[] strings = info.split("@");
        startPlace = strings[3];
        destination = strings[4];
        fare = Integer.parseInt(strings[6]);
        seatsSold = Integer.parseInt(strings[5]);
        revenue = fare*seatsSold;
        AdminName = strings[7];

        this.statement = statement;
    }

    public void writeData() throws SQLException {
        String str1 = "SELECT * FROM dashboard WHERE CoachNo = '"+coachNo+"' AND JourneyDate = '"+date+"'";
        ResultSet resultSet = statement.executeQuery(str1);
        if (!resultSet.next()) {
            String str2 = "INSERT INTO dashboard (CoachNo, JourneyDate, StartPlace, Destination, Fare, SeatsSold, Revenue, Admin) VALUES ('" + coachNo + "', '" + date + "', '" + startPlace + "', '" + destination + "', '" + fare + "', '" + seatsSold + "', '" + revenue + "', '" + AdminName + "')";
            statement.executeUpdate(str2);
        }
        else {
            int num = resultSet.getInt("SeatsSold");
            int sum = num + seatsSold;
            String str3 = "UPDATE dashboard SET SeatsSold = '"+sum+"' WHERE CoachNo = '"+coachNo+"' AND JourneyDate = '"+date+"'";
            statement.executeUpdate(str3);
            String str4 = "SELECT * FROM dashboard WHERE CoachNo = '"+coachNo+"' AND JourneyDate = '"+date+"'";
            ResultSet resultSet1 = statement.executeQuery(str4);
            resultSet1.next();
            int seatsSold1 = resultSet1.getInt("SeatsSold");
            int fare1 = resultSet1.getInt("Fare");
            int revenue1 = fare1 * seatsSold1;
            String str5 = "UPDATE dashboard SET Revenue = '" + revenue1 + "' WHERE CoachNo = '" + coachNo + "' AND JourneyDate = '" + date + "'";
            statement.executeUpdate(str5);
            System.out.println("dds");
        }
    }
}
