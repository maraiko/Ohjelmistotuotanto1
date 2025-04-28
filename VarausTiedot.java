package com.example.ohjelmistotuotanto;

import java.util.Date;

public class VarausTiedot {
    private int varausId;
    private int asiakasId;
    private int mokkiId;
    private int laskuId;
    private Date aloistuPaiva;
    private Date lopetusPaiva;
    private Date luotu;
    private Date paivitetty;


    public VarausTiedot(int varausId, int asiakasId, int mokkiId, int laskuId, Date aloistuPaiva, Date lopetusPaiva, Date luotu, Date paivitetty) {
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
    public Date getAloistuPaiva() {
        return aloistuPaiva;
    }
    public void setAloistuPaiva(Date aloistuPaiva) {
        this.aloistuPaiva = aloistuPaiva;
    }
    public Date getLopetusPaiva() {
        return lopetusPaiva;
    }
    public void setLopetusPaiva(Date lopetusPaiva) {
        this.lopetusPaiva = lopetusPaiva;
    }
    public Date getLuotu() {
        return luotu;
    }
    public void setLuotu(Date luotu) {
        this.luotu = luotu;
    }
    public Date getPaivitetty() {
        return paivitetty;
    }
    public void setPaivitetty(Date paivitetty) {
        this.paivitetty = paivitetty;
    }



}
