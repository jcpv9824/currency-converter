package models;

import java.util.Map;

public class Currency {

    private Map<String, Double> conversionRates;
    private String initialCurrencyCode;
    private String  finalCurrencyCode;
    private Double amountToConvert;

    public Currency(CurrencyDataAPI currency_code, String initialCurrencyCode, String  finalCurrencyCode, Double amountToConvert) {
        this.conversionRates = currency_code.conversion_rates();
        this.initialCurrencyCode = initialCurrencyCode;
        this.finalCurrencyCode = finalCurrencyCode;
        this.amountToConvert = amountToConvert;
    }

    @Override
    public String toString() {
        return "conversionRates=" + conversionRates +
                ", initialCurrencyCode='" + initialCurrencyCode + '\'' +
                ", finalCurrencyCode='" + finalCurrencyCode;
    }

    public Double convertion() {
        Double convertionRateToFinalCurrency = conversionRates.get(finalCurrencyCode);
        return convertionRateToFinalCurrency * amountToConvert;
    }
}
