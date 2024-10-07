import com.google.gson.Gson;
import exceptions.ErrorIllegalCharactersException;
import java.util.Scanner;
import models.Currency;
import models.CurrencyDataAPI;
import models.RequestAndResponse;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Gson gson = new Gson();
        String POPULAR_CURRENCIES = "Available Currencies:\nUSD - United States Dollar\nEUR - Euro\nGBP - British Pound Sterling\nJPY - Japanese Yen\nAUD - Australian Dollar\nCAD - Canadian Dollar\nCHF - Swiss Franc\nCNY - Chinese Yuan\nHKD - Hong Kong Dollar\nINR - Indian Rupee\nNZD - New Zealand Dollar\nSEK - Swedish Krona\nSGD - Singapore Dollar\nNOK - Norwegian Krone\nMXN - Mexican Peso\nZAR - South African Rand\n";
        System.out.println("Welcome to the Currency Converter!");
        System.out.println(POPULAR_CURRENCIES);

        while(true) {
            try {

                //Requesting input data
                System.out.println("Select the currency to convert (e.g., USD, EUR, GBP), or type 'exit' to exit: ");
                String initialCurrencyCode = keyboard.next().trim().toUpperCase();
                if (initialCurrencyCode.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Select the currency that you want to get a conversion from " + initialCurrencyCode + ", or type 'exit' to exit: ");
                String finalCurrencyCode = keyboard.next().trim().toUpperCase();
                if (finalCurrencyCode.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Enter the amount to convert:");
                Double amountToConvert = keyboard.nextDouble();

                //Making the request and getting the response
                String URL = "https://v6.exchangerate-api.com/v6/9c7615a80490188deefdf178/latest/" + initialCurrencyCode.replace(" ", "");
                RequestAndResponse requestAndResponse = new RequestAndResponse(URL);
                String json = requestAndResponse.responseToRequest();

                //Handling response errors
                if (json.contains("html") || json.contains("error")) {
                    throw new ErrorIllegalCharactersException("You didn't enter a valid currency code to convert");
                }

                //Adding response to a DTO
                CurrencyDataAPI currencyDataAPI = gson.fromJson(json, CurrencyDataAPI.class);

                //Creating the class to process the currency conversion using the DTO
                Currency currency = new Currency(currencyDataAPI, initialCurrencyCode, finalCurrencyCode, amountToConvert);

                //Handling response errors
                if (currency.convertion() == 0.0) {
                    throw new ErrorIllegalCharactersException("You didn't enter a valid currency code for the currency that wanted to get");
                }

                System.out.println(amountToConvert + " " + initialCurrencyCode + " = " + currency.convertion() + " " + finalCurrencyCode);
            } catch (ErrorIllegalCharactersException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
