package br.com.tavinho.searchcep.models;

import com.google.gson.annotations.SerializedName;

public class Address {

    private String cep;
    private String uf;
    @SerializedName("localidade")
    private String city;
    @SerializedName("logradouro")
    private String avenue;

    public Address() { }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvenue() {
        return this.avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    @Override
    public String toString() {
        return "CEP: " + this.cep + "\n" +
                "ESTADO: " + this.uf + "\n" +
                "CIDADE: " + this.city + "\n" +
                "LOGRADOURO: " + this.avenue + "\n" +
                "==================================================\n";
    }
}
