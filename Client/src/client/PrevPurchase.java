package client;

public class PrevPurchase {
    String date;
    String agency;
    String time;
    String from;
    String to;
    String noTicket;
    String fare;

    public PrevPurchase(String purchaseData) {
        String[] data = purchaseData.split("@");
        date = data[0];
        agency = data[1];
        time = data[2];
        from = data[3];
        to = data[4];
        noTicket = data[5];
        fare = data[6];
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getNoTicket() {
        return noTicket;
    }

    public void setNoTicket(String noTicket) {
        this.noTicket = noTicket;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }
}
