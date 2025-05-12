package com.example.ohjelmistotuotanto;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

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

    TextField asiakasIdTextField = new TextField();
    TextField mokkiIdTextField = new TextField();
    ComboBox<String> laskutustapaComboBox = new ComboBox<>();
    DatePicker aloituspaivaDatePicker = new DatePicker();
    DatePicker lopetuspaivaDatePicker = new DatePicker();

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
        BorderPane aloitusIkkuna = new BorderPane();

        // Padding ikkunoille
        aloitusIkkuna.setPadding(new Insets(20,20,20,20));
        mokkiIkkuna.setPadding(new Insets(20,20,20,20));
        alkuIkkuna.setPadding(new Insets(20,20,20,20));
        laskuIkkuna.setPadding(new Insets(20,20,20,20));
        varausIkkuna.setPadding(new Insets(20,20,20,20));
        asiakasIkkuna.setPadding(new Insets(20,20,20,20));

        // Taustakuva aloitusikkunalle
        Image image1 = new Image("mokki.jpg");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        Background background2 = new Background(new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
        aloitusIkkuna.setBackground(background2);

        // Alkuruudun labelit ja sun muut
        Label userLabel = new Label("Käyttäjätunnus:");
        userLabel.setFont(Font.font("Montserrat"));
        TextField userField = new TextField();
        Label passLabel = new Label("Salasana:");
        passLabel.setFont(Font.font("Montserrat"));
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        loginButton.setFont(Font.font("Montserrat", 25));
        Label messageLabel = new Label("Väärä käyttäjätunnus\ntai salasana.");
        messageLabel.setFont(Font.font("Montserrat"));
        messageLabel.setVisible(false);

        // Ruudut ja näyttämö
        Scene alkuIkkunaScene = new Scene(alkuIkkuna, 1000, 700);
        Scene varausIkkunaScene = new Scene(varausIkkuna, 1000, 700);
        Scene mokkiIkkunaScene = new Scene(mokkiIkkuna, 1000, 700);
        Scene laskuIkkunaScene = new Scene(laskuIkkuna, 1000, 700);
        Scene asiakasIkkunaScene = new Scene(asiakasIkkuna, 1000, 700);
        Scene aloitusIkkunaScene = new Scene(aloitusIkkuna, 1000, 700);
        primaryStage.setScene(aloitusIkkunaScene);
        primaryStage.setTitle("lomaKylä");
        primaryStage.getIcons().add(new Image("parhainlogoikina.png"));
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
        Button logout = new Button("Kirjaudu ulos");
        Button guestLogin = new Button("Kirjaudu vierailijana");
        Button lisaaLaskuPainike = new Button("Lisää");
        Button lisaaAsiakasPainike = new Button("Lisää");
        Button lisaaMokkiPainike = new Button("Lisää");
        Button lisaaVarausPainike = new Button("Luo varaus ja lasku");
        Button poistaAsiakasPainike = new Button("Poista");
        Button poistaMokkiPainike = new Button("Poista");
        Button poistaLaskuPainike = new Button("Poista lasku ja varaus");
        Button poistaVarausPainike = new Button("Poista varaus ja lasku");
        Label varausTeksti = new Label();
        guestLogin.setFont(Font.font("Book Antiqua",16));

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
        logout.setOnAction(actionEvent -> {
            primaryStage.setScene(aloitusIkkunaScene);
        });
        guestLogin.setOnAction(actionEvent -> {
            primaryStage.setScene(alkuIkkunaScene);
            asiakasTiedotTableView.setEditable(false);
            mokkiTiedotTableView.setEditable(false);
            laskuTiedotTableView.setEditable(false);
            varausTiedotTableView.setEditable(false);
            lisaaAsiakasPainike.setVisible(false);
            lisaaMokkiPainike.setVisible(false);
            lisaaLaskuPainike.setVisible(false);
            lisaaVarausPainike.setVisible(false);
            poistaAsiakasPainike.setVisible(false);
            poistaMokkiPainike.setVisible(false);
            poistaLaskuPainike.setVisible(false);
            poistaVarausPainike.setVisible(false);
            messageLabel.setVisible(false);
            varausTeksti.setVisible(false);
        });

        // Login-napin toiminta
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            // Tarkistus
            if (username.equals(DBCONNECT.getDB_NAME()) && password.equals(DBCONNECT.getDB_PASSWORD())) {
                primaryStage.setScene(alkuIkkunaScene);
                userField.clear();
                passField.clear();
                asiakasTiedotTableView.setEditable(true);
                mokkiTiedotTableView.setEditable(true);
                laskuTiedotTableView.setEditable(true);
                varausTiedotTableView.setEditable(true);
                lisaaAsiakasPainike.setVisible(true);
                lisaaMokkiPainike.setVisible(true);
                lisaaLaskuPainike.setVisible(true);
                lisaaVarausPainike.setVisible(true);
                poistaAsiakasPainike.setVisible(true);
                poistaMokkiPainike.setVisible(true);
                poistaLaskuPainike.setVisible(true);
                poistaVarausPainike.setVisible(true);
                messageLabel.setVisible(false);
                varausTeksti.setVisible(true);
            }
            else {
                messageLabel.setVisible(true);
                userField.clear();
                passField.clear();
            }
        });

        // Aloitusikkunan asettelu
        GridPane aloitusKeski = new GridPane();
        aloitusKeski.setPadding(new Insets(20));
        aloitusKeski.setHgap(20);
        aloitusKeski.setVgap(20);
        aloitusKeski.setAlignment(Pos.CENTER);

        aloitusKeski.add(userLabel, 0, 0);
        aloitusKeski.add(userField, 1, 0);
        aloitusKeski.add(passLabel, 0, 1);
        aloitusKeski.add(passField, 1, 1);
        aloitusKeski.add(loginButton, 0, 2);
        aloitusKeski.add(messageLabel, 1, 2);

        userField.setMaxWidth(175);
        userField.setPrefHeight(32);
        passField.setMaxWidth(175);
        passField.setPrefHeight(32);
        aloitusKeski.setMaxHeight(220);
        aloitusKeski.setMaxWidth(400);
        
        // Muut audiovisuaaliset jutut aloitusikkunaan
        Image logo = new Image("parhainlogoikina.png");
        ImageView naytalogo = new ImageView(logo);
        naytalogo.setFitHeight(300);
        naytalogo.setFitWidth(300);

        aloitusIkkuna.setLeft(naytalogo);
        aloitusIkkuna.setBottom(guestLogin);
        aloitusIkkuna.setCenter(aloitusKeski);

        BorderPane.setAlignment(aloitusKeski, Pos.CENTER);
        BorderPane.setAlignment(naytalogo, Pos.CENTER);
        BorderPane.setAlignment(guestLogin, Pos.CENTER_RIGHT);

        // CSS tyylimäärittelyt
        aloitusKeski.setStyle("-fx-background-color: linear-gradient(to bottom right, #e0f7fa, #f1f8e9);" +
                            "-fx-padding: 0px;" +
                            "-fx-hgap: 5px;" +
                            "-fx-vgap: 5px;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-border-radius: 20px;" +
                            "-fx-border-color: #0099FF;" +
                            "-fx-border-width: 2px;"
        );

        String messageLabelStyle = "-fx-background-color: linear-gradient(to right, #FF4B2B, #FF416C);" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-size: 14px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-padding: 10px 20px;" +
                                    "-fx-background-radius: 20px;" +
                                    "-fx-border-radius: 20px;" +
                                    "-fx-border-color: transparent;";

        String labelStyle = "-fx-background-color: linear-gradient(to right, #FFB6C1, #E6A8D7);" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 15px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-padding: 10px 20px;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-border-radius: 20px;" +
                            "-fx-border-color: transparent;" +
                            "-fx-pref-width: 153px;" +
                            "-fx-alignment: center;";

        String buttonStyle = "-fx-background-color: linear-gradient(to right, #33CCB3, #0099FF);" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 18px;" +
                            "-fx-padding: 10px 20px;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-border-radius: 20px;" +
                            "-fx-border-color: transparent;" +
                            "-fx-cursor: hand;" +
                            "-fx-opacity: 0.9;";

        String buttonStyle2 = "-fx-background-color: linear-gradient(to right, #fbc02d, #f57f17);" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 28px;" +
                                "-fx-padding: 10px 20px;" +
                                "-fx-background-radius: 20px;" +
                                "-fx-border-radius: 20px;" +
                                "-fx-border-color: transparent;" +
                                "-fx-cursor: hand;" +
                                "-fx-opacity: 0.95;";

        String textfieldStyle = "-fx-background-color: #E1F5FE; " +
                                "-fx-background-radius: 10px; " +
                                "-fx-border-color: grey; " +
                                "-fx-border-width: 1px; " +
                                "-fx-border-radius: 10px; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.1), 5, 0, 0, 5); " +
                                "-fx-padding: 5px; " +
                                "-fx-font-family: 'Montserrat'; " +
                                "-fx-font-size: 16px; " +
                                "-fx-font-weight: normal;";

        String checkboxStyle = "-fx-font-size: 18px;" +
                                "-fx-text-fill: #333;";

        String tableviewStyle = "-fx-font-size: 15px;" +
                                "-fx-background-color: white;" +
                                "-fx-table-cell-border-color: transparent;";

        alkuIkkuna.setStyle("-fx-background-color: linear-gradient(to right, #FFAFBD, #C9FFBF);");

        userField.setStyle(textfieldStyle);
        passField.setStyle(textfieldStyle);
        
        messageLabel.setStyle(messageLabelStyle);
        userLabel.setStyle(labelStyle);
        passLabel.setStyle(labelStyle);

        varaukset.setStyle(buttonStyle2);
        mokit.setStyle(buttonStyle2);
        laskut.setStyle(buttonStyle2);
        asiakkaat.setStyle(buttonStyle2);

        logout.setStyle(buttonStyle);
        loginButton.setStyle(buttonStyle);
        guestLogin.setStyle(buttonStyle);
        takaisin.setStyle(buttonStyle);
        takaisin2.setStyle(buttonStyle);
        takaisin3.setStyle(buttonStyle);
        takaisin4.setStyle(buttonStyle);

        lisaaAsiakasPainike.setStyle(buttonStyle);
        poistaAsiakasPainike.setStyle(buttonStyle);
        lisaaMokkiPainike.setStyle(buttonStyle);
        poistaMokkiPainike.setStyle(buttonStyle);
        lisaaVarausPainike.setStyle(buttonStyle);
        poistaVarausPainike.setStyle(buttonStyle);
        lisaaLaskuPainike.setStyle(buttonStyle);
        poistaLaskuPainike.setStyle(buttonStyle);

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
        TableColumn<MokkiTiedot, Boolean> tilaCol1 = new TableColumn<>("Varattu?");
        tilaCol1.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<MokkiTiedot, Integer> huoneetCol = new TableColumn<>("Huoneet");
        huoneetCol.setCellValueFactory(new PropertyValueFactory<>("huoneet"));
        TableColumn<MokkiTiedot, Integer> kokoCol = new TableColumn<>("Koko (m²)");
        kokoCol.setCellValueFactory(new PropertyValueFactory<>("koko"));
        TableColumn<MokkiTiedot, Float> hinta_per_yoCol = new TableColumn<>("Hinta/yö");
        hinta_per_yoCol.setCellValueFactory(new PropertyValueFactory<>("hinta_per_yo"));
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
        TableColumn<VarausTiedot, String> asiakasNimiCol = new TableColumn<>("Nimi");
        asiakasNimiCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAsiakasNimi()));
        TableColumn<VarausTiedot, String> mokkiOsoiteCol = new TableColumn<>("Osoite");
        mokkiOsoiteCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMokkiOsoite()));
        TableColumn<VarausTiedot, Integer> lasku_IdCol = new TableColumn<>("Lasku ID");
        lasku_IdCol.setCellValueFactory(new PropertyValueFactory<>("lasku_id"));
        TableColumn<VarausTiedot, LocalDateTime> aloituspaivaCol = new TableColumn<>("Aloituspäivä");
        aloituspaivaCol.setCellValueFactory(new PropertyValueFactory<>("aloituspaiva"));
        TableColumn<VarausTiedot, LocalDateTime> lopetuspaivaCol = new TableColumn<>("Lopetuspäivä");
        lopetuspaivaCol.setCellValueFactory(new PropertyValueFactory<>("lopetuspaiva"));
        TableColumn<VarausTiedot, LocalDateTime> luotu_Col = new TableColumn<>("Luotu");
        luotu_Col.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<VarausTiedot, LocalDateTime> paivitetty_Col = new TableColumn<>("Päivitetty");
        paivitetty_Col.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));

        // Sarakkeiden lisääminen tauluun
        asiakasTiedotTableView.getColumns().addAll(asiakas_idCol, sahkopostiCol, nimiCol, puhelinnumeroCol, maaCol, yritysCol);
        mokkiTiedotTableView.getColumns().addAll(mokki_IdCol, osoiteCol, tilaCol1, huoneetCol, kokoCol, hinta_per_yoCol, luotuCol, paivitettyCol);
        laskuTiedotTableView.getColumns().addAll(laskuIdCol, tilaCol, hintaCol, laskutusTapaCol, erapaivaCol);
        varausTiedotTableView.getColumns().addAll(varausIdCol, lasku_IdCol, asiakasNimiCol, mokkiOsoiteCol, aloituspaivaCol, lopetuspaivaCol, luotu_Col, paivitetty_Col);

        asiakasTiedotTableView.setStyle(tableviewStyle);
        mokkiTiedotTableView.setStyle(tableviewStyle);
        laskuTiedotTableView.setStyle(tableviewStyle);
        varausTiedotTableView.setStyle(tableviewStyle);

        asiakasTiedotTableView.setItems(DBCONNECT.haeAsiakkaat());
        mokkiTiedotTableView.setItems(DBCONNECT.haeMokit());
        laskuTiedotTableView.setItems(DBCONNECT.haeLaskut());
        varausTiedotTableView.setItems(DBCONNECT.haeVaraukset());

        mokkiTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        laskuTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        asiakasTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        varausTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

        hinta_per_yoCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
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

        hinta_per_yoCol.setOnEditCommit(event -> {
            MokkiTiedot mokki = event.getRowValue();
            mokki.setHinta_per_yo(event.getNewValue());
            paivitaMokki(mokki);
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

        // Tehdään GridPaneeli alkuikkunan navigaatiopainikkeille
        GridPane navigaatioNapitGrid = new GridPane();
        navigaatioNapitGrid.setHgap(10);
        navigaatioNapitGrid.setVgap(10);
        navigaatioNapitGrid.add(varaukset, 0, 0);
        navigaatioNapitGrid.add(mokit, 1, 0);
        navigaatioNapitGrid.add(asiakkaat, 0, 1);
        navigaatioNapitGrid.add(laskut, 1, 1);
        navigaatioNapitGrid.setAlignment(Pos.CENTER);

        varaukset.setPrefSize(300, 100);
        mokit.setPrefSize(300, 100);
        asiakkaat.setPrefSize(300, 100);
        laskut.setPrefSize(300, 100);

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
        sahkopostiTextField.setStyle(textfieldStyle);
        nimiTextField.setStyle(textfieldStyle);
        puhelinnumeroTextField.setStyle(textfieldStyle);
        maaTextField.setStyle(textfieldStyle);
        yritysCheckBox.setStyle(checkboxStyle);
        lisaaAsiakasPainike.setPrefWidth(150);
        poistaAsiakasPainike.setPrefWidth(150);

        // Tekstikentät mökkitietoruudulle
        TextField osoiteTextField = new TextField();
        TextField huoneetTextField = new TextField();
        TextField kokoTextField = new TextField();
        TextField hinta_per_yoTextField = new TextField();
        osoiteTextField.setPromptText("Osoite");
        huoneetTextField.setPromptText("Huoneet");
        hinta_per_yoTextField.setPromptText("Hinta yöltä");
        kokoTextField.setPromptText("Koko");
        osoiteTextField.setStyle(textfieldStyle);
        huoneetTextField.setStyle(textfieldStyle);
        kokoTextField.setStyle(textfieldStyle);
        hinta_per_yoTextField.setStyle(textfieldStyle);
        lisaaMokkiPainike.setPrefWidth(150);
        poistaMokkiPainike.setPrefWidth(150);

        // Tekstikentät varaustietoruudulle
        asiakasIdTextField.setPromptText("Asiakkaan ID");
        mokkiIdTextField.setPromptText("Mökin ID");
        asiakasIdTextField.setStyle(textfieldStyle);
        mokkiIdTextField.setStyle(textfieldStyle);
        laskutustapaComboBox.getItems().addAll(
                "E-lasku",
                "Paperilasku",
                "jotain",
                "jotain 2"
        );
        laskutustapaComboBox.setPrefHeight(asiakasIdTextField.getHeight());
        laskutustapaComboBox.setPrefWidth(253);
        laskutustapaComboBox.setPromptText("Valitse laskutustapa");
        aloituspaivaDatePicker.setPromptText("Aloituspäivä");
        lopetuspaivaDatePicker.setPromptText("Lopetuspäivä");
        aloituspaivaDatePicker.setStyle(textfieldStyle);
        lopetuspaivaDatePicker.setStyle(textfieldStyle);
        laskutustapaComboBox.setStyle(textfieldStyle);
        varausTeksti.setText("Valitse ensiksi asiakkaan ID\n" +
                "ja mökin ID taulukoista.\n" +
                "Sitten, luo varaus käyttäen\n" +
                "ID:eitä.");

        lisaaVarausPainike.setPrefWidth(250);
        poistaVarausPainike.setPrefWidth(250);

        varausTeksti.setMaxWidth(250);
        varausTeksti.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-padding: 10px;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-background-color: #F0F0F0;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-border-color: #CCCCCC;"
        );

        // Painike asiakkaan lisäämiselle
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
        lisaaMokkiPainike.setOnAction(e -> {
            String osoite = osoiteTextField.getText();
            LocalDateTime paivitetty = LocalDateTime.now();
            LocalDateTime luotu = LocalDateTime.now();
            boolean tila = false;
            int koko = Integer.parseInt(kokoTextField.getText());
            int huoneet = Integer.parseInt(huoneetTextField.getText());
            float hinta_per_yo = Float.parseFloat(hinta_per_yoTextField.getText());

            lisaaMokki(osoite, tila, huoneet, koko, hinta_per_yo, paivitetty, luotu);
            mokkiTiedotTableView.setItems(DBCONNECT.haeMokit());

            osoiteTextField.clear();
            kokoTextField.clear();
            huoneetTextField.clear();
            hinta_per_yoTextField.clear();
        });

        // Painike varauksen ja laskun lisäämiselle
        lisaaVarausPainike.setOnAction(event -> {
            luoVarausJaLasku();
            varausTiedotTableView.setItems(DBCONNECT.haeVaraukset());
            laskuTiedotTableView.setItems(DBCONNECT.haeLaskut());

            asiakasIdTextField.clear();
            mokkiIdTextField.clear();
            aloituspaivaDatePicker.setValue(null);
            lopetuspaivaDatePicker.setValue(null);
            laskutustapaComboBox.valueProperty().setValue(null);
        });


        // Painike asiakkaan poistamiselle
        poistaAsiakasPainike.setOnAction(e -> poistaAsiakas());

        // Painike mökin poistamiselle
        poistaMokkiPainike.setOnAction(e -> poistaMokki());

        // Painike laskun ja varauksen poistamiselle
        poistaLaskuPainike.setOnAction(event -> {
            LaskuTiedot selectedLasku = laskuTiedotTableView.getSelectionModel().getSelectedItem();
            if (selectedLasku != null) {
                poistaVarausJaLasku(selectedLasku.getLaskuId());
            }
        });

        // Painike varauksen poistamiselle
        poistaVarausPainike.setOnAction(event -> {
            VarausTiedot selectedVaraus = varausTiedotTableView.getSelectionModel().getSelectedItem();
            if (selectedVaraus != null) {
                poistaVarausJaLasku(selectedVaraus.getVarausId(), selectedVaraus.getLasku_id());
            }
        });

        // VBox varaustietoruudun tekstikentille
        VBox varausTietoVBox = new VBox();
        varausTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        varausTietoVBox.setSpacing(10);
        varausTietoVBox.getChildren().addAll(asiakasIdTextField, mokkiIdTextField, laskutustapaComboBox, aloituspaivaDatePicker, lopetuspaivaDatePicker, lisaaVarausPainike, poistaVarausPainike, varausTeksti);

        // VBox asiakastietoruudun tekstikentille
        VBox asiakasTietoVBox = new VBox();
        asiakasTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        asiakasTietoVBox.setSpacing(10);
        asiakasTietoVBox.getChildren().addAll(sahkopostiTextField, nimiTextField, puhelinnumeroTextField, maaTextField, yritysCheckBox, lisaaAsiakasPainike, poistaAsiakasPainike);

        // VBox mökkitietoruudun tekstikentille
        VBox mokkiTietoVBox = new VBox();
        mokkiTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        mokkiTietoVBox.setSpacing(10);
        mokkiTietoVBox.getChildren().addAll(osoiteTextField, huoneetTextField, kokoTextField, hinta_per_yoTextField, lisaaMokkiPainike, poistaMokkiPainike);

        // Lisätään alkuikkunaruudulle napit ja taulut
        alkuIkkuna.setCenter(navigaatioNapitGrid);
        alkuIkkuna.setBottom(logout);
        BorderPane.setAlignment(logout, Pos.BASELINE_CENTER);

        // Lisätään laskutietoruudulle napit ja taulut
        laskuIkkuna.setCenter(laskuTiedotTableView);
        laskuIkkuna.setLeft(poistaLaskuPainike);
        laskuIkkuna.setBottom(takaisin);
        BorderPane.setAlignment(poistaLaskuPainike, Pos.CENTER);

        // Lisätään asiakastietoruudulle napit ja taulut
        asiakasIkkuna.setCenter(asiakasTiedotTableView);
        asiakasIkkuna.setLeft(asiakasTietoVBox);
        asiakasIkkuna.setBottom(takaisin2);

        // Lisätään mökkitietoruudulle napit ja taulut
        mokkiIkkuna.setCenter(mokkiTiedotTableView);
        mokkiIkkuna.setLeft(mokkiTietoVBox);
        mokkiIkkuna.setBottom(takaisin3);

        // Lisätään varaustietoruudulle napit ja taulut
        varausIkkuna.setCenter(varausTiedotTableView);
        varausIkkuna.setLeft(varausTietoVBox);
        varausIkkuna.setBottom(takaisin4);
    }
    /**
     * Metodi asiakkaan luomiselle
     *
     * @param sahkoposti    asiakkaan sähköposti
     * @param nimi          asiakkaan nimi
     * @param puhelinnumero asiakkaan puhelinnumero
     * @param maa           asiakkaan maa
     * @param yritys        onko asiakas yritys (false = ei, true = on)
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe lisättäessä asiakkaan tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen lisääminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }
    /**
     * Metodi mökin luomiselle
     *
     * @param osoite       mökin osoite
     * @param tila         mökin tila (false = ei varattu, true = varattu)
     * @param huoneet      mökin huoneiden määrät
     * @param koko         mökin koko neliökuutioissa
     * @param hinta_per_yo mökin hinta yöltä
     * @param paivitetty   mökin päivitysaika tietokantaan
     * @param luotu        mökin lisäysaika tietokantaan
     */
    public void lisaaMokki(String osoite, boolean tila, int huoneet, int koko, float hinta_per_yo, LocalDateTime paivitetty, LocalDateTime luotu) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO mokki (osoite, tila, huoneet, koko, hinta_per_yo, paivitetty, luotu) VALUES (?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, osoite);
            ps.setBoolean(2, tila);
            ps.setInt(3, huoneet);
            ps.setInt(4, koko);
            ps.setFloat(5, hinta_per_yo);
            ps.setTimestamp(6, Timestamp.valueOf(paivitetty));
            ps.setTimestamp(7, Timestamp.valueOf(luotu));
            ps.executeUpdate();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe lisättäessä mökkiä");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin lisääminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi, joka luo varauksen
     * @param aloituspaiva    varauksen aloituspäivä
     * @param lopetuspaiva    varauksen lopetuspäivä
     * @param luotu           varauksen lisäysaika tietokantaan
     * @param paivitetty      varauksen päivitysaika tietokantaan
     * @param asiakasId       asiakkaan ID (foreign key)
     * @param mokkiId         mökin ID (foreign key)
     * @param laskuId         laskun ID (foreign key)
     */
    private int luoVaraus(LocalDate aloituspaiva, LocalDate lopetuspaiva, LocalDateTime luotu, LocalDateTime paivitetty, int asiakasId, int mokkiId, int laskuId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO varaus (aloituspaiva, lopetuspaiva, luotu, paivitetty, asiakas_id, mokki_id, lasku_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
                     , Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, java.sql.Date.valueOf(aloituspaiva));
            stmt.setDate(2, java.sql.Date.valueOf(lopetuspaiva));
            stmt.setTimestamp(3, Timestamp.valueOf(luotu));
            stmt.setTimestamp(4, Timestamp.valueOf(paivitetty));
            stmt.setInt(5, asiakasId);
            stmt.setInt(6, mokkiId);
            stmt.setInt(7, laskuId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (PreparedStatement updateMokkiStmt = connection.prepareStatement(
                        "UPDATE mokki SET tila = true WHERE id = ?")) {
                    updateMokkiStmt.setInt(1, mokkiId);
                    updateMokkiStmt.executeUpdate();
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int varausId = generatedKeys.getInt(1);
                        for (MokkiTiedot mokki : mokkiTiedotTableView.getItems()) {
                            if (mokki.getId() == mokkiId) {
                                mokki.setTila(true);
                                mokkiTiedotTableView.refresh();
                                break;
                            }
                        }

                        return varausId;
                    } else {
                        throw new SQLException("Luodun varauksen ID:tä ei saatu.");
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Metodi, joka luo laskun
     */
    private int luoLasku(float hinta, String laskutustapa, LocalDate erapaiva) throws SQLException {

        try (PreparedStatement stmt = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD).prepareStatement(
                "INSERT INTO lasku (hinta, laskutustapa, erapaiva, tila) VALUES (?, ?, ?, false)"
                , Statement.RETURN_GENERATED_KEYS)) {
            stmt.setFloat(1, hinta);
            stmt.setString(2, laskutustapa);
            stmt.setDate(3, java.sql.Date.valueOf(erapaiva));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    /**
     * Metodi, joka hakee tietokannasta mökin hinnan yöltä
     */
    private float haeMokinHintaPerYo(int mokki_id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT hinta_per_yo FROM mokki WHERE id = ?")) {
            ps.setInt(1, mokki_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("hinta_per_yo");
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe hakiessa mökin tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin tietojen etsiminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
        return 0;
    }

    /**
     * Metodi, joka luo varauksen sekä laskun
     */
    private void luoVarausJaLasku() {
        try {
            int asiakas_id = Integer.parseInt(asiakasIdTextField.getText());
            int mokki_id = Integer.parseInt(mokkiIdTextField.getText());

            String laskutustapa = laskutustapaComboBox.getValue();
            LocalDate aloituspaiva = aloituspaivaDatePicker.getValue();
            LocalDate lopetuspaiva = lopetuspaivaDatePicker.getValue();

            LocalDateTime luotu = LocalDateTime.now();
            LocalDateTime paivitetty = LocalDateTime.now();

            float hintaPerYo = haeMokinHintaPerYo(mokki_id);
            long paivatVaraus = ChronoUnit.DAYS.between(aloituspaiva, lopetuspaiva);
            float hinta = hintaPerYo * paivatVaraus;
            LocalDate erapaiva = lopetuspaiva.plusDays(14);
            int lasku_id = luoLasku(hinta, laskutustapa, erapaiva);
            int varausId = luoVaraus(aloituspaiva, lopetuspaiva, luotu, paivitetty, asiakas_id, mokki_id, lasku_id);

            VarausTiedot uusiVaraus = new VarausTiedot(varausId, aloituspaiva, lopetuspaiva, luotu, paivitetty, asiakas_id, mokki_id, lasku_id);
            varausTiedotTableView.getItems().add(uusiVaraus);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe luodessa varausta");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen lisääminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe poistettaessa asiakasta");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan poistaminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe poistettaessa mökkiä");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin poistaminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi, joka poistaa varauksen ja siihen kiinitetyn laskun
     *
     * @param varausId varauksen ID
     * @param laskuId laskun ID
     */
    private void poistaVarausJaLasku(int varausId, int laskuId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            int mokkiId = -1;
            try (PreparedStatement stmt = connection.prepareStatement("SELECT mokki_id FROM varaus WHERE varaus_id = ?")) {
                stmt.setInt(1, varausId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        mokkiId = rs.getInt("mokki_id");
                    }
                }
            }

            if (mokkiId != -1) {
                try (PreparedStatement updateMokkiStmt = connection.prepareStatement(
                        "UPDATE mokki SET tila = false WHERE id = ?")) {
                    updateMokkiStmt.setInt(1, mokkiId);
                    updateMokkiStmt.executeUpdate();
                }

                for (MokkiTiedot mokki : mokkiTiedotTableView.getItems()) {
                    if (mokki.getId() == mokkiId) {
                        mokki.setTila(false);
                        mokkiTiedotTableView.refresh();
                        break;
                    }
                }
            }

            if (laskuId != -1) {
                String updateVarausQuery = "UPDATE varaus SET lasku_id = NULL WHERE varaus_id = ?";
                try (PreparedStatement ps = connection.prepareStatement(updateVarausQuery)) {
                    ps.setInt(1, varausId);
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM varaus WHERE varaus_id = ?")) {
                stmt.setInt(1, varausId);
                stmt.executeUpdate();
            }

            if (laskuId != -1) {
                try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM varaus WHERE lasku_id = ?")) {
                    ps.setInt(1, laskuId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            try (PreparedStatement ps2 = connection.prepareStatement("DELETE FROM lasku WHERE id = ?")) {
                                ps2.setInt(1, laskuId);
                                ps2.executeUpdate();
                            }
                        }
                    }
                }
            }

            laskuTiedotTableView.getItems().removeIf(lasku -> lasku.getLaskuId() == laskuId);
            varausTiedotTableView.getItems().removeIf(varaus -> varaus.getVarausId() == varausId);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe poistaessa laskun tai varauksen tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tai varauksen poistaminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi, joka poistaa varauksen ja siihen kiinitetyn laskun
     *
     * @param laskuId laskun ID
     */
    private void poistaVarausJaLasku(int laskuId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            int varausId = -1;
            try (PreparedStatement stmt = connection.prepareStatement("SELECT varaus_id FROM varaus WHERE lasku_id = ?")) {
                stmt.setInt(1, laskuId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    varausId = rs.getInt("varaus_id");
                }
            }

            if (varausId != -1) {
                try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM varaus WHERE varaus_id = ?")) {
                    stmt.setInt(1, varausId);
                    stmt.executeUpdate();
                }
            }

            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM lasku WHERE id = ?")) {
                stmt.setInt(1, laskuId);
                stmt.executeUpdate();
            }

            int finalVarausId = varausId;
            laskuTiedotTableView.getItems().removeIf(lasku -> lasku.getLaskuId() == laskuId);
            varausTiedotTableView.getItems().removeIf(varaus -> varaus.getVarausId() == finalVarausId);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe poistaessa laskun tai varauksen tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tai varauksen poistaminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi asiakkaan tietojen päivittämiselle
     *
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
            for (AsiakasTiedot item : asiakasTiedotTableView.getItems()) {
                if (item.getId() == asiakas.getId()) {
                    item.setNimi(asiakas.getNimi());
                    item.setSahkoposti(asiakas.getSahkoposti());
                    item.setPuhelinnumero(asiakas.getPuhelinnumero());
                    item.setMaa(asiakas.getMaa());
                    item.setYritys(asiakas.getYritys());
                }
            }

            for (VarausTiedot varaus : varausTiedotTableView.getItems()) {
                if (varaus.getAsiakas_id() == asiakas.getId()) {
                    varaus.setAsiakasNimi(asiakas.getNimi());
                }
            }

            asiakasTiedotTableView.refresh();
            varausTiedotTableView.refresh();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe päivittäessä asiakkaan tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakastietojen päivitys epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi mökin tietojen päivittämiselle
     *
     * @param mokki muokattava mökki
     */
    private void paivitaMokki(MokkiTiedot mokki) {
        mokki.setPaivitetty(LocalDateTime.now());
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE mokki SET osoite = ?, tila = ?, paivitetty = ?, koko = ?, huoneet = ?, luotu = ?, hinta_per_yo = ? WHERE id = ?")) {

            ps.setString(1, mokki.getOsoite());
            ps.setBoolean(2, mokki.getTila());
            ps.setTimestamp(3, Timestamp.valueOf(mokki.getPaivitetty()));
            ps.setInt(4, mokki.getKoko());
            ps.setInt(5, mokki.getHuoneet());
            ps.setTimestamp(6, Timestamp.valueOf(mokki.getLuotu()));
            ps.setFloat(7, mokki.getHinta_per_yo());
            ps.setInt(8, mokki.getId());

            ps.executeUpdate();

            for (MokkiTiedot item : mokkiTiedotTableView.getItems()) {
                if (item.getId() == mokki.getId()) {
                    item.setOsoite(mokki.getOsoite());
                    item.setPaivitetty(mokki.getPaivitetty());
                }
            }

            for (VarausTiedot varaus : varausTiedotTableView.getItems()) {
                if (varaus.getMokki_id() == mokki.getId()) {
                    varaus.setMokkiOsoite(mokki.getOsoite());
                }
            }

            mokkiTiedotTableView.refresh();
            varausTiedotTableView.refresh();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe päivittäessä mökin tietoja");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("mökin päivitys epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Metodi laskun tietojen päivittämiselle
     *
     * @param lasku muokattava lasku
     */
    private void paivitaLasku(LaskuTiedot lasku) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE lasku SET hinta = ?, laskutustapa = ?, erapaiva = ?, tila = ? WHERE id = ?")) {

            ps.setFloat(1, lasku.getHinta());
            ps.setString(2, lasku.getLaskutustapa());
            ps.setTimestamp(3, Timestamp.valueOf(lasku.getErapaiva()));
            ps.setBoolean(4, lasku.getTila());
            ps.setInt(5, lasku.getLaskuId());

            ps.executeUpdate();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Virhe päivittäessä laskua");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun päivittäminen epäonnistui.\nVirhe: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
