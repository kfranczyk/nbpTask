package com.mbp.task.configuration;

import com.mbp.task.pojos.CountryCodesTableLocation;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CountryCodesTableLocation countryCodesTableLocationSingleton() {
        return new CountryCodesTableLocation();
    }

}
