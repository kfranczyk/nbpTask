package com.mbp.task.pojos.fullTablesQuerries;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ExchangeRatesTable {
    private String table;
    private String no;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date effectiveDate;
    private List<EffectiveRate> rates;

    public ExchangeRatesTable(String table, String no, Date effectiveDate, List<EffectiveRate> rates){
            this.effectiveDate = effectiveDate;
            this.table = table;
            this.no = no;
            this.rates = rates;
    }
}
