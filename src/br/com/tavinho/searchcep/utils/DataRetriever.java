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

/**
 * Utility class for retrieving data from APIs and deserializing JSON data.
 */
public class DataRetriever {

    /**
     * Retrieves data from the specified URI using HTTP GET request.
     *
     * @param uri The URI to retrieve data from.
     * @return The response body as a string.
     * @throws IOException          If an I/O error occurs while processing the request.
     * @throws InterruptedException If the operation is interrupted while waiting for the response.
     */
    public static String retrieveDataFromAPI(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    /**
     * Deserializes JSON data into a list of Address objects.
     *
     * @param data   The JSON data to be deserialized.
     * @param isCep  The user-selected option (true for address by CEP, false for CEP by address).
     * @return A list of Address objects representing the deserialized data.
     */
    public static List<Address> deserializeData(String data, boolean isCep) {
        Gson gson = new Gson();

        /*
            Type information is lost during compilation (type erasure).
            TypeToken is used to retain the <List<Address>> information
            to make it possible for Gson to understand the type during deserialization at runtime.
        */
        Type type = new TypeToken<List<Address>>() {}.getType();

        if (isCep) {
            // If the search is from an CEP, return a singleton list with a single Address object
            return Collections.singletonList(gson.fromJson(data, Address.class));
        } else {
            // return a list of Address objects since it's possible to have more than one address returned from the API
            return gson.fromJson(data, type);
        }
    }
}
