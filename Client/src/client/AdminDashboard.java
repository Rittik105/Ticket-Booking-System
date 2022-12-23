package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    @FXML
    private TableView<DashBoard> tableView;

    @FXML
    private TableColumn<DashBoard, String> coachNo;

    @FXML
    private TableColumn<DashBoard, LocalDate> date;

    @FXML
    private TableColumn<DashBoard, String> from;

    @FXML
    private TableColumn<DashBoard, String> to;

    @FXML
    private TableColumn<DashBoard, Integer> fare;

    @FXML
    private TableColumn<DashBoard, Integer> seatsSold;

    @FXML
    private TableColumn<DashBoard, Integer> income;

    @FXML
    private Label label;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker tillDate;

    @FXML
    private Button viewAll;

    @FXML
    private Button filter;

    static DashBoard clicked;


    ObservableList<DashBoard> dashBoards = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            String data = Main.dataInputStream.readUTF();
            while (!data.equals("end")) {
                DashBoard dashBoard  = new DashBoard(data);
                dashBoards.add(dashBoard);
                data = Main.dataInputStream.readUTF();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        setTable(dashBoards);
    }

    public void onActionViewAll(){
        setTable(dashBoards);
    }

    public void onActionFilter() {
        ObservableList<DashBoard> list = FXCollections.observableArrayList();
        if(fromDate.getValue()!=null&&tillDate.getValue()!=null){
            for (int i=0; i<dashBoards.size(); i++) {
                if(dashBoards.get(i).date.isAfter(fromDate.getValue())&&dashBoards.get(i).date.isBefore(tillDate.getValue()))
                    list.add(dashBoards.get(i));
            }
            setTable(list);
        }
    }

    public void setTable(ObservableList<DashBoard> list) {
        int total=0;
        for (int i =0; i<list.size(); i++)
            total+=list.get(i).income;
        coachNo.setCellValueFactory(new PropertyValueFactory<>("coachNo"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
        seatsSold.setCellValueFactory(new PropertyValueFactory<>("seatsSold"));
        income.setCellValueFactory(new PropertyValueFactory<>("income"));

        tableView.setItems(list);
        label.setText(String.valueOf(total));
    }

    public void mouseClick() throws IOException{
        clicked = (DashBoard) tableView.getSelectionModel().getSelectedItem();
        Parent pane = FXMLLoader.load(getClass().getResource("adminbuslayout.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}
