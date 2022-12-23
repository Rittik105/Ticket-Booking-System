package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminBuslayout implements Initializable {
    Button[] btnarr = new Button[40];
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
    Button button, abort;

    @FXML
    Label label;

    HashMap<String, String> map = new HashMap<>();

    public void changeColor(ActionEvent event) throws IOException {
        String str = "mapload#"+AdminDashboard.clicked.coachNo+"@"+AdminDashboard.clicked.date+".txt";
        Main.dataOutputStream.writeUTF(str);
        String[] sttr = Main.dataInputStream.readUTF().split("@");
        for (int i=0; i<sttr.length-1; i = i+2) {
            map.put(sttr[i], sttr[i+1]);
        }
        for(int i=0; i<40; i++) {
            if(((Button)event.getSource()).getId().equals(btnarr[i].getId())) {
                label.setText(map.get(btnarr[i].getId()));
                break;
            }
        }
    }


    public void actionAbort() {
        Stage stage = (Stage) abort.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String str = "adminseat#" + AdminDashboard.clicked.coachNo+"_"+AdminDashboard.clicked.date;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

