package com.az.dronehub.config;

import com.az.dronehub.constants.DroneState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "drone-hub.availability")
@Getter
@Setter
public class AvailabilityConfig {
    private int minimalBattery;
    private List<DroneState> availableStates;
}
