package br.com.tavinho.searchcep.utils;

import java.io.IOException;

public class Finder {

    private String uri;
    private final String errorMessage = "ERRO: Problema na requisição HTTP.\nPossíveis causas:\n\t- Ortografia dos dados inseridos\n\t- Falha na conexão com a internet.";

    public String getAddressBy(String cep) {

        this.uri = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            return DataRetriever.retrieveDataFromAPI(uri);
        } catch (IOException | InterruptedException e) {
            System.err.println(errorMessage);
            System.exit(1);
        }

        return null;
    }

    public String getAddressBy(String uf, String city, String avenue) {

        this.uri = "https://viacep.com.br/ws/" + uf + "/" + city.replace(" ", "%20") + "/" + avenue.replace(" ", "+") + "/json/";

        try {
            return DataRetriever.retrieveDataFromAPI(uri);
        } catch (IOException | InterruptedException e) {
            System.err.println(errorMessage);
            System.exit(1);
        }

        return null;
    }
}
