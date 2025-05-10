package com.example.ohjelmistotuotanto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Luokka varauksiin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class VarausTiedot {
    private int varausid;
    private int asiakas_id;
    private int mokki_id;
    private int lasku_id;
    private LocalDate aloituspaiva;
    private LocalDate lopetuspaiva;
    private LocalDateTime luotu;
    private LocalDateTime paivitetty;
    DbConnect dbConnect = new DbConnect();
    private String asiakasNimi;
    private String mokkiOsoite;

    public VarausTiedot(int varausId, LocalDate aloituspaiva, LocalDate lopetuspaiva,
                        LocalDateTime luotu, LocalDateTime paivitetty,
                        int asiakas_id, int mokki_id, int lasku_id) {
        this.varausid = varausId;
        this.aloituspaiva = aloituspaiva;
        this.lopetuspaiva = lopetuspaiva;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
        this.asiakas_id = asiakas_id;
        this.mokki_id = mokki_id;
        this.lasku_id = lasku_id;
        this.asiakasNimi = dbConnect.haeAsiakasNimi(asiakas_id);
        this.mokkiOsoite = dbConnect.haeMokkiOsoite(mokki_id);
    }

    public String getAsiakasNimi() {
        return asiakasNimi;
    }

    public String getMokkiOsoite() {
        return mokkiOsoite;
    }

    public void setAsiakasNimi(String asiakasNimi) {
        this.asiakasNimi = asiakasNimi;
    }

    public void setMokkiOsoite(String mokkiOsoite){
        this.mokkiOsoite = mokkiOsoite;
    }

    public int getVarausId() {
        return varausid;
    }
    public void setVarausId(int varausid) {
        this.varausid = varausid;
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }
    public void setAsiakas_id(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    public int getMokki_id() {
        return mokki_id;
    }
    public void setMokki_id(int mokki_id) {
        this.mokki_id = mokki_id;
    }

    public int getLasku_id() {
        return lasku_id;
    }
    public void setLasku_id(int lasku_id) {
        this.lasku_id = lasku_id;
    }

    public LocalDate getAloituspaiva() {
        return aloituspaiva;
    }
    public void setAloituspaiva(LocalDate aloituspaiva) {
        this.aloituspaiva = aloituspaiva;
    }

    public LocalDate getLopetuspaiva() {
        return lopetuspaiva;
    }
    public void setLopetuspaiva(LocalDate lopetuspaiva) {
        this.lopetuspaiva = lopetuspaiva;
    }

    public LocalDateTime getLuotu() {
        return luotu;
    }
    public void setLuotu(LocalDateTime luotu) {
        this.luotu = luotu;
    }

    public LocalDateTime getPaivitetty() {
        return paivitetty;
    }
    public void setPaivitetty(LocalDateTime paivitetty) {
        this.paivitetty = paivitetty;
    }
}
