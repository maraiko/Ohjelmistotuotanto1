package com.example.ohjelmistotuotanto;

import java.time.LocalDateTime;

/**
 * Luokka mökkeihin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class MokkiTiedot {
    private int id;
    private String osoite;
    private boolean tila;
    private LocalDateTime paivitetty;
    private int koko;
    private int huoneet;
    private LocalDateTime luotu;
    private float hinta_per_yo;

    public MokkiTiedot(int id, String osoite, boolean tila, int huoneet, int koko, float hinta_per_yo, LocalDateTime paivitetty, LocalDateTime luotu) {
        this.id = id;
        this.osoite = osoite;
        this.tila = tila;
        this.huoneet = huoneet;
        this.koko = koko;
        this.hinta_per_yo = hinta_per_yo;
        this.paivitetty = paivitetty;
        this.luotu = luotu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public boolean getTila() {
        return tila;
    }

    public void setTila(boolean tila) {
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

    public float getHinta_per_yo() {
        return hinta_per_yo;
    }

    public void setHinta_per_yo(float hinta_per_yo){
        this.hinta_per_yo = hinta_per_yo;
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


