package com.example.ohjelmistotuotanto;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Luokka graafiselle käyttöliittymälle
 */
public class Main extends Application {
    // Näytetään kaikkien taulujen tiedot käyttöliittymässä
    private final TableView<VarausTiedot> varausTiedotTableView = new TableView<>();
    private final TableView<MokkiTiedot> mokkiTiedotTableView = new TableView<>();
    private final TableView<LaskuTiedot> laskuTiedotTableView = new TableView<>();
    private final TableView<AsiakasTiedot> asiakasTiedotTableView = new TableView<>();

    // Tietokantaan liittyvät tiedot
    private final DbConnect DBCONNECT = new DbConnect();
    private final String DB_URL = DBCONNECT.getDB_URL();
    private final String DB_NAME = DBCONNECT.getDB_NAME();
    private final String DB_PASSWORD = DBCONNECT.getDB_PASSWORD();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // BorderPane-ikkunat jokaiselle ruudulle
        BorderPane alkuIkkuna = new BorderPane();
        BorderPane varausIkkuna = new BorderPane();
        BorderPane mokkiIkkuna = new BorderPane();
        BorderPane laskuIkkuna = new BorderPane();
        BorderPane asiakasIkkuna = new BorderPane();

        // Ruudut ja näyttämö
        Scene alkuIkkunaScene = new Scene(alkuIkkuna, 1000, 700);
        Scene varausIkkunaScene = new Scene(varausIkkuna, 1000, 700);
        Scene mokkiIkkunaScene = new Scene(mokkiIkkuna, 1000, 700);
        Scene laskuIkkunaScene = new Scene(laskuIkkuna, 1000, 700);
        Scene asiakasIkkunaScene = new Scene(asiakasIkkuna, 1000, 700);
        primaryStage.setScene(alkuIkkunaScene);
        primaryStage.setTitle("Mökkien hallintajärjestelmä");
        primaryStage.setResizable(false);
        primaryStage.show();

        // Navigaatiopainikkeet
        Button varaukset = new Button("Varaukset");
        Button mokit = new Button("Mökit");
        Button asiakkaat = new Button("Asiakkaat");
        Button laskut = new Button("Laskutukset");
        Button takaisin = new Button("Takaisin");
        Button takaisin2 = new Button("Takaisin");
        Button takaisin3 = new Button("Takaisin");
        Button takaisin4 = new Button("Takaisin");

        // Nappien toiminnallisuus
        varaukset.setOnAction(actionEvent -> {
            primaryStage.setScene(varausIkkunaScene);
        });
        mokit.setOnAction(actionEvent -> {
            primaryStage.setScene(mokkiIkkunaScene);
        });
        laskut.setOnAction(actionEvent -> {
            primaryStage.setScene(laskuIkkunaScene);
        });
        asiakkaat.setOnAction(actionEvent -> {
            primaryStage.setScene(asiakasIkkunaScene);
        });
        takaisin.setOnAction(actionEvent -> {
            primaryStage.setScene(alkuIkkunaScene);
        });
        takaisin2.setOnAction(actionEvent -> {
            primaryStage.setScene(alkuIkkunaScene);
        });
        takaisin3.setOnAction(actionEvent -> {
            primaryStage.setScene(alkuIkkunaScene);
        });
        takaisin4.setOnAction(actionEvent -> {
            primaryStage.setScene(alkuIkkunaScene);
        });

        // Asiakastiedot tauluun
        TableColumn<AsiakasTiedot, Integer> asiakas_idCol = new TableColumn<>("Asiakas ID");
        asiakas_idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<AsiakasTiedot, String> sahkopostiCol = new TableColumn<>("Sähköposti");
        sahkopostiCol.setCellValueFactory(new PropertyValueFactory<>("sahkoposti"));
        TableColumn<AsiakasTiedot, String> nimiCol = new TableColumn<>("Nimi");
        nimiCol.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        TableColumn<AsiakasTiedot, String> puhelinnumeroCol = new TableColumn<>("Puhelinnumero");
        puhelinnumeroCol.setCellValueFactory(new PropertyValueFactory<>("puhelinnumero"));
        TableColumn<AsiakasTiedot, String> maaCol = new TableColumn<>("Maa");
        maaCol.setCellValueFactory(new PropertyValueFactory<>("maa"));
        TableColumn<AsiakasTiedot, Boolean> yritysCol = new TableColumn<>("Yritys");
        yritysCol.setCellValueFactory(new PropertyValueFactory<>("yritys"));

