package com.mbp.task.pojos.fullTablesQuerries;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectiveRate {
    private String currency;
    private String code;
    private float mid;

    @JsonCreator
    public EffectiveRate(String currency, String code, float mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }
}
