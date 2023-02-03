package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import historyobject.dynasty.Dynasty;
import historyobject.event.Event;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private AnchorPane App;
    Stage stage;
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
    @FXML
    private TableView<Event> tblSukien;
    @FXML
    private TableView<Festival> tblLehoi;
    @FXML
    private TableView<Dynasty> tblTrieudai;
    @FXML
    private TableView<King> tblNhanvat;
    @FXML
    private TableView<Site> tblDiadiem;
    // column id used in table Trieu dai
    @FXML
    private TableColumn<Dynasty, String> columnAge;
    @FXML
    private TableColumn<Dynasty, String> columnPeriod;
    @FXML
    private TableColumn<Dynasty, String> columnKings;
    @FXML
    private TableColumn<Dynasty, String> columnDynastyName;
    @FXML
    private TableColumn<Dynasty, String> columnReignTime;
    @FXML
    private TableColumn<Festival, String> columnFestivalName;
    @FXML
    private TableColumn<Festival, String> columnLunarCalendarDate;
    @FXML
    private TableColumn<Festival, String> columnNote;
    @FXML
    private TableColumn<Festival, String> columnTheFirstTime_Year;
    @FXML
    private TableColumn<Festival, String> columnLocation;
    @FXML
    private TableColumn<Festival, String> columnRelatedCharacter;
    @FXML
    private TableColumn<King, String> columnTen;
    @FXML
    private TableColumn<King, String> columnMieuHieu;
    @FXML
    private TableColumn<King, String> columnThuyHieu;
    @FXML
    private TableColumn<King, String> columnNienHieu;
    @FXML
    private TableColumn<King, String> columnTenHuy;
    @FXML
    private TableColumn<King, String> columnTheThu;
    @FXML
    private TableColumn<King, String> columnTriVi;
    @FXML
    private TableColumn<Site, String> columnTypeOfMonument;
    @FXML
    private TableColumn<Site, String> columnMonument;
    @FXML
    private TableColumn<Site, String> columnNoteOfMonument;
    @FXML
    private TableColumn<Site, String> columnLocationOfMonument;
    @FXML
    private TableColumn<Site, String> columnDateOfMonument;
    @FXML
    private TableColumn<Event, String> columnEventName;
    @FXML
    private TableColumn<Event, String> columnEventTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];

        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        FilteredList<Dynasty> filteredDataDynasty = new FilteredList<>(observableDynastyList, b -> true);
        tfTrieuDai.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataDynasty.setPredicate(Dynasty -> {
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
                } else
                    return false;
            });
        });

        SortedList<Dynasty> sortedDataDynasty = new SortedList<>(filteredDataDynasty);
        sortedDataDynasty.comparatorProperty().bind(tblTrieudai.comparatorProperty());
        tblTrieudai.setItems(sortedDataDynasty);
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
        FilteredList<Festival> filteredDataFestival = new FilteredList<>(observableFestivalList, b -> true);
        tfLeHoi.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataFestival.setPredicate(Festival -> {
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
                } else if (Festival.getRelatedCharacter().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<Festival> sortedDataFestival = new SortedList<>(filteredDataFestival);
        sortedDataFestival.comparatorProperty().bind(tblLehoi.comparatorProperty());
        tblLehoi.setItems(sortedDataFestival);
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
        FilteredList<King> filteredDataKing = new FilteredList<>(observableKingList, b -> true);
        tfNhanVat.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataKing.setPredicate(King -> {
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
                } else
                    return false;
            });
        });

        SortedList<King> sortedDataKing = new SortedList<>(filteredDataKing);
        sortedDataKing.comparatorProperty().bind(tblNhanvat.comparatorProperty());
        tblNhanvat.setItems(sortedDataKing);
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
        FilteredList<Site> filteredDataSite = new FilteredList<>(observableSiteList, b -> true);
        tfDiaDiem.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataSite.setPredicate(Site -> {
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
                } else
                    return false;
            });
        });

        SortedList<Site> sortedDataSite = new SortedList<>(filteredDataSite);
        sortedDataSite.comparatorProperty().bind(tblDiadiem.comparatorProperty());
        tblDiadiem.setItems(sortedDataSite);
        Type eventType = new TypeToken<List<Event>>() {
        }.getType();
        List<Event> objEvent;
        try {
            objEvent = gson.fromJson(new FileReader("src\\main\\resources\\data\\event.json"), eventType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final ObservableList<Event> observableEventList = FXCollections.observableArrayList(objEvent);

        columnEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        columnEventTime.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        tblSukien.setItems(observableEventList);
        FilteredList<Event> filteredDataEvent = new FilteredList<>(observableEventList, b -> true);
        tfSukien.textProperty().addListener((observableValue, s, t1) -> {
            filteredDataEvent.setPredicate(Site -> {
                if (t1.isEmpty() || t1.isBlank() || t1 == null) {
                    return true;
                }
                String searchKeyword = t1.toLowerCase();
                if (Site.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Site.getTime().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<Event> sortedDataEvent = new SortedList<>(filteredDataEvent);
        sortedDataEvent.comparatorProperty().bind(tblSukien.comparatorProperty());
        tblSukien.setItems(sortedDataEvent);
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLeHoi) {
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
