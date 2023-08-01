package br.com.tavinho.searchcep.models;

import com.google.gson.annotations.SerializedName;

/**
 * The Address class represents an address with CEP (postal code), UF (state), city, and avenue (or street) information.
 * This class is used to store and manipulate address data retrieved from the Viacep API.
 */
public class Address implements Comparable<Address> {

    private String cep;
    private String uf;
    @SerializedName("localidade")
    private String city;
    @SerializedName("logradouro")
    private String avenue;

    /**
     * Constructs an Address object with the provided CEP (postal code).
     *
     * @param cep The CEP (postal code) of the address.
     */
    public Address(String cep) {
        this.cep = cep;
    }

    /**
     * Constructs an Address object with the provided UF (state), city, and avenue (or street) information.
     *
     * @param uf     The UF (state) of the address.
     * @param city   The city of the address.
     * @param avenue The avenue or street of the address.
     */
    public Address(String uf, String city, String avenue) {
        this.uf = uf;
        this.city = city;
        this.avenue = avenue;
    }

    /**
     * Gets the CEP (postal code) of the address.
     *
     * @return The CEP (postal code) of the address.
     */
    public String getCEP() {
        return this.cep;
    }

    /**
     * Gets the UF (state) of the address.
     *
     * @return The UF (state) of the address.
     */
    public String getUf() {
        return this.uf;
    }

    /**
     * Gets the city of the address.
     *
     * @return The city of the address.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Gets the avenue (or street) of the address.
     *
     * @return The avenue (or street) of the address.
     */
    public String getAvenue() {
        return this.avenue;
    }

    /**
     * Returns a string representation of the Address object, including its CEP, UF, city, and avenue (or street) information.
     *
     * @return A string representation of the Address object.
     */
    @Override
    public String toString() {
        return "CEP: " + getCEP() + "\n" +
                "ESTADO: " + getUf() + "\n" +
                "CIDADE: " + getCity() + "\n" +
                "LOGRADOURO: " + getAvenue() + "\n" +
                "==================================================\n";
    }

    /**
     * Compares this Address object with another Address object based on avenue names for sorting.
     * The sorting is done in a case-insensitive manner.
     *
     * @param address The other Address object to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the other object.
     */
    @Override
    public int compareTo(Address address) {
        return this.avenue.compareToIgnoreCase(address.getAvenue());
    }
}
