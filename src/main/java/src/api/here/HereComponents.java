package src.api.here;

import src.api.keys.HereKey;
import src.exceptions.HereApiException;
import src.model.entities.UserEntities.Address;
import com.google.gson.*;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HereComponents {

    public static Address obtainAddressThroughApi (Address address) {

        try {

            String obtainAddress = String.format("%s+%s+%s", address.getLabel(), address.getNumber(), address.getCity());

            String encodedAddress = URLEncoder.encode(obtainAddress, StandardCharsets.UTF_8);

            String urlString = "https://geocode.search.hereapi.com/v1/" +
                    "geocode" +
                    "?q=" + encodedAddress +
                    "&apiKey=" + HereKey.HERE_KEY;

            URI uri = new URI(urlString);

            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/json")
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String jsonResponse = response.body();

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            JsonObject firstItem = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();

            JsonObject findCurrentAddress = firstItem.getAsJsonObject("address");

            return findElements(address.getCEP(), address.getComplement(), findCurrentAddress);

        } catch (URISyntaxException | IOException | InterruptedException exception) {

            throw new HereApiException(exception.getMessage());

        }

    }

    private static Address findElements (String CEP, String complement, JsonObject currentAddress) {

        String label = currentAddress.get("label").getAsString();

        String houseNumber = currentAddress.get("houseNumber").getAsString();

        String city = currentAddress.get("city").getAsString();

        return new Address(CEP, houseNumber, complement, label, city);

    }

    public static void main (String[] args) {

        Address address = obtainAddressThroughApi(new Address("", "280", "11D", "Pedro II", "Campina Grande"));

        System.out.println(address);

    }

}