        // Mökkitiedot tauluun
        TableColumn<MokkiTiedot, Integer> mokki_IdCol = new TableColumn<>("Mökki ID");
        mokki_IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<MokkiTiedot, String> osoiteCol = new TableColumn<>("Osoite");
        osoiteCol.setCellValueFactory(new PropertyValueFactory<>("osoite"));
        TableColumn<MokkiTiedot, Boolean> tilaCol1 = new TableColumn<>("Käytössä?");
        tilaCol1.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<MokkiTiedot, Integer> huoneetCol = new TableColumn<>("Huoneet");
        huoneetCol.setCellValueFactory(new PropertyValueFactory<>("huoneet"));
        TableColumn<MokkiTiedot, Integer> kokoCol = new TableColumn<>("Koko (m²)");
        kokoCol.setCellValueFactory(new PropertyValueFactory<>("koko"));
        TableColumn<MokkiTiedot, Date> luotuCol = new TableColumn<>("Luotu");
        luotuCol.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<MokkiTiedot, Date> paivitettyCol = new TableColumn<>("Päivitetty");
        paivitettyCol.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));

        // Laskutiedot tauluun
        TableColumn<LaskuTiedot, Integer> laskuIdCol = new TableColumn<>("Lasku ID");
        laskuIdCol.setCellValueFactory(new PropertyValueFactory<>("laskuId"));
        TableColumn<LaskuTiedot, Boolean> tilaCol = new TableColumn<>("Maksettu?");
        tilaCol.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<LaskuTiedot, Float> hintaCol = new TableColumn<>("Hinta");
        hintaCol.setCellValueFactory(new PropertyValueFactory<>("hinta"));
        TableColumn<LaskuTiedot, String> laskutusTapaCol = new TableColumn<>("Laskutustapa");
        laskutusTapaCol.setCellValueFactory(new PropertyValueFactory<>("laskutustapa"));
        TableColumn<LaskuTiedot, LocalDateTime> erapaivaCol = new TableColumn<>("Eräpäivä");
        erapaivaCol.setCellValueFactory(new PropertyValueFactory<>("erapaiva"));

        // Varaustiedot tauluun
        TableColumn<VarausTiedot, Integer> varausIdCol = new TableColumn<>("Varaus ID");
        varausIdCol.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        TableColumn<VarausTiedot, String> asiakasIdCol = new TableColumn<>("Asiakas ID");
        asiakasIdCol.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        TableColumn<VarausTiedot, String> mokkiIdCol = new TableColumn<>("Mokki ID");
        mokkiIdCol.setCellValueFactory(new PropertyValueFactory<>("mokkiId"));
        TableColumn<VarausTiedot, Integer> lasku_IdCol = new TableColumn<>("Lasku ID");
        lasku_IdCol.setCellValueFactory(new PropertyValueFactory<>("laskuId"));
        TableColumn<VarausTiedot, Date> aloituspaivaCol = new TableColumn<>("Aloituspäivä");
        aloituspaivaCol.setCellValueFactory(new PropertyValueFactory<>("aloituspaiva"));
        TableColumn<VarausTiedot, Date> lopetuspaivaCol = new TableColumn<>("Lopetuspäivä");
        lopetuspaivaCol.setCellValueFactory(new PropertyValueFactory<>("lopetuspaiva"));
        TableColumn<VarausTiedot, Date> luotu_Col = new TableColumn<>("Luotu");
        luotu_Col.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<VarausTiedot, Date> paivitetty_Col = new TableColumn<>("Päivitetty");
        paivitetty_Col.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));

        // Sarakkeiden lisääminen tauluun
        asiakasTiedotTableView.getColumns().addAll(asiakas_idCol, sahkopostiCol, nimiCol, puhelinnumeroCol, maaCol, yritysCol);
        mokkiTiedotTableView.getColumns().addAll(mokki_IdCol, osoiteCol, tilaCol1, huoneetCol, kokoCol, luotuCol, paivitettyCol);
        laskuTiedotTableView.getColumns().addAll(laskuIdCol, tilaCol, hintaCol, laskutusTapaCol, erapaivaCol);
        varausTiedotTableView.getColumns().addAll(varausIdCol, asiakasIdCol, mokkiIdCol, lasku_IdCol, aloituspaivaCol, lopetuspaivaCol, luotu_Col, paivitetty_Col);

        asiakasTiedotTableView.setItems(DBCONNECT.haeAsiakkaat());
        mokkiTiedotTableView.setItems(DBCONNECT.haeMokit());
        laskuTiedotTableView.setItems(DBCONNECT.haeLaskut());
        varausTiedotTableView.setItems(DBCONNECT.haeVaraukset());

        laskuTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        asiakasTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        varausTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        mokkiTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        asiakasTiedotTableView.setEditable(true);
        mokkiTiedotTableView.setEditable(true);
        laskuTiedotTableView.setEditable(true);
        varausTiedotTableView.setEditable(true);

        // Mökkitietojen muokkaaminen muokattaviksi
        osoiteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        osoiteCol.setOnEditCommit(e -> {
            MokkiTiedot mokki = e.getRowValue();
            mokki.setOsoite(e.getNewValue());
            paivitaMokki(mokki);
        });

        huoneetCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        huoneetCol.setOnEditCommit(e -> {
            MokkiTiedot mokki = e.getRowValue();
            Integer uusiHuoneet = e.getNewValue();
            mokki.setHuoneet(uusiHuoneet);
            paivitaMokki(mokki);
        });

        kokoCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        kokoCol.setOnEditCommit(e -> {
            MokkiTiedot mokki = e.getRowValue();
            Integer uusiKoko = e.getNewValue();
            mokki.setKoko(uusiKoko);
            paivitaMokki(mokki);
        });

        tilaCol1.setCellFactory(tc -> {
            CheckBoxTableCell<MokkiTiedot, Boolean> solu = new CheckBoxTableCell<>();
            solu.setSelectedStateCallback(indeksi -> {
                MokkiTiedot mokki = tilaCol1.getTableView().getItems().get(indeksi);
                SimpleBooleanProperty prop = new SimpleBooleanProperty(mokki.getTila());

                prop.addListener((o, w, valittuna) -> {
                    mokki.setTila(valittuna);
                    paivitaMokki(mokki);
                });

                return prop;
            });
            return solu;
        });

        // Laskutietojen muokkaaminen muokattaviksi
        laskutusTapaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        laskutusTapaCol.setOnEditCommit(e -> {
            LaskuTiedot lasku = e.getRowValue();
            lasku.setLaskutustapa(e.getNewValue());
            paivitaLasku(lasku);
        });

        hintaCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>() {
            public String toString(Float value) {
                return value != null ? String.format("%.2f", value) : "";
            }
            public Float fromString(String text) {
                try {
                    text = text.replace(',', '.');
                    return Float.parseFloat(text);
                } catch (NumberFormatException e) {
                    return 0f;
                }
            }
        }));

        hintaCol.setOnEditCommit(event -> {
            LaskuTiedot lasku = event.getRowValue();
            lasku.setHinta(event.getNewValue());
            paivitaLasku(lasku);
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        erapaivaCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDateTime>() {
            public String toString(LocalDateTime date) {
                return date != null ? date.format(formatter) : "";
            }
            public LocalDateTime fromString(String string) {
                try {
                    return LocalDate.parse(string, formatter).atStartOfDay();
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        }));

        erapaivaCol.setOnEditCommit(event -> {
            LaskuTiedot lasku = event.getRowValue();
            LocalDateTime uusiArvo = event.getNewValue();
            if (uusiArvo != null) {
                lasku.setErapaiva(uusiArvo);
                paivitaLasku(lasku);
            }
        });

        tilaCol.setCellFactory(tc -> {
            CheckBoxTableCell<LaskuTiedot, Boolean> solu = new CheckBoxTableCell<>();
            solu.setSelectedStateCallback(indeksi -> {
                LaskuTiedot lasku = tilaCol.getTableView().getItems().get(indeksi);
                SimpleBooleanProperty prop = new SimpleBooleanProperty(lasku.getTila());

                prop.addListener((o, w, valittuna) -> {
                    lasku.setTila(valittuna);
                    paivitaLasku(lasku);
                });

                return prop;
            });
            return solu;
        });

        // Asiakastietojen muokkaaminen muokattaviksi
        sahkopostiCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sahkopostiCol.setOnEditCommit(e -> {
            AsiakasTiedot asiakas = e.getRowValue();
            asiakas.setSahkoposti(e.getNewValue());
            paivitaAsiakas(asiakas);
        });
        nimiCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nimiCol.setOnEditCommit(e -> {
            AsiakasTiedot asiakas = e.getRowValue();
            asiakas.setNimi(e.getNewValue());
            paivitaAsiakas(asiakas);
        });
        puhelinnumeroCol.setCellFactory(TextFieldTableCell.forTableColumn());
        puhelinnumeroCol.setOnEditCommit(e -> {
            AsiakasTiedot asiakas = e.getRowValue();
            asiakas.setPuhelinnumero(e.getNewValue());
            paivitaAsiakas(asiakas);
        });
        maaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        maaCol.setOnEditCommit(e -> {
            AsiakasTiedot asiakas = e.getRowValue();
            asiakas.setMaa(e.getNewValue());
            paivitaAsiakas(asiakas);
        });
        yritysCol.setCellFactory(tc -> {
            CheckBoxTableCell<AsiakasTiedot, Boolean> solu = new CheckBoxTableCell<>();
            solu.setSelectedStateCallback(indeksi -> {
                AsiakasTiedot asiakas = yritysCol.getTableView().getItems().get(indeksi);
                SimpleBooleanProperty prop = new SimpleBooleanProperty(asiakas.getYritys());

                prop.addListener((o, w, valittuna) -> {
                    asiakas.setYritys(valittuna);
                    paivitaAsiakas(asiakas);
                });

                return prop;
            });
            return solu;
        });

        // Tehdään HBox alkuikkunan navigaatiopainikkeille
        HBox navigaatioNapitHBox = new HBox();
        navigaatioNapitHBox.setSpacing(10);
        navigaatioNapitHBox.getChildren().addAll(varaukset, mokit, asiakkaat, laskut);

        // Tekstikentät laskutustietoruudulle
        TextField hintaTextField = new TextField();
        hintaTextField.setPromptText("Hinta");
        TextField laskutusTapaTextField = new TextField();
        laskutusTapaTextField.setPromptText("Laskutustapa");
        DatePicker erapaivaDatePicker = new DatePicker();
        erapaivaDatePicker.setPromptText("Eräpäivä");
        CheckBox tilaCheckBox = new CheckBox("Maksettu");

        // Tekstikentät asiakastietoruudulle
        CheckBox yritysCheckBox = new CheckBox("Yritys");
        TextField sahkopostiTextField = new TextField();
        TextField nimiTextField = new TextField();
        TextField puhelinnumeroTextField = new TextField();
        TextField maaTextField = new TextField();
        sahkopostiTextField.setPromptText("Sähköposti");
        nimiTextField.setPromptText("Nimi");
        puhelinnumeroTextField.setPromptText("Puhelinnumero");
        maaTextField.setPromptText("Maa");

        // Tekstikentät mökkitietoruudulle
        TextField osoiteTextField = new TextField();
        TextField huoneetTextField = new TextField();
        TextField kokoTextField = new TextField();
        CheckBox mokkiTilaCheckBox = new CheckBox("Käytössä");
        osoiteTextField.setPromptText("Osoite");
        huoneetTextField.setPromptText("Huoneet");
        kokoTextField.setPromptText("Koko");

        // Tekstikentät varaustietoruudulle
        DatePicker aloituspaivaDatePicker = new DatePicker();
        aloituspaivaDatePicker.setPromptText("Aloituspäivä");
        DatePicker lopetuspaivaDatePicker = new DatePicker();
        lopetuspaivaDatePicker.setPromptText("Lopetuspäivä");

        // Painike asiakkaan lisäämiselle
        Button lisaaAsiakasPainike = new Button("Lisää");
        lisaaAsiakasPainike.setOnAction(e -> {
            String sahkoposti = sahkopostiTextField.getText();
            String nimi = nimiTextField.getText();
            String puhelinnumero = puhelinnumeroTextField.getText();
            String maa = maaTextField.getText();
            boolean yritys = yritysCheckBox.isSelected();

            lisaaAsiakas(sahkoposti, nimi, puhelinnumero, maa, yritys);
            asiakasTiedotTableView.setItems(DBCONNECT.haeAsiakkaat());

            sahkopostiTextField.clear();
            nimiTextField.clear();
            puhelinnumeroTextField.clear();
            maaTextField.clear();
            yritysCheckBox.setSelected(false);
        });

        // Painike mökin lisäämiselle
        Button lisaaMokkiPainike = new Button("Lisää");
        lisaaMokkiPainike.setOnAction(e -> {
            String osoite = osoiteTextField.getText();
            LocalDateTime paivitetty = LocalDateTime.now();
            LocalDateTime luotu = LocalDateTime.now();
            boolean tila = mokkiTilaCheckBox.isSelected();
            int koko = Integer.parseInt(kokoTextField.getText());
            int huoneet = Integer.parseInt(huoneetTextField.getText());


            lisaaMokki(osoite, tila, huoneet, koko, paivitetty, luotu);
            mokkiTiedotTableView.setItems(DBCONNECT.haeMokit());

            osoiteTextField.clear();
            mokkiTilaCheckBox.setSelected(false);
            kokoTextField.clear();
            huoneetTextField.clear();
        });

        // Painike laskun lisäämiselle
        Button lisaaLaskuPainike = new Button("Lisää");
        lisaaLaskuPainike.setOnAction(e -> {
            float hinta = Float.parseFloat(hintaTextField.getText());
            String laskutustapa = laskutusTapaTextField.getText();
            LocalDateTime erapaiva = erapaivaDatePicker.getValue().atStartOfDay();
            boolean tila = tilaCheckBox.isSelected();

            lisaaLasku(hinta, laskutustapa, erapaiva, tila);
            laskuTiedotTableView.setItems(DBCONNECT.haeLaskut());

            hintaTextField.clear();
            laskutusTapaTextField.clear();
            erapaivaDatePicker.setValue(null);
            tilaCheckBox.setSelected(false);
        });

        // Painike asiakkaan poistamiselle
        Button poistaAsiakasPainike = new Button("Poista");
        poistaAsiakasPainike.setOnAction(e -> poistaAsiakas());

        // Painike mökin poistamiselle
        Button poistaMokkiPainike = new Button("Poista");
        poistaMokkiPainike.setOnAction(e -> poistaMokki());

        // Painike laskun poistamiselle
        Button poistaLaskuPainike = new Button("Poista");
        poistaLaskuPainike.setOnAction(e -> poistaLasku());

        // VBox varaustietoruudun tekstikentille
        VBox varausTietoVBox = new VBox();
        varausTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        varausTietoVBox.setSpacing(10);
        varausTietoVBox.getChildren().addAll(aloituspaivaDatePicker, lopetuspaivaDatePicker);

        // VBox asiakastietoruudun tekstikentille
        VBox asiakasTietoVBox = new VBox();
        asiakasTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        asiakasTietoVBox.setSpacing(10);
        asiakasTietoVBox.getChildren().addAll(sahkopostiTextField, nimiTextField, puhelinnumeroTextField, maaTextField, yritysCheckBox, lisaaAsiakasPainike, poistaAsiakasPainike);

        // VBox mökkitietoruudun tekstikentille
        VBox mokkiTietoVBox = new VBox();
        mokkiTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        mokkiTietoVBox.setSpacing(10);
        mokkiTietoVBox.getChildren().addAll(osoiteTextField, huoneetTextField, kokoTextField, mokkiTilaCheckBox, lisaaMokkiPainike, poistaMokkiPainike);

        // VBox laskutietoruudun tekstikentille
        VBox laskuTietoVBox = new VBox();
        laskuTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        laskuTietoVBox.setSpacing(10);
        laskuTietoVBox.getChildren().addAll(hintaTextField, laskutusTapaTextField, erapaivaDatePicker, tilaCheckBox, lisaaLaskuPainike, poistaLaskuPainike);

        // Lisätään alkuikkunaruudulle napit ja taulut
        alkuIkkuna.setBottom(navigaatioNapitHBox);

        // Lisätään laskutietoruudulle napit ja taulut
        laskuIkkuna.setRight(laskuTiedotTableView);
        laskuIkkuna.setLeft(laskuTietoVBox);
        laskuIkkuna.setBottom(takaisin);

        // Lisätään asiakastietoruudulle napit ja taulut
        asiakasIkkuna.setRight(asiakasTiedotTableView);
        asiakasIkkuna.setLeft(asiakasTietoVBox);
        asiakasIkkuna.setBottom(takaisin2);

        // Lisätään mökkitietoruudulle napit ja taulut
        mokkiIkkuna.setRight(mokkiTiedotTableView);
        mokkiIkkuna.setLeft(mokkiTietoVBox);
        mokkiIkkuna.setBottom(takaisin3);

        // Lisätään varaustietoruudulle napit ja taulut
        varausIkkuna.setRight(varausTiedotTableView);
        varausIkkuna.setLeft(varausTietoVBox);
        varausIkkuna.setBottom(takaisin4);
    }

    /**
     * Metodi asiakkaan luomiselle
     * @param sahkoposti asiakkaan sähköposti
     * @param nimi asiakkaan nimi
     * @param puhelinnumero asiakkaan puhelinnumero
     * @param maa asiakkaan maa
     * @param yritys onko asiakas yritys (false = ei, true = on)
     */
    public void lisaaAsiakas(String sahkoposti, String nimi, String puhelinnumero, String maa, boolean yritys) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO asiakas (sahkoposti, nimi, puhelinnumero, maa, yritys) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, sahkoposti);
            ps.setString(2, nimi);
            ps.setString(3, puhelinnumero);
            ps.setString(4, maa);
            ps.setBoolean(5, yritys);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi mökin luomiselle
     * @param osoite mökin osoite
     * @param tila mökin tila (false = ei varattu, true = varattu) ????? onhan oikein
     * @param huoneet mökin huoneiden määrät
     * @param koko mökin koko neliökuutioissa
     * @param paivitetty mökin päivitysaika tietokantaan
     * @param luotu mökin lisäysaika tietokantaan
     */
    public void lisaaMokki(String osoite, boolean tila, int huoneet, int koko, LocalDateTime paivitetty, LocalDateTime luotu) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO mokki (osoite, tila, huoneet, koko, paivitetty, luotu) VALUES (?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, osoite);
            ps.setBoolean(2, tila);
            ps.setInt(3, huoneet);
            ps.setInt(4, koko);
            ps.setTimestamp(5, Timestamp.valueOf(paivitetty));
            ps.setTimestamp(6, Timestamp.valueOf(luotu));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi laskun lisäämiselle
     * @param hinta laskun hinta
     * @param laskutustapa laskutustapa
     * @param erapaiva laskun eräpäivä
     * @param tila onko lasku maksettu (false = ei, true = on)
     */
    public void lisaaLasku(float hinta, String laskutustapa, LocalDateTime erapaiva, boolean tila) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO lasku (hinta, laskutustapa, erapaiva, tila) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setFloat(1, hinta);
            ps.setString(2, laskutustapa);
            ps.setTimestamp(3, Timestamp.valueOf(erapaiva));
            ps.setBoolean(4, tila);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi asiakkaan poistamiselle
     */
    private void poistaAsiakas() {
        AsiakasTiedot valittu = asiakasTiedotTableView.getSelectionModel().getSelectedItem();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("DELETE FROM asiakas WHERE sahkoposti = ?")) {
            ps.setString(1, valittu.getSahkoposti());
            ps.executeUpdate();
            asiakasTiedotTableView.getItems().remove(valittu);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi mökin poistamiselle
     */
    private void poistaMokki() {
        MokkiTiedot valittu = mokkiTiedotTableView.getSelectionModel().getSelectedItem();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("DELETE FROM mokki WHERE osoite = ?")) {
            ps.setString(1, valittu.getOsoite());
            ps.executeUpdate();
            mokkiTiedotTableView.getItems().remove(valittu);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi laskun poistamiselle
     */
    private void poistaLasku() {
        LaskuTiedot valittu = laskuTiedotTableView.getSelectionModel().getSelectedItem();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("DELETE FROM lasku WHERE lasku_id = ?")) {
            ps.setInt(1, valittu.getLaskuId());
            ps.executeUpdate();
            laskuTiedotTableView.getItems().remove(valittu);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi asiakkaan tietojen päivittämiselle
     * @param asiakas muokattava asiakas
     */
    private void paivitaAsiakas(AsiakasTiedot asiakas) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("UPDATE asiakas SET sahkoposti = ?, nimi = ?, puhelinnumero = ?, maa = ?, yritys = ? WHERE id = ?")) {

            ps.setString(1, asiakas.getSahkoposti());
            ps.setString(2, asiakas.getNimi());
            ps.setString(3, asiakas.getPuhelinnumero());
            ps.setString(4, asiakas.getMaa());
            ps.setBoolean(5, asiakas.getYritys());
            ps.setInt(6, asiakas.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi mökin tietojen päivittämiselle
     * @param mokki muokattava mökki
     */
    private void paivitaMokki(MokkiTiedot mokki) {
        mokki.setPaivitetty(LocalDateTime.now());
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE mokki SET osoite = ?, tila = ?, paivitetty = ?, koko = ?, huoneet = ?, luotu = ? WHERE id = ?")) {

            ps.setString(1, mokki.getOsoite());
            ps.setBoolean(2, mokki.getTila());
            ps.setTimestamp(3, Timestamp.valueOf(mokki.getPaivitetty()));
            ps.setInt(4, mokki.getKoko());
            ps.setInt(5, mokki.getHuoneet());
            ps.setTimestamp(6, Timestamp.valueOf(mokki.getLuotu()));
            ps.setInt(7, mokki.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi laskun tietojen päivittämiselle
     * @param lasku muokattava lasku
     */
    private void paivitaLasku(LaskuTiedot lasku) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE lasku SET hinta = ?, laskutustapa = ?, erapaiva = ?, tila = ? WHERE lasku_id = ?")) {

            ps.setFloat(1, lasku.getHinta());
            ps.setString(2, lasku.getLaskutustapa());
            ps.setTimestamp(3, Timestamp.valueOf(lasku.getErapaiva()));
            ps.setBoolean(4, lasku.getTila());
            ps.setInt(5, lasku.getLaskuId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
