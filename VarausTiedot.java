package com.example.ohjelmistotuotanto;

import java.time.LocalDateTime;

public class VarausTiedot {
    private int varausId;
    private LocalDateTime aloituspaiva;
    private LocalDateTime lopetuspaiva;
    private LocalDateTime luotu;
    private LocalDateTime paivitetty;
    private int asiakas_id;
    private int mokki_id;
    private int lasku_id;

    public VarausTiedot(int varaus_id, LocalDateTime aloituspaiva, LocalDateTime lopetuspaiva, LocalDateTime luotu, LocalDateTime paivitetty, int asiakas_id, int mokki_Id, int lasku_Id) {
        this.varausId = varaus_id;
        this.aloituspaiva = aloituspaiva;
        this.lopetuspaiva = lopetuspaiva;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
        this.asiakas_id = asiakas_id;
        this.mokki_id = mokki_id;
        this.lasku_id = lasku_id;
    }

    public int getVarausId() {
        return varausId;
    }
    public void setVarausId(int varausId) {
        this.varausId = varausId;
    }

    public LocalDateTime getAloituspaiva() {
        return aloituspaiva;
    }
    public void setAloituspaiva(LocalDateTime aloituspaiva) {
        this.aloituspaiva = aloituspaiva;
    }

    public LocalDateTime getLopetuspaiva() {
        return lopetuspaiva;
    }
    public void setLopetuspaiva(LocalDateTime lopetuspaiva) {
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

    public int getAsiakasId() {
        return asiakas_id;
    }
    public void setAsiakasId(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    public int getMokkiId() {
        return mokki_id;
    }
    public void setMokkiId(int mokki_id) {
        this.mokki_id = mokki_id;
    }

    public int getLaskuId() {
        return lasku_id;
    }
    public void setLaskuId(int lasku_id) {
        this.lasku_id = lasku_id;
    }
}
