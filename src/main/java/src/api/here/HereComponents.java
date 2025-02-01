package src.api.here;

import src.api.keys.HereKey;
import src.model.entities.UserEntities.Address;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HereComponents {

    public static boolean isAddressValid(Address address) {

        try {

            String obtainAddress = String.format("%s+%s+%s", address.getNeighbourhood(), address.getNumber(), address.getCity());

            String encodedAddress = URLEncoder.encode(obtainAddress, StandardCharsets.UTF_8);

            String urlString = "https://geocode.search.hereapi.com/v1/" +
                    "geocode" +
                    "?q=" + encodedAddress +
                    "&apiKey=" + HereKey.HERE_KEY;

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();

            System.out.println(jsonResponse);

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            System.out.println(jsonObject);

            JsonObject firstItem = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();
            JsonObject address1 = firstItem.getAsJsonObject("address");

            System.out.println(address);

            String label = address1.has("label") ? address1.get("label").getAsString() : "Label não encontrado";
            String houseNumber = address1.has("houseNumber") ? address1.get("houseNumber").getAsString() : "Número não encontrado";
            String city = address1.has("city") ? address1.get("city").getAsString() : "Cidade não encontrada";

                System.out.println(label);

                System.out.println(houseNumber);

                System.out.println(city);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean isValid = isAddressValid(new Address("","183", "11x", "sexo", "San Paolo"));
        System.out.println(isValid);
    }

}
