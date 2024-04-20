package com.mbp.task.pojos.singleRecordQuerries;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Currency {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    @JsonCreator
    public Currency(String table, String currency, String code,List<Rate> rates){
        this.currency = currency;
        this.code = code;
        this.table = table;
        this.rates = rates;
    }
}
