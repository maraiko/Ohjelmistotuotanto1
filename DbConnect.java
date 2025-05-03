package com.example.ohjelmistotuotanto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

import java.sql.*;

//Tässä luokassa on metodit tietojen hakua varten
public class DbConnect {

    private final String DB_URL = ""; //LISÄÄ TIETOKANNAN URL
    private final String DB_NAME = ""; //LISÄÄ TIETOKANNAN NIMI
    private final String DB_PASSWORD = ""; //LISÄÄ TIETOKANNAN SALASANA

    public ObservableList<AsiakasTiedot> haeAsiakkaat() {
        ObservableList<AsiakasTiedot> asiakasLista = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM asiakas");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String sahkoposti = rs.getString("sahkoposti");
                String nimi = rs.getString("nimi");
                String puhelinnumero = rs.getString("puhelinnumero");
                String maa = rs.getString("maa");
                boolean yritys = rs.getBoolean("yritys");

                AsiakasTiedot asiakas = new AsiakasTiedot(id, sahkoposti, nimi, puhelinnumero, maa, yritys);
                asiakasLista.add(asiakas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asiakasLista;
    }

    public ObservableList<MokkiTiedot> haeMokit() {
        ObservableList<MokkiTiedot> mokkiLista = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM mokki");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String osoite = rs.getString("osoite");
                boolean tila = rs.getBoolean("tila");
                Timestamp paivitettyTs = rs.getTimestamp("paivitetty");
                int koko = rs.getInt("koko");
                int huoneet = rs.getInt("huoneet");
                Timestamp luotuTs = rs.getTimestamp("luotu");

                LocalDateTime paivitetty = paivitettyTs.toLocalDateTime();
                LocalDateTime luotu = luotuTs.toLocalDateTime();

                MokkiTiedot mokki = new MokkiTiedot(id, osoite, tila, huoneet, koko, paivitetty, luotu);
                mokkiLista.add(mokki);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mokkiLista;
    }

    public ObservableList<LaskuTiedot> haeLaskut() {
        ObservableList<LaskuTiedot> laskuLista = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM lasku");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int laskuId = rs.getInt("lasku_id");
                float hinta = rs.getFloat("hinta");
                String laskutustapa = rs.getString("laskutustapa");
                LocalDateTime erapaiva = rs.getTimestamp("erapaiva").toLocalDateTime();
                boolean tila = rs.getBoolean("tila");

                LaskuTiedot lasku = new LaskuTiedot(laskuId, hinta, laskutustapa, erapaiva, tila);
                laskuLista.add(lasku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return laskuLista;
    }

    public ObservableList<VarausTiedot> haeVaraukset() {
        ObservableList<VarausTiedot> varausLista = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM varaus");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int varausId = rs.getInt("varaus_id");
                Timestamp aloituspaivaTs = rs.getTimestamp("aloituspaiva");
                Timestamp lopetuspaivaTs = rs.getTimestamp("lopetuspaiva");
                Timestamp luotuTs = rs.getTimestamp("luotu");
                Timestamp paivitettyTs = rs.getTimestamp("paivitetty");
                int asiakas_id = rs.getInt("asiakas_id");
                int mokki_id = rs.getInt("mokki_id");
                int lasku_id = rs.getInt("lasku_id");

                LocalDateTime aloituspaiva = aloituspaivaTs.toLocalDateTime();
                LocalDateTime lopetuspaiva = lopetuspaivaTs.toLocalDateTime();
                LocalDateTime luotu = luotuTs.toLocalDateTime();
                LocalDateTime paivitetty = paivitettyTs.toLocalDateTime();

                VarausTiedot varaus = new VarausTiedot(varausId, aloituspaiva, lopetuspaiva, luotu, paivitetty, asiakas_id, mokki_id, lasku_id);
                varausLista.add(varaus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return varausLista;
    }
}
