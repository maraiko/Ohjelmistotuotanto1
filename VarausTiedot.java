package com.example.ohjelmistotuotanto;

import java.util.Date;

public class VarausTiedot {
    private int varausId;
    private int asiakasId;
    private int mokkiId;
    private int laskuId;
    private String aloistuPaiva;
    private String lopetusPaiva;
    private String luotu;
    private String paivitetty;


    public VarausTiedot() {
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.mokkiId = mokkiId;
        this.laskuId = laskuId;
        this.aloistuPaiva = aloistuPaiva;
        this.lopetusPaiva = lopetusPaiva;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
    }
    public int getVarausId() {
        return varausId;
    }
    public void setVarausId(int varausId) {
        this.varausId = varausId;
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
    public String getAloistuPaiva() {
        return aloistuPaiva;
    }
    public void setAloistuPaiva(String aloistuPaiva) {
        this.aloistuPaiva = aloistuPaiva;
    }
    public String getLopetusPaiva() {
        return lopetusPaiva;
    }
    public void setLopetusPaiva(String lopetusPaiva) {
        this.lopetusPaiva = lopetusPaiva;
    }
    public String getLuotu() {
        return luotu;
    }
    public void setLuotu(String luotu) {
        this.luotu = luotu;
    }
    public String getPaivitetty() {
        return paivitetty;
    }
    public void setPaivitetty(String paivitetty) {
        this.paivitetty = paivitetty;
    }
}
}
