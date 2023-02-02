package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import historyobject.dynasty.Dynasty;
import historyobject.festival.Festival;
import historyobject.king.King;
import historyobject.site.Site;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    @FXML
    private TextField tfLeHoi;
    @FXML
    private TextField tfTrieuDai;
    @FXML
    private TextField tfNhanVat;
    @FXML
    private TextField tfDiaDiem;
    @FXML
    private TextField tfSukien;

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
    // column id used in table Trieu dai
    @FXML
    private TableColumn columnAge;
    @FXML
    private TableColumn columnPeriod;
    @FXML
    private TableColumn columnKings;
    @FXML
    private TableColumn columnDynastyName;
    @FXML
    private TableColumn columnReignTime;
    // column id used in table Le hoi
    @FXML
    private TableColumn columnFestivalName;
    @FXML
    private TableColumn columnLunarCalendarDate;
    @FXML
    private TableColumn columnNote;
    @FXML
    private TableColumn columnTheFirstTime_Year;
    @FXML
    private TableColumn columnLocation;
    @FXML
    private TableColumn columnRelatedCharacter;
    // column id used in table Nhan vat
    @FXML
    private TableColumn columnTen;
    @FXML
    private TableColumn columnMieuHieu;
    @FXML
    private TableColumn columnThuyHieu;
    @FXML
    private TableColumn columnNienHieu;
    @FXML
    private TableColumn columnTenHuy;
    @FXML
    private TableColumn columnTheThu;
    @FXML
    private TableColumn columnTriVi;
    // column id used in table Dia diem
    @FXML
    private TableColumn columnTypeOfMonument;
    @FXML
    private TableColumn columnMonument;
    @FXML
    private TableColumn columnNoteOfMonument;
    @FXML
    private TableColumn columnLocationOfMonument;
    @FXML
    private TableColumn columnDateOfMonument;

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

        // Add Data Dynasty to TableView
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type dynastyType = new TypeToken<List<Dynasty>>() {
        }.getType();
        List<Dynasty> objDynasty;
        try {
            objDynasty = gson.fromJson(new FileReader("src\\main\\resources\\data\\dynasty.json"), dynastyType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<Dynasty> observableDynastyList = FXCollections.observableArrayList(objDynasty);

        columnAge.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("age"));
        columnPeriod.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("period"));
        columnKings.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("kings"));
        columnDynastyName.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("dynastyName"));
        columnReignTime.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("reignTime"));
        tblTrieudai.setItems(observableDynastyList);
        //Initial filtered list <Dynasty>
        FilteredList<Dynasty> filteredDataDynasty = new FilteredList<>(observableDynastyList, b -> true);
        tfTrieuDai.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataDynasty.setPredicate(Dynasty ->
            {
                if (t1.isEmpty() || t1.isBlank() || t1 == null) {
                    return true;
                }
                String searchKeyword = t1.toLowerCase();
                if (Dynasty.getReignTime().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Dynasty.getPeriod().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Dynasty.getKings().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Dynasty.getDynastyName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Dynasty.getAge().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else return false;
            });
        });

        SortedList<Dynasty> sortedDataDynasty = new SortedList<>(filteredDataDynasty);
        sortedDataDynasty.comparatorProperty().bind(tblTrieudai.comparatorProperty());
        tblTrieudai.setItems(sortedDataDynasty);
        // Add Data Festival to TableView
        Type festivalType = new TypeToken<List<Festival>>() {
        }.getType();
        List<Festival> objFestival;
        try {
            objFestival = gson.fromJson(new FileReader("src\\main\\resources\\data\\festival.json"), festivalType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<Festival> observableFestivalList = FXCollections.observableArrayList(objFestival);

        columnFestivalName.setCellValueFactory(new PropertyValueFactory<Festival, String>("festivalName"));
        columnLunarCalendarDate.setCellValueFactory(new PropertyValueFactory<Festival, String>("lunarCalendarDate"));
        columnNote.setCellValueFactory(new PropertyValueFactory<Festival, String>("note"));
        columnTheFirstTime_Year.setCellValueFactory(new PropertyValueFactory<Festival, String>("theFirstTime_Year"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<Festival, String>("location"));
        columnRelatedCharacter.setCellValueFactory(new PropertyValueFactory<Festival, String>("relatedCharacter"));
        tblLehoi.setItems(observableFestivalList);
//        Initial filtered list <festival>
        FilteredList<Festival> filteredDataFestival = new FilteredList<>(observableFestivalList, b -> true);
        tfLeHoi.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataFestival.setPredicate(Festival ->
            {
                if (t1.isEmpty() || t1.isBlank() || t1 == null) {
                    return true;
                }
                String searchKeyword = t1.toLowerCase();
                if (Festival.getFestivalName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Festival.getLocation().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Festival.getNote().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Festival.getLunarCalendarDate().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Festival.getTheFirstTime_Year().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Festival.getRelatedCharacter().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }  else return false;
            });
        });

        SortedList<Festival> sortedDataFestival = new SortedList<>(filteredDataFestival);
        sortedDataFestival.comparatorProperty().bind(tblLehoi.comparatorProperty());
        tblLehoi.setItems(sortedDataFestival);
        // Add Data King to TableView
        Type kingType = new TypeToken<List<King>>() {
        }.getType();
        List<King> objKing;
        try {
            objKing = gson.fromJson(new FileReader("src\\main\\resources\\data\\king.json"), kingType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<King> observableKingList = FXCollections.observableArrayList(objKing);

        columnTen.setCellValueFactory(new PropertyValueFactory<King, String>("ten"));
        columnMieuHieu.setCellValueFactory(new PropertyValueFactory<King, String>("mieuHieu"));
        columnThuyHieu.setCellValueFactory(new PropertyValueFactory<King, String>("thuyHieu"));
        columnNienHieu.setCellValueFactory(new PropertyValueFactory<King, String>("nienHieu"));
        columnTenHuy.setCellValueFactory(new PropertyValueFactory<King, String>("tenHuy"));
        columnTheThu.setCellValueFactory(new PropertyValueFactory<King, String>("theThu"));
        columnTriVi.setCellValueFactory(new PropertyValueFactory<King, String>("triVi"));
        tblNhanvat.setItems(observableKingList);
        //        Initial filtered list <King>
        FilteredList<King> filteredDataKing = new FilteredList<>(observableKingList, b -> true);
        tfNhanVat.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataKing.setPredicate(King ->
            {
                if (t1.isEmpty() || t1.isBlank() || t1 == null) {
                    return true;
                }
                String searchKeyword = t1.toLowerCase();
                if (King.getMieuHieu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (King.getNienHieu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (King.getTen().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (King.getTenHuy().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (King.getTheThu().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (King.getTriVi().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else return false;
            });
        });

        SortedList<King> sortedDataKing = new SortedList<>(filteredDataKing);
        sortedDataKing.comparatorProperty().bind(tblNhanvat.comparatorProperty());
        tblNhanvat.setItems(sortedDataKing);
        // Add Site to TableView
        Type siteType = new TypeToken<List<Site>>() {
        }.getType();
        List<Site> objSite;
        try {
            objSite = gson.fromJson(new FileReader("src\\main\\resources\\data\\site.json"), siteType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<Site> observableSiteList = FXCollections.observableArrayList(objSite);

        columnTypeOfMonument.setCellValueFactory(new PropertyValueFactory<Site, String>("typeOfSite"));
        columnMonument.setCellValueFactory(new PropertyValueFactory<Site, String>("name"));
        columnNoteOfMonument.setCellValueFactory(new PropertyValueFactory<Site, String>("note"));
        columnLocationOfMonument.setCellValueFactory(new PropertyValueFactory<Site, String>("location"));
        columnDateOfMonument.setCellValueFactory(new PropertyValueFactory<Site, String>("recognizedYear"));
        tblDiadiem.setItems(observableSiteList);
        //        Initial filtered list <Site>
        FilteredList<Site> filteredDataSite = new FilteredList<>(observableSiteList, b -> true);
        tfDiaDiem.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataSite.setPredicate(Site ->
            {
                if (t1.isEmpty() || t1.isBlank() || t1 == null) {
                    return true;
                }
                String searchKeyword = t1.toLowerCase();
                if (Site.getLocation().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Site.getNote().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Site.getTypeOfSite().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Site.getRecognizedYear().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Site.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else return false;
            });
        });

        SortedList<Site> sortedDataSite = new SortedList<>(filteredDataSite);
        sortedDataSite.comparatorProperty().bind(tblDiadiem.comparatorProperty());
        tblDiadiem.setItems(sortedDataSite);
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
            alert.setTitle("Thoát");
            alert.setHeaderText("Ban muốn thoát");
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) App.getScene().getWindow();
                stage.close();
            }
        }
    }
}
