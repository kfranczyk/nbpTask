package com.mbp.task;

import com.mbp.task.controllers.CurrencyController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTests {

    @Autowired
    MockMvc mvc;


    @ParameterizedTest
    @ValueSource(strings = {"EUR", "eur", "BRL", "PHP"})
    public void givenValidCurrencyCode_getCurrExchRate_thenIsStatusOk(String code) throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/rate/{code}", code)
                )
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"pln", "abc"})
    public void givenInvalidCurrencyCode_getCurrExchRate_throwIllegalArgumentException(String code) throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/rate/{code}", code)
        ).andExpect(status().isConflict());

    }


}
