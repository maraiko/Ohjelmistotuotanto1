package com.example.ohjelmistotuotanto;

public class AsiakasTiedot {
    private String sahkoposti;
    private String nimi;
    private String puhelinnumero;
    private String maa;
    private Boolean yritys;

    public AsiakasTiedot() {
        this.sahkoposti = sahkoposti;
        this.nimi = nimi;
        this.puhelinnumero = puhelinnumero;
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
    public String getPuhelinnumero() {
        return puhelinnumero;
    }
    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = this.puhelinnumero;
    }
    public String getMaa() {
        return maa;
    }
    public void setMaa(String maa) {
        this.maa = maa;
    }
    public Boolean getYritys() {
        return yritys;}
    public void setYritys(Boolean yritys) {
        this.yritys = yritys;
    }
}
