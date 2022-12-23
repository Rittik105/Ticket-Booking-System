package client;

import java.time.LocalDate;

public class DashBoard {
    String coachNo;
    LocalDate date;
    String from;
    String to;
    int fare;
    int seatsSold;
    int income;

    public DashBoard(String string) {
        String[] str = string.split("#");
        coachNo = str[0];
        date = LocalDate.parse(str[1]);
        from = str[2];
        to = str[3];
        fare = Integer.parseInt(str[4]);
        seatsSold = Integer.parseInt(str[5]);
        income = Integer.parseInt(str[6]);
    }

    public String getCoachNo() {
        return coachNo;
    }

    public void setCoachNo(String coachNo) {
        this.coachNo = coachNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getSeatsSold() {
        return seatsSold;
    }

    public void setSeatsSold(int seatsSold) {
        this.seatsSold = seatsSold;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
