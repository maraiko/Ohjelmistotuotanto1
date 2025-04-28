package com.example.ohjelmistotuotanto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static java.nio.file.Files.delete;

public class Main extends Application {
    /**
     * tehdään tablieviewit tarvittaville tiedoille
     */
    private final TableView<VarausTiedot> varausTiedotTableView = new TableView<>();
    private final TableView<MokkiTiedot> mokkiTiedotTableView = new TableView<>();
    private final TableView<LaskuTiedot> laskuTiedotTableView = new TableView<>();
    private final TableView<AsiakasTiedot> asiakasTiedotTableView = new TableView<>();


    /**
     * laitetaan tableviewit muokattaviksi suoraan
     */
    public void initialize() {
        varausTiedotTableView.setEditable(true);
        mokkiTiedotTableView.setEditable(true);
        laskuTiedotTableView.setEditable(true);
        asiakasTiedotTableView.setEditable(true);
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        /**
         * borderpanet eri skeneille
         */
        BorderPane alkuIkkuna = new BorderPane();
        BorderPane varausIkkuna = new BorderPane();
        BorderPane mokkiIkkuna = new BorderPane();
        BorderPane laskuIkkuna = new BorderPane();
        BorderPane asiakasIkkuna = new BorderPane();
        /**
         * skenet
         */
        Scene alkuIkkunaScene = new Scene(alkuIkkuna, 800, 600);
        Scene varausIkkunaScene = new Scene(varausIkkuna, 800, 600);
        Scene mokkiIkkunaScene = new Scene(mokkiIkkuna, 800, 600);
        Scene laskuIkkunaScene = new Scene(laskuIkkuna, 800, 600);
        Scene asiakasIkkunaScene = new Scene(asiakasIkkuna, 800, 600);
        primaryStage.setScene(alkuIkkunaScene);
        primaryStage.setTitle("mökkien hallintajärjestelmä");
        primaryStage.setResizable(false);
        primaryStage.show();
        /**
         * navigaatiopainikkeet
         */
        Button varaukset = new Button("Varaukset");
        Button mokit = new Button("Mökit");
        Button asiakkaat = new Button("Asiakkaat");
        Button laskut = new Button("Laskutukset");
        Button takaisin = new Button("Takaisin");
        Button takaisin2 = new Button("Takaisin");
        Button takaisin3 = new Button("Takaisin");
        Button takaisin4 = new Button("Takaisin");
        /**
         * actionit napeille
         */
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
        /**
         * tehdään HBox alkuikkunan navigaatiopainikkeille
         */
        HBox navigaatioNapitHBox = new HBox();
        navigaatioNapitHBox.setSpacing(10);
        navigaatioNapitHBox.getChildren().addAll(varaukset, mokit, varaukset, laskut);
        /**
         * laskutiedot columniin
         */
        TableColumn<LaskuTiedot, Integer> laskuIdCol = new TableColumn<>("Lasku ID");
        laskuIdCol.setCellValueFactory(new PropertyValueFactory<>("laskuId"));
        TableColumn<LaskuTiedot, Integer> asiakasIdCol = new TableColumn<>("Asiakas ID");
        asiakasIdCol.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        TableColumn<LaskuTiedot, Boolean> tilaCol = new TableColumn<>("Tila");
        tilaCol.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<LaskuTiedot, Float> hintaCol = new TableColumn<>("Hinta");
        hintaCol.setCellValueFactory(new PropertyValueFactory<>("hinta"));
        TableColumn<LaskuTiedot, String> laskutusTapaCol = new TableColumn<>("Laskutustapa");
        laskutusTapaCol.setCellValueFactory(new PropertyValueFactory<>("laskutusTapa"));
        TableColumn<LaskuTiedot, Date> eraPaivaCol = new TableColumn<>("Eräpäivä");
        eraPaivaCol.setCellValueFactory(new PropertyValueFactory<>("eraPaiva"));
        /**
         * asiakastiedot columniin
         */
        TableColumn<AsiakasTiedot, String> sahkopostiCol = new TableColumn<>("Sähköposti");
        sahkopostiCol.setCellValueFactory(new PropertyValueFactory<>("sahkoposti"));
        TableColumn<AsiakasTiedot, String> nimiCol = new TableColumn<>("Nimi");
        nimiCol.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        TableColumn<AsiakasTiedot, String> puhelinnumeroCol = new TableColumn<>("Puhelinnumero");
        puhelinnumeroCol.setCellValueFactory(new PropertyValueFactory<>("puhellinnumero"));
        TableColumn<AsiakasTiedot, String> maaCol = new TableColumn<>("Maa");
        maaCol.setCellValueFactory(new PropertyValueFactory<>("maa"));
        TableColumn<AsiakasTiedot, Boolean> yritysCol = new TableColumn<>("Yritys");
        yritysCol.setCellValueFactory(new PropertyValueFactory<>("yritys"));
        /**
         * mökkitiedot columniin
         */
        TableColumn<MokkiTiedot, String> osoiteCol = new TableColumn<>("Osoite");
        osoiteCol.setCellValueFactory(new PropertyValueFactory<>("osoite"));
        TableColumn<MokkiTiedot, String> tilaCol = new TableColumn<>("Tila");
        tilaCol.setCellValueFactory(new PropertyValueFactory<>("tila"));
        TableColumn<MokkiTiedot, Integer> huoneetCol = new TableColumn<>("Huoneet");
        huoneetCol.setCellValueFactory(new PropertyValueFactory<>("huoneet"));
        TableColumn<MokkiTiedot, Integer> kokoCol = new TableColumn<>("Koko (m²)");
        kokoCol.setCellValueFactory(new PropertyValueFactory<>("koko"));
        TableColumn<MokkiTiedot, Date> luotuCol = new TableColumn<>("Luotu");
        luotuCol.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<MokkiTiedot, Date> paivitettyCol = new TableColumn<>("Päivitetty");
        paivitettyCol.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));
        /**
         * varaustiedot columniin
         */
        TableColumn<VarausTiedot, Integer> varausIdCol = new TableColumn<>("Varaus ID");
        varausIdCol.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        TableColumn<VarausTiedot, Integer> asiakasIdCol = new TableColumn<>("Asiakas ID");
        asiakasIdCol.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        TableColumn<VarausTiedot, Integer> mokkiIdCol = new TableColumn<>("Mökki ID");
        mokkiIdCol.setCellValueFactory(new PropertyValueFactory<>("mokkiId"));
        TableColumn<VarausTiedot, Integer> laskuIdCol = new TableColumn<>("Lasku ID");
        laskuIdCol.setCellValueFactory(new PropertyValueFactory<>("laskuId"));
        TableColumn<VarausTiedot, Date> aloitusPaivaCol = new TableColumn<>("Aloituspäivä");
        aloitusPaivaCol.setCellValueFactory(new PropertyValueFactory<>("aloistuPaiva"));
        TableColumn<VarausTiedot, Date> lopetusPaivaCol = new TableColumn<>("Lopetuspäivä");
        lopetusPaivaCol.setCellValueFactory(new PropertyValueFactory<>("lopetusPaiva"));
        TableColumn<VarausTiedot, Date> luotuCol = new TableColumn<>("Luotu");
        luotuCol.setCellValueFactory(new PropertyValueFactory<>("luotu"));
        TableColumn<VarausTiedot, Date> paivitettyCol = new TableColumn<>("Päivitetty");
        paivitettyCol.setCellValueFactory(new PropertyValueFactory<>("paivitetty"));
        /**
         * lisätään columnit tablevieweihin
         */
        laskuTiedotTableView.getColumns().addAll(laskuIdCol, asiakasIdCol, tilaCol, hintaCol, laskutusTapaCol, eraPaivaCol);
        asiakasTiedotTableView.getColumns().addAll(sahkopostiCol, nimiCol, puhelinnumeroCol, maaCol, yritysCol);
        mokkiTiedotTableView.getColumns().addAll(osoiteCol, tilaCol, huoneetCol, kokoCol, luotuCol, paivitettyCol);
        varausTiedotTableView.getColumns().addAll(varausIdCol, asiakasIdCol, mokkiIdCol, laskuIdCol, aloitusPaivaCol, lopetusPaivaCol, luotuCol, paivitettyCol);
        laskuTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        asiakasTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        varausTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        mokkiTiedotTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /**
         * tehdään tekstikentät eri skeneille
         * ekana laskutustiedoille
         */
        TextField laskuIdTextField = new TextField();
        laskuIdTextField.setPromptText("Lasku ID");
        TextField asiakasIdTextField = new TextField();
        asiakasIdTextField.setPromptText("Asiakas ID");
        TextField tilaTextField = new TextField();
        tilaTextField.setPromptText("Tila");
        TextField hintaTextField = new TextField();
        hintaTextField.setPromptText("Hinta");
        TextField laskutusTapaTextField = new TextField();
        laskutusTapaTextField.setPromptText("Laskutustapa");
        TextField eraPaivaTextField = new TextField();
        eraPaivaTextField.setPromptText("Eräpäivä");
        /**
         * tekstikentät asiakastiedoille
         */
        TextField sahkopostiTextField = new TextField();
        sahkopostiTextField.setPromptText("Sähköposti");
        TextField nimiTextField = new TextField();
        nimiTextField.setPromptText("Nimi");
        TextField puhelinnumeroTextField = new TextField();
        puhelinnumeroTextField.setPromptText("Puhelinnumero");
        TextField maaTextField = new TextField();
        maaTextField.setPromptText("Maa");
        TextField yritysTextField = new TextField();
        yritysTextField.setPromptText("Yritys");
        /**
         * tekstikentät mökkitiedoille
         */
        TextField osoiteTextField = new TextField();
        osoiteTextField.setPromptText("Osoite");
        TextField tilaTextField2 = new TextField();
        tilaTextField2.setPromptText("Tila");
        TextField huoneetTextField = new TextField();
        huoneetTextField.setPromptText("Huoneet");
        TextField kokoTextField = new TextField();
        kokoTextField.setPromptText("Koko");
        TextField luotuTextField = new TextField();
        luotuTextField.setPromptText("Luotu");
        TextField paivitettyTextField = new TextField();
        paivitettyTextField.setPromptText("Paivitetty");
        /**
         * tekstikentät varaustiedoille
         */
        TextField varausTextField = new TextField();
        varausTextField.setPromptText("Varaus");
        TextField asiakasTextField = new TextField();
        asiakasTextField.setPromptText("Asiakas");
        TextField mokkiTextField = new TextField();
        mokkiTextField.setPromptText("Mokki");
        TextField laskuTextField = new TextField();
        laskuTextField.setPromptText("Lasku");
        TextField aloitusPaivaTextField = new TextField();
        aloitusPaivaTextField.setPromptText("AloitusPaiva");
        TextField lopetusPaivaTextField = new TextField();
        lopetusPaivaTextField.setPromptText("lähtöpäivä");
        TextField luotuTextField2 = new TextField();
        luotuTextField2.setPromptText("Luotu");
        TextField paivitettyTextField2 = new TextField();
        paivitettyTextField2.setPromptText("Paivitetty");

        /**
         * Vboxien teko eri tekstikentille
         * ensin varaustiedoille
         */
        VBox varausTietoVBox = new VBox();
        varausTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        varausTietoVBox.setSpacing(10);
        varausTietoVBox.getChildren().addAll(varausTextField, asiakasTextField, mokkiTextField, laskuTextField, aloitusPaivaTextField, lopetusPaivaTextField, luotuTextField2, paivitettyTextField2);
        /**
         *asiakastiedoille VBox
         */
        VBox asiakasTietoVBox = new VBox();
        asiakasTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        asiakasTietoVBox.setSpacing(10);
        asiakasTietoVBox.getChildren().addAll(sahkopostiTextField, nimiTextField, puhelinnumeroTextField, maaTextField, yritysTextField);
        /**
         * mökkitiedoille VBox
         */
        VBox mokkiTietoVBox = new VBox();
        mokkiTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        mokkiTietoVBox.setSpacing(10);
        mokkiTietoVBox.getChildren().addAll(osoiteTextField, tilaTextField2, huoneetTextField, kokoTextField, luotuTextField, paivitettyTextField);
        /**
         * laskutiedoille VBox
         */
        VBox laskuTietoVBox = new VBox();
        laskuTietoVBox.setPadding(new Insets(5, 5, 5, 5));
        laskuTietoVBox.setSpacing(10);
        laskuTietoVBox.getChildren().addAll(varausTextField, asiakasTextField, tilaTextField, hintaTextField, laskutusTapaTextField, eraPaivaTextField);
        /**
         * poistonappien teko
         */
        Button poistaVaraustiedot = luoPoistaNappi(varausTiedotTableView);
        Button poistaMokkiTiedot = luoPoistaNappi(mokkiTiedotTableView);
        Button poistaLaskuTiedot = luoPoistaNappi(laskuTiedotTableView);
        Button poistaAsiakasTiedot = luoPoistaNappi(asiakasTiedotTableView);
        /**
         * talletanappien teko
         */
        Button talletaLasku = new Button("Talleta");{
        talletaLasku.setOnAction(actionEvent ->{
            try {
                VarausTiedot varaus = new VarausTiedot();
                varaus.setVarausId(Integer.parseInt(varausTextField.getText()));
                varaus.setAsiakasId(Integer.parseInt(asiakasTextField.getText()));
                varaus.setMokkiId(Integer.parseInt(mokkiTextField.getText()));
                varaus.setLaskuId(Integer.parseInt(laskuTextField.getText()));
                varaus.setAloistuPaiva(aloitusPaivaTextField.getText());
                varaus.setLopetusPaiva(lopetusPaivaTextField.getText());
                varaus.setLuotu(luotuTextField2.getText());
                varaus.setPaivitetty(paivitettyTextField2.getText());

                varausTextField.clear();
                asiakasTextField.clear();
                mokkiTextField.clear();
                laskuTextField.clear();
                aloitusPaivaTextField.clear();
                lopetusPaivaTextField.clear();
                luotuTextField2.clear();
                paivitettyTextField2.clear();

        } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

        /**
         * lisätään skeneihin napit ja tablet
         * ekana alkuikkuna
         */
        alkuIkkuna.setBottom(navigaatioNapitHBox);
        /**
         * laskuikkuna
         */
        laskuIkkuna.setRight(laskuTiedotTableView);
        laskuIkkuna.setLeft(laskuTietoVBox);
        laskuIkkuna.setBottom(takaisin);
        /**
         * asiakasikkuna
         */
        asiakasIkkuna.setRight(asiakasTiedotTableView);
        asiakasIkkuna.setLeft(asiakasTietoVBox);
        asiakasIkkuna.setBottom(takaisin2);
        /**
         * mökki-ikkuna
         */
        mokkiIkkuna.setRight(mokkiTiedotTableView);
        mokkiIkkuna.setLeft(mokkiTietoVBox);
        mokkiIkkuna.setBottom(takaisin3);
        /**
         * varausikkuna
         */
        varausIkkuna.setRight(varausTiedotTableView);
        varausIkkuna.setLeft(varausTietoVBox);
        varausIkkuna.setBottom(takaisin4);

    }


    /**
     * apufunktio poistonappien tekoon
     * @param tableView
     * @return
     * @param <T>
     */
    private <T> Button luoPoistaNappi(TableView<T> tableView) {
        Button poista = new Button("Poista");
        poista.setOnAction(e -> {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                tableView.getItems().remove(selectedIndex);
            }
        });
        return poista;
    }
}


