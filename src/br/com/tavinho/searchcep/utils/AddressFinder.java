package br.com.tavinho.searchcep.utils;

import java.io.IOException;

/**
 * The AddressFinder class is responsible for retrieving address information from the Viacep API.
 * It provides methods to fetch address data based on a CEP (postal code) or address details
 * such as UF (state), city, and avenue.
 */
public class AddressFinder {

    private String uri;

    /**
     * Retrieves address data from the Viacep API based on a CEP (postal code).
     *
     * @param cep The CEP (postal code) to look up the address.
     * @return The address data in JSON format fetched from the Viacep API.
     */
    public String getAddressBy(String cep) {
        this.uri = "https://viacep.com.br/ws/" + cep + "/json/";
        System.out.println(uri);
        return connectionAttempt(uri);
    }

    /**
     * Retrieves address data from the Viacep API based on UF (state), city, and avenue.
     *
     * @param uf     The UF (state) of the address.
     * @param city   The city of the address.
     * @param avenue The avenue or street of the address.
     * @return The address data in JSON format fetched from the Viacep API.
     */
    public String getAddressBy(String uf, String city, String avenue) {
        this.uri = "https://viacep.com.br/ws/" + uf.trim() + "/" + city.replace(" ", "%20").trim() + "/" + avenue.replace(" ", "%20").trim() + "/json/";
        System.out.println(uri);
        return connectionAttempt(uri);
    }

    /**
     * Attempts to establish a connection to the Viacep API and retrieve address data.
     * If the connection attempt fails due to IOException or InterruptedException,
     * an error message is printed, and the program terminates.
     *
     * @param uri The URI to fetch address data from the Viacep API.
     * @return The address data in JSON format fetched from the Viacep API.
     */
    private String connectionAttempt(String uri) {
        try {
            return DataRetriever.retrieveDataFromAPI(uri);
        } catch (IOException | InterruptedException e) {
            System.err.println("ERRO: Problema na requisição HTTP.\nPossíveis causas:\n\t- Ortografia dos dados inseridos\n\t- Falha na conexão com a Internet.");
            System.exit(1);
        }

        return null;
    }
}
