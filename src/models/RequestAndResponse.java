package models;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestAndResponse {
    private String url;

    public RequestAndResponse(String url) {
        this.url = url;
    }

    public String responseToRequest() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.url)).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response.body();
        } catch (Exception var4) {
            System.out.println("An exception ocurred during the process of making and receiving the request");
            return "error";
        }
    }
}
