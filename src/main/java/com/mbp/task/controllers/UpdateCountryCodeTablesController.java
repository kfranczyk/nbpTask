package com.mbp.task.controllers;

import com.mbp.task.pojos.CountryCodesTableLocation;
import com.mbp.task.pojos.UpdateCountryCodeTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class UpdateCountryCodeTablesController {

    @Autowired
    CountryCodesTableLocation countryCodesTableLocation;

    @Scheduled( cron = "* * 6 * * MON-FRI" )
    void update(){
        countryCodesTableLocation.setCountryCodeLocationTable( UpdateCountryCodeTables.getCurrentCountryCodesMap() );
    }

}
