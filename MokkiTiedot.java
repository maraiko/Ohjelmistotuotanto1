package com.example.ohjelmistotuotanto;
public class MokkiTiedot {
    private String osoite;
    private Boolean tila;
    private int huoneet;
    private int koko;
    private String luotu;
    private String paivitetty;


    public MokkiTiedot(String osoite, Boolean tila, int huoneet, int koko, String luotu, String paivitetty) {
        this.osoite = osoite;
        this.tila = tila;
        this.huoneet = huoneet;
        this.koko = koko;
        this.luotu = luotu;
        this.paivitetty = paivitetty;
    }

    public MokkiTiedot() {

    }

    public String getOsoite() {
        return osoite;
    }
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    public Boolean getTila() {
        return tila;
    }
    public void setTila(Boolean tila) {
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

