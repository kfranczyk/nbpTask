package com.mbp.task.controllers;

import com.mbp.task.configuration.ProjectSettingsConfiguration;
import com.mbp.task.pojos.CountryCodesTableLocation;
import com.mbp.task.pojos.singleRecordQuerries.Currency;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class CurrencyController {

    private ProjectSettingsConfiguration projectConfiguration;
    private CountryCodesTableLocation countryCodesTableLocation;


    @GetMapping(value = "/rate/{currShortName}")
    public ResponseEntity<Float> getCurrExchRate(@PathVariable String currShortName) {

        //returns A or B
        String currTableLocation = countryCodesTableLocation.getCountryCodeLocationTable().get(currShortName.toUpperCase());

        if (null == currTableLocation) {
            throw new IllegalArgumentException("Theres no currency with given code");
        }

        String baseUrl = String.format("%s/exchangerates/rates/%s/%s",
                projectConfiguration.getBaseMbpApiURL(),
                currTableLocation,
                currShortName);


        Optional<Currency> currency = Optional.ofNullable(RestClient.create()
                .get()
                .uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .body(Currency.class));


        return currency.map(value -> ResponseEntity.ofNullable(value.getRates().get(0).getMid()))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }


    @GetMapping(value = "/getallrates")
    public String getAllCurrencyTableLocations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dostępne waluty | Ich lokalizacja w tablicy\n");
        countryCodesTableLocation.getCountryCodeLocationTable().forEach((s, s2) -> sb.append(String.format("%s | %s\n", s, s2)));
        return sb.toString();
    }

}
