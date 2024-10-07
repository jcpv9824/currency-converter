package models;

import java.util.Map;

public record CurrencyDataAPI(Map<String, Double> conversion_rates) {

    public Map<String, Double> conversion_rates() {
        return this.conversion_rates;
    }
}
