package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Buslayout implements Initializable {

    Button [] btnarr = new Button[40];
    String[] strings;

    @FXML
    private Button a1;

    @FXML
    private Button a2;

    @FXML
    private Button a3;

    @FXML
    private Button a4;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button c1;

    @FXML
    private Button c2;

    @FXML
    private Button c3;

    @FXML
    private Button c4;

    @FXML
    private Button d1;

    @FXML
    private Button d2;

    @FXML
    private Button d3;

    @FXML
    private Button d4;

    @FXML
    private Button e1;

    @FXML
    private Button e2;

    @FXML
    private Button e3;

    @FXML
    private Button e4;

    @FXML
    private Button f1;

    @FXML
    private Button f2;

    @FXML
    private Button f3;

    @FXML
    private Button f4;

    @FXML
    private Button g1;

    @FXML
    private Button g2;

    @FXML
    private Button g3;

    @FXML
    private Button g4;

    @FXML
    private Button h1;

    @FXML
    private Button h2;

    @FXML
    private Button h3;

    @FXML
    private Button h4;

    @FXML
    private Button i1;

    @FXML
    private Button i2;

    @FXML
    private Button i3;

    @FXML
    private Button i4;

    @FXML
    private Button j1;

    @FXML
    private Button j2;

    @FXML
    private Button j3;

    @FXML
    private Button j4;

    @FXML
    Button button, book, abort;

    String[] booked_seats = new String[40];

    Integer nofTicket = 0;
    public void changeColor(ActionEvent event) {

        int i;
        button = (Button)event.getSource();
        for (i=0; i<40; i++) {
            if (btnarr[i] == button) {
                break;
            }
        }
        Color color = (Color)button.getBackground().getFills().get(0).getFill();
        if (color.equals(Color.RED)) {
            button.setBackground(new Background(new BackgroundFill(
                    Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            strings[i] = "0";
            booked_seats[i] = "0";
            nofTicket--;
        }
        else {
            button.setBackground(new Background(new BackgroundFill(
                    Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            strings[i] = "1";
            booked_seats[i] = "1";
            nofTicket++;
        }
    }

    public void actionBook() throws IOException {
        String str = "book#"+ VehicleList.coach + "_" + UserHome.date+"#";
        for (String s:
             strings) {
            str += s+" ";
        }
        str += "#"+UserHome.userName+"#"+UserHome.date+"@"+VehicleList.clickedVehicle.Agency
                +"@"+VehicleList.clickedVehicle.Time+"@"+UserHome.start+"@"
                +UserHome.destination+"@"+nofTicket+"@"+VehicleList.clickedVehicle.Fare+"@"
                +VehicleList.clickedVehicle.adminName+"@"+"\n";
        Main.dataOutputStream.writeUTF(str);

        str = "";
        for (int i=0; i< booked_seats.length; i++){
            if(booked_seats[i].equals("1")){
                str += btnarr[i].getId()+"@"+UserHome.userName+"@";
            }
        }
        Main.dataOutputStream.writeUTF("bok#"+VehicleList.coach + "@" + UserHome.date+"#"+str);
        Stage stage = (Stage) book.getScene().getWindow();
        stage.close();
    }

    public void actionAbort() {
        Stage stage = (Stage) abort.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                String str = "seat#" + VehicleList.coach + "_" + UserHome.date;
                Main.dataOutputStream.writeUTF(str);
                strings = Main.dataInputStream.readUTF().split("#");

                btnarr[0]=a1;
                btnarr[1]=a2;
                btnarr[2]=a3;
                btnarr[3]=a4;
                btnarr[4]=b1;
                btnarr[5]=b2;
                btnarr[6]=b3;
                btnarr[7]=b4;
                btnarr[8]=c1;
                btnarr[9]=c2;
                btnarr[10]=c3;
                btnarr[11]=c4;
                btnarr[12]=d1;
                btnarr[13]=d2;
                btnarr[14]=d3;
                btnarr[15]=d4;
                btnarr[16]=e1;
                btnarr[17]=e2;
                btnarr[18]=e3;
                btnarr[19]=e4;
                btnarr[20]=f1;
                btnarr[21]=f2;
                btnarr[22]=f3;
                btnarr[23]=f4;
                btnarr[24]=g1;
                btnarr[25]=g2;
                btnarr[26]=g3;
                btnarr[27]=g4;
                btnarr[28]=h1;
                btnarr[29]=h2;
                btnarr[30]=h3;
                btnarr[31]=h4;
                btnarr[32]=i1;
                btnarr[33]=i2;
                btnarr[34]=i3;
                btnarr[35]=i4;
                btnarr[36]=j1;
                btnarr[37]=j2;
                btnarr[38]=j3;
                btnarr[39]=j4;

                for (int i=0; i<40; i++){
                    if(strings[i].equals("1"))
                        btnarr[i].setDisable(true);
                }

                Arrays.fill(booked_seats, "0");

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
