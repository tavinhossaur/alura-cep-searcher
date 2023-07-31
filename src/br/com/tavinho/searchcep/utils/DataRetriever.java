package br.com.tavinho.searchcep.utils;

import br.com.tavinho.searchcep.models.Address;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class DataRetriever {
    public static String retrieveDataFromAPI(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static List<Address> formatDataIntoList(String data, int option){
        Gson gson = new Gson();

        Type type = new TypeToken<List<Address>>() {}.getType();

        if (option == 1) return Collections.singletonList(gson.fromJson(data, Address.class));
        else return gson.fromJson(data, type);
    }
}
