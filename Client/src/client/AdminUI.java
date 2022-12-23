package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUI implements Initializable {
    @FXML
    Label loggedIn;
    @FXML
    Button addBus;
    @FXML
    Button logOut, dashboard;
    @FXML
    BorderPane borderPane;

    static String adminName;

    public void onActionAddBus(ActionEvent event) throws IOException {

        dashboard.setDisable(false);
        addBus.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("addbus.fxml"));
        borderPane.setCenter(root);
    }

    public void onActionlogout (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminName = Controller.username;
            Main.dataOutputStream.writeUTF("adminhome#"+Controller.username);
            Parent parent = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
            borderPane.setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionDashboard(ActionEvent event) throws IOException {
        dashboard.setDisable(true);
        addBus.setDisable(false);
        Main.dataOutputStream.writeUTF("adminhome#"+Controller.username);
        Parent parent = FXMLLoader.load(getClass().getResource("admindashboard.fxml"));
        borderPane.setCenter(parent);
    }
}
