package com.example.ohjelmistotuotanto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.*;

/**
 * Luokka SQL-tietokannan käsittelyyn ja muihin toimenpiteisiin
 */
public class DbConnect {
    private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/lomakyla";
    private final String DB_NAME = "root";
    private final String DB_PASSWORD = "admin";

    public String getDB_URL() {
        return DB_URL;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

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
        }
        catch (SQLException e) {e.printStackTrace();}
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
                float hinta_per_yo = rs.getFloat("hinta_per_yo");

                LocalDateTime paivitetty = paivitettyTs.toLocalDateTime();
                LocalDateTime luotu = luotuTs.toLocalDateTime();

                MokkiTiedot mokki = new MokkiTiedot(id, osoite, tila, huoneet, koko, hinta_per_yo, paivitetty, luotu);
                mokkiLista.add(mokki);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return mokkiLista;
    }

    public ObservableList<LaskuTiedot> haeLaskut() {
        ObservableList<LaskuTiedot> laskuLista = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM lasku");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int laskuId = rs.getInt("id");
                float hinta = rs.getFloat("hinta");
                String laskutustapa = rs.getString("laskutustapa");
                LocalDateTime erapaiva = rs.getTimestamp("erapaiva").toLocalDateTime();
                boolean tila = rs.getBoolean("tila");

                LaskuTiedot lasku = new LaskuTiedot(laskuId, hinta, laskutustapa, erapaiva, tila);
                laskuLista.add(lasku);
            }
        }
        catch (SQLException e) {e.printStackTrace();}
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
                int asiakasId = rs.getInt("asiakas_id");
                int mokkiId = rs.getInt("mokki_id");
                int laskuId = rs.getInt("lasku_id");

                LocalDate aloituspaiva = aloituspaivaTs.toLocalDateTime().toLocalDate();
                LocalDate lopetuspaiva = lopetuspaivaTs.toLocalDateTime().toLocalDate();

                LocalDateTime luotu = luotuTs.toLocalDateTime();
                LocalDateTime paivitetty = paivitettyTs.toLocalDateTime();

                VarausTiedot varaus = new VarausTiedot(varausId, aloituspaiva, lopetuspaiva, luotu, paivitetty, asiakasId, mokkiId, laskuId);
                varausLista.add(varaus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return varausLista;
    }

    public String haeAsiakasNimi(int asiakasId) {
        String asiakasNimi = "";
        String query = "SELECT nimi FROM asiakas WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, asiakasId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                asiakasNimi = rs.getString("nimi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asiakasNimi;
    }

    public String haeMokkiOsoite(int mokkiId) {
        String mokkiOsoite = "";
        String query = "SELECT osoite FROM mokki WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, mokkiId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mokkiOsoite = rs.getString("osoite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mokkiOsoite;
    }

}
