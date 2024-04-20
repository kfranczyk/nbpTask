package com.mbp.task.pojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class CountryCodesTableLocation {

    Map<String, String> countryCodeLocationTable = new HashMap<String, String>();

    public CountryCodesTableLocation() {
        this.countryCodeLocationTable = UpdateCountryCodeTables.getCurrentCountryCodesMap();
    }


}
