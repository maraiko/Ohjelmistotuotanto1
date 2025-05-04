package com.example.ohjelmistotuotanto;

import java.time.LocalDateTime;

/**
 * Luokka laskuihin liittyville tiedoille ja niiden käsittelylle SQL:ssä
 */
public class LaskuTiedot {
    private int laskuId;
    private float hinta;
    private String laskutustapa;
    private LocalDateTime erapaiva;
    private boolean tila;

    public LaskuTiedot(int laskuId, float hinta, String laskutustapa, LocalDateTime erapaiva, boolean tila) {
        this.laskuId = laskuId;
        this.hinta = hinta;
        this.laskutustapa = laskutustapa;
        this.erapaiva = erapaiva;
        this.tila = tila;
    }

    public int getLaskuId() {
        return laskuId;
    }

    public void setLaskuId(int laskuId) {
        this.laskuId = laskuId;
    }

    public float getHinta() {
        return hinta;
    }

    public void setHinta(float hinta) {
        this.hinta = hinta;
    }

    public void setLaskutustapa(String laskutustapa) {
        this.laskutustapa = laskutustapa;
    }

    public void setErapaiva(LocalDateTime erapaiva) {
        this.erapaiva = erapaiva;
    }

    public String getLaskutustapa() {
        return laskutustapa;
    }

    public LocalDateTime getErapaiva() {
        return erapaiva;
    }

    public boolean getTila() {
        return tila;
    }

    public void setTila(boolean tila) {
        this.tila = tila;
    }
}
