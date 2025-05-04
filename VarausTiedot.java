package com.example.ohjelmistotuotanto;

import java.time.LocalDateTime;

/**
 * Luokka varauksiin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class VarausTiedot {
    private int varausId;
    private LocalDateTime aloituspaiva;
    private LocalDateTime lopetuspaiva;
    private LocalDateTime luotu;
    private LocalDateTime paivitetty;
    private int asiakasId;
    private int mokkiId;
    private int laskuId;

    public VarausTiedot(int varausId, LocalDateTime aloituspaiva, LocalDateTime lopetuspaiva, LocalDateTime luotu, LocalDateTime paivitetty, int asiakasId, int mokkiId, int laskuId) {
        this.varausId = varausId;
        this.aloituspaiva = aloituspaiva;
        this.lopetuspaiva = lopetuspaiva;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
        this.asiakasId = asiakasId;
        this.mokkiId = mokkiId;
        this.laskuId = laskuId;
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
        return asiakasId;
    }

    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }

    public int getMokkiId() {
        return mokkiId;
    }

    public void setMokkiId(int mokkiId) {
        this.mokkiId = mokkiId;
    }

    public int getLaskuId() {
        return laskuId;
    }
    public void setLaskuId(int laskuId) {
        this.laskuId = laskuId;
    }
}
