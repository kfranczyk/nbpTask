package com.mbp.task.pojos;

import com.mbp.task.pojos.fullTablesQuerries.EffectiveRate;
import com.mbp.task.pojos.fullTablesQuerries.ExchangeRatesTable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.*;

@Component
public class UpdateCountryCodeTables {


    public static Map<String, String> getCurrentCountryCodesMap() {

        URI tableA = URI.create("http://api.nbp.pl/api/exchangerates/tables/a/");
        URI tableB = URI.create("http://api.nbp.pl/api/exchangerates/tables/b/");

        Map<String, String> map = new HashMap<>();
        map.putAll(mapCountriesToTableCode(getCountriesTableCode(tableA)));
        map.putAll(mapCountriesToTableCode(getCountriesTableCode(tableB)));

        return map;
    }

    static private List<ExchangeRatesTable> getCountriesTableCode(URI url) {
        RestClient restClient = RestClient.create();
        ExchangeRatesTable[] exchangeRatesTables = restClient.get()
                .uri(url)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ExchangeRatesTable[].class);


        return new ArrayList<>(Arrays.stream(exchangeRatesTables).toList());
    }

    static private Map<String, String> mapCountriesToTableCode(List<ExchangeRatesTable> tables) {
        Map<String, String> map = new HashMap<String, String>();

        for (ExchangeRatesTable table : tables) {
            for (EffectiveRate rate : table.getRates()) {
                map.put(rate.getCode(), table.getTable());
            }
        }
        return map;
    }
}
