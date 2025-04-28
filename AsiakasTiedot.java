package com.example.ohjelmistotuotanto;

public class AsiakasTiedot {
    private String sahkoposti;
    private String nimi;
    private String puhellinnumero;
    private String maa;
    private Boolean yritys;

    public AsiakasTiedot(String sahkoposti, String nimi, String puhellinnumero, String maa, Boolean yritys) {
        this.sahkoposti = sahkoposti;
        this.nimi = nimi;
        this.puhellinnumero = puhellinnumero;
        this.maa = maa;
        this.yritys = yritys;
    }
    public String getSahkoposti() {
        return sahkoposti;
    }
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }
    public String getNimi() {
        return nimi;
    }
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public String getPuhellinnumero() {
        return puhellinnumero;
    }
    public void setPuhellinnumero(String puhellinnumero) {
        this.puhellinnumero = puhellinnumero;
    }
    public String getMaa() {
        return maa;
    }
    public void setMaa(String maa) {
        this.maa = maa;
    }
    public Boolean getYritys() {
        return yritys;
    }
    public void setYritys(Boolean yritys) {
        this.yritys = yritys;
    }
}
