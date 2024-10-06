import com.google.gson.Gson;
import models.Currency;
import models.CurrencyDataAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Gson gson = new Gson();
        String POPULAR_CURRENCIES = """
            Available Currencies:
            USD - United States Dollar
            EUR - Euro
            GBP - British Pound Sterling
            JPY - Japanese Yen
            AUD - Australian Dollar
            CAD - Canadian Dollar
            CHF - Swiss Franc
            CNY - Chinese Yuan
            HKD - Hong Kong Dollar
            INR - Indian Rupee
            NZD - New Zealand Dollar
            SEK - Swedish Krona
            SGD - Singapore Dollar
            NOK - Norwegian Krone
            MXN - Mexican Peso
            ZAR - South African Rand
            """;

        System.out.println("Welcome to the Currency Converter!");
        System.out.println(POPULAR_CURRENCIES);

        while (true) {
            System.out.println("Select the currency to convert from (e.g., USD, EUR, GBP), or type 'exit' to exit: ");
            String initialCurrencyCode = keyboard.nextLine().trim().toUpperCase();
            if (initialCurrencyCode.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Select the currency to convert from" + initialCurrencyCode + ", or type 'exit' to exit: ");
            String finalCurrencyCode = keyboard.nextLine().trim().toUpperCase();
            if (initialCurrencyCode.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter the amount to convert:");
            Double amountToConvert = keyboard.nextDouble();
            String URL = "https://v6.exchangerate-api.com/v6/9c7615a80490188deefdf178/latest/" + initialCurrencyCode.replace(" ","");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                if (json.substring(0, 6).equals("<html>")) {
                    System.out.println("create exception");
                } else if (json.equals("{\"result\":\"error\",\"documentation\":\"https://www.exchangerate-api.com/docs\",\"terms-of-use\":\"https://www.exchangerate-api.com/terms\",\"error-type\":\"unsupported-code\"}")) {
                    System.out.println("create exception");
                } else {
                    CurrencyDataAPI currencyDataAPI = gson.fromJson(json, CurrencyDataAPI.class);
                    Currency currency = new Currency(currencyDataAPI, initialCurrencyCode, finalCurrencyCode, amountToConvert);
                    System.out.println(amountToConvert + " " + initialCurrencyCode + " = " + currency.convertion() + " " + finalCurrencyCode);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a valid name");
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
