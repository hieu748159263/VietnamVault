package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import historyobject.dynasty.Dynasty;
import historyobject.festival.Festival;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane App;
    Stage stage;
    // @FXML
    // private VBox pnItems = null;
    @FXML
    private Button btnTrangchu;

    @FXML
    private Button btnSuKien;

    @FXML
    private Button btnLeHoi;

    @FXML
    private Button btnTrieuDai;

    @FXML
    private Button btnNhanVat;

    @FXML
    private Button btnDiaDiem;

    @FXML
    private Button btnThoat;

    @FXML
    private Pane pnlLeHoi;

    @FXML
    private Pane pnlSuKien;

    @FXML
    private Pane pnlTrangchu;
    @FXML
    private Pane pnlNhanVat;
    @FXML
    private Pane pnlDiaDiem;
    @FXML
    private Pane pnlTrieuDai;
    private Label eventname;

//    @FXML
//    private ChoiceBox<String> festival_choicebox;
//    @FXML
//    private ChoiceBox<String> dynasty_choicebox;
//    @FXML
//    private ChoiceBox<String> king_choicebox;
//    @FXML
//    private ChoiceBox<String> site_choicebox;
//    private String[] festival = { "Lễ hội truyền thống", "Ngày âm lịch", "Ghi chú", "Lần đầu tổ chức năm", "Vị trí" };
//    private String[] dynasty = { "Tuổi thọ", "Thời kỳ", "Các vị vua", "Tên triều đại", "Năm trị vì" };
//    private String[] king = { "Vua", "Miếu hiệu", "Thụy hiệu", "Niên hiệu", "Tên húy", "Thế thứ", "Trị vì" };
//    private String[] site = { "Loại di tích", "Di tích", "Ghi chú", "Vị trí", "Năm CN" };

    @FXML
    private TableView tblSukien;
    @FXML
    private TableView tblLehoi;
    @FXML
    private TableView tblTrieudai;
    @FXML
    private TableView tblNhanvat;
    @FXML
    private TableView tblDiadiem;
    @FXML
    private TableColumn columnTuoiTho;
    @FXML
    private TableColumn columnThoiKy;
    @FXML
    private TableColumn columnCacViVua;
    @FXML
    private TableColumn columnTenTrieuDai;
    @FXML
    private TableColumn columnNamTriVi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];

//        festival_choicebox.getItems().addAll(festival);
//        dynasty_choicebox.getItems().addAll(dynasty);
//        king_choicebox.getItems().addAll(king);
//        site_choicebox.getItems().addAll(site);

        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));

                // give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                // pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<Dynasty>>() {
        }.getType();
        List<Dynasty> objDynasty;
        try {
            objDynasty = gson.fromJson(new FileReader("src\\main\\resources\\data\\dynasty.json"), type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<Dynasty> observableDynastyList = FXCollections.observableArrayList(objDynasty);

//            final ObservableList<Dynasty> data = FXCollections.observableArrayList(
//                    new Dynasty("10", "20","30", "40", "50"),
//                    new Dynasty("10", "20","30", "40", "50"),
//                    new Dynasty("10", "20","30", "40", "50"),
//                    new Dynasty("10", "20","30", "40", "50")
//            );
        columnTuoiTho.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("age"));
        columnThoiKy.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("period"));
        columnCacViVua.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("kings"));
        columnTenTrieuDai.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("dynastyName"));
        columnNamTriVi.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("reignTime"));
        tblTrieudai.setItems(observableDynastyList);

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLeHoi) {
            // pnlLeHoi.setStyle("-fx-background-color : #FFF");
            pnlLeHoi.setVisible(true);

            pnlSuKien.setVisible(false);
            pnlNhanVat.setVisible(false);
            pnlDiaDiem.setVisible(false);
            pnlTrieuDai.setVisible(false);

            pnlLeHoi.toFront();
        }
        if (actionEvent.getSource() == btnTrieuDai) {

            pnlTrieuDai.setVisible(true);

            pnlDiaDiem.setVisible(false);
            pnlLeHoi.setVisible(false);
            pnlSuKien.setVisible(false);
            pnlNhanVat.setVisible(false);

            pnlTrieuDai.toFront();
        }
        if (actionEvent.getSource() == btnTrangchu) {

            pnlTrangchu.setVisible(true);

            pnlDiaDiem.setVisible(false);
            pnlTrieuDai.setVisible(false);
            pnlLeHoi.setVisible(false);
            pnlNhanVat.setVisible(false);

            pnlTrangchu.toFront();

        }
        if (actionEvent.getSource() == btnSuKien) {

            pnlSuKien.setVisible(true);

            pnlDiaDiem.setVisible(false);
            pnlTrieuDai.setVisible(false);
            pnlLeHoi.setVisible(false);
            pnlNhanVat.setVisible(false);

            pnlSuKien.toFront();

        }
        if (actionEvent.getSource() == btnNhanVat) {

            pnlNhanVat.setVisible(true);

            pnlDiaDiem.setVisible(false);
            pnlLeHoi.setVisible(false);
            pnlSuKien.setVisible(false);
            pnlTrieuDai.setVisible(false);

            pnlNhanVat.toFront();

        }
        if (actionEvent.getSource() == btnDiaDiem) {

            pnlDiaDiem.setVisible(true);

            pnlTrieuDai.setVisible(false);
            pnlSuKien.setVisible(false);
            pnlLeHoi.setVisible(false);
            pnlNhanVat.setVisible(false);

            pnlDiaDiem.toFront();

        }
        if (actionEvent.getSource() == btnThoat) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thoat");
            alert.setHeaderText("Ban muốn thoát");
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) App.getScene().getWindow();
                stage.close();
            }
        }
    }
}
