package com.example.ohjelmistotuotanto;

import java.util.Date;

public class LaskuTiedot {
    private int laskuId;
    private int asiakasId;
    private boolean tila;
    private float hinta;
    private String laskutusTapa;
    private Date eraPaiva;

    public LaskuTiedot(int laskuId, int asiakasId, boolean tila, float hinta, String laskutusTapa, Date eraPaiva) {
        this.laskuId = laskuId;
        this.asiakasId = asiakasId;
        this.tila = tila;
        this.hinta = hinta;
        this.laskutusTapa = laskutusTapa;
        this.eraPaiva = eraPaiva;
    }
    public int getLaskuId() {
        return laskuId;
    }
    public void setLaskuId(int laskuId) {
        this.laskuId = laskuId;
    }
    public int getAsiakasId() {
        return asiakasId;
    }
    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }
    public boolean isTila() {
        return tila;
    }
    public void setTila(boolean tila) {
        this.tila = tila;
    }
    public float getHinta() {
        return hinta;
    }
    public void setHinta(float hinta) {
        this.hinta = hinta;
    }
    public String getLaskutusTapa() {
        return laskutusTapa;
    }
    public void setLaskutusTapa(String laskutusTapa) {
        this.laskutusTapa = laskutusTapa;
    }
    public Date getEraPaiva() {
        return eraPaiva;
    }
    public void setEraPaiva(Date eraPaiva) {
        this.eraPaiva = eraPaiva;
    }
}
