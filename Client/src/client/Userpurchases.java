package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Userpurchases implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<PrevPurchase, String> jouneyDate;

    @FXML
    private TableColumn<PrevPurchase, String> agency;

    @FXML
    private TableColumn<PrevPurchase, String> time;

    @FXML
    private TableColumn<PrevPurchase, String> from;

    @FXML
    private TableColumn<PrevPurchase, String> to;

    @FXML
    private TableColumn<PrevPurchase, String> noTicket;

    @FXML
    private TableColumn<PrevPurchase, String> fare;

    ObservableList<PrevPurchase> purchases = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String purchaseData = Main.dataInputStream.readUTF();
            while (!purchaseData.equals("end")) {
                PrevPurchase prevPurchase  = new PrevPurchase(purchaseData);
                purchases.add(prevPurchase);
                purchaseData = Main.dataInputStream.readUTF();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        jouneyDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        agency.setCellValueFactory(new PropertyValueFactory<>("agency"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        noTicket.setCellValueFactory(new PropertyValueFactory<>("noTicket"));
        fare.setCellValueFactory(new PropertyValueFactory<>("fare"));

        tableView.setItems(purchases);

    }
}
