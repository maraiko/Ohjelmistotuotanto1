package com.example.ohjelmistotuotanto;

import java.util.Date;

public class MokkiTiedot {
    private String osoite;
    private String tila;
    private int huoneet;
    private int koko;
    private Date luotu;
    private Date paivitetty;


    public MokkiTiedot(String osoite, String tila, int huoneet, int koko, Date luotu, Date paivitetty) {
        this.osoite = osoite;
        this.tila = tila;
        this.huoneet = huoneet;
        this.koko = koko;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
    }
    public String getOsoite() {
        return osoite;
    }
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    public String getTila() {
        return tila;
    }
    public void setTila(String tila) {
        this.tila = tila;
    }
    public int getHuoneet() {
        return huoneet;
    }
    public void setHuoneet(int huoneet) {
        this.huoneet = huoneet;
    }
    public int getKoko() {
        return koko;
    }
    public void setKoko(int koko) {
        this.koko = koko;
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
