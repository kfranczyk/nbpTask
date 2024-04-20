package com.mbp.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.mbp.task.configuration.ProjectSettingsConfiguration;
import com.mbp.task.controllers.CurrencyController;
import com.mbp.task.pojos.CountryCodesTableLocation;
import com.mbp.task.pojos.singleRecordQuerries.Currency;
import com.mbp.task.pojos.singleRecordQuerries.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.sql.Date;
import java.util.ArrayList;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@WireMockTest(proxyMode = true)
@SpringBootTest
public class CurrencyControllerWiremockTest {

    @Value("${mbp.baseMbpApiURL}")
    private String baseURL;

    @Autowired
    static CurrencyController currencyController;

    static ProjectSettingsConfiguration projectSettingsConfiguration;
    static CountryCodesTableLocation countryCodesTableLocation;

    @BeforeEach
    void init() {
        projectSettingsConfiguration = new ProjectSettingsConfiguration();
        projectSettingsConfiguration.setBaseMbpApiURL(baseURL);
        countryCodesTableLocation = new CountryCodesTableLocation();
        currencyController = new CurrencyController(projectSettingsConfiguration, countryCodesTableLocation);
    }

    @Test
    void givenSuccesfulApiResp_getCurrExchangeRateVal_isEqualMockedVal(WireMockRuntimeInfo wmRuntimeInfo) throws JsonProcessingException {
        String apiUrl = "/api/exchangerates/rates/A/EUR";

        Currency respBody = getMockResponseObj();
        ObjectMapper Obj = new ObjectMapper();

        //Define stub
        stubFor(get(apiUrl)
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(Obj.writeValueAsString(respBody))
                ));

        var controllerResp = currencyController.getCurrExchRate("EUR");
        float controlllerRespVal = controllerResp.getBody().floatValue();
        float mockRespVal = respBody.getRates().get(0).getMid();

        assertEquals(mockRespVal, controlllerRespVal);
        //Verify API is hit
        verify(getRequestedFor(urlEqualTo(apiUrl)));
    }

    private Currency getMockResponseObj() {
        Rate rate = Rate.builder()
                .mid(4.20f)
                .effectiveDate(Date.valueOf("2022-02-05"))
                .no("ABC").build();

        Currency tmpCurr = Currency.builder()
                .table("A")
                .currency("EUR")
                .rates(new ArrayList<Rate>() {
                    {
                        add(rate);
                    }
                })
                .code("ABC").build();

        return tmpCurr;
    }


}
