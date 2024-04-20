package com.mbp.task.pojos.singleRecordQuerries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Builder
public class Rate {
    private String no;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date effectiveDate;
    private float mid;

    @JsonCreator
    public Rate(String no, Date effectiveDate, float mid){
        this.no = no;
        this.mid = mid;
        this.effectiveDate = effectiveDate;

    }
}
