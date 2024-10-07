package models;

import exceptions.ErrorCurrencyIllegalCharactersException;

import java.util.Map;

public class Currency {
    private Map<String, Double> conversionRates;
    private String initialCurrencyCode;
    private String finalCurrencyCode;
    private Double amountToConvert;

    public Currency(CurrencyDataAPI currency_code, String initialCurrencyCode, String finalCurrencyCode, Double amountToConvert) {
        this.conversionRates = currency_code.conversion_rates();
        this.initialCurrencyCode = initialCurrencyCode;
        this.finalCurrencyCode = finalCurrencyCode;
        this.amountToConvert = amountToConvert;
    }

    public String toString() {
        return "conversionRates=" + this.conversionRates + ", initialCurrencyCode='" + this.initialCurrencyCode + "', finalCurrencyCode='" + this.finalCurrencyCode;
    }

    public Double convertion() {
        try {
            if (this.conversionRates.get(this.finalCurrencyCode) == null) {
                throw new ErrorCurrencyIllegalCharactersException("You didn't enter a valid currency code to convert");
            }
            Double convertionRateToFinalCurrency = this.conversionRates.get(this.finalCurrencyCode);
            return convertionRateToFinalCurrency * this.amountToConvert;
        } catch (ErrorCurrencyIllegalCharactersException e) {
            return 0.0;
        }
    }
}
