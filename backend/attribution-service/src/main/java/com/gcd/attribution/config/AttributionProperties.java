package com.gcd.attribution.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "attribution")
public class AttributionProperties {

    private int defaultStartDateOffsetDays = 1;
    private List<LocalDate> holidays = new ArrayList<>();
}
