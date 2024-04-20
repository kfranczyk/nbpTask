package com.mbp.task.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "mbp")
@Getter
@Setter
@Data
public class ProjectSettingsConfiguration {

    private String baseMbpApiURL;

}
