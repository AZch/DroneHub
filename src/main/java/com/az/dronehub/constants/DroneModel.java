package com.az.dronehub.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DroneModel {
    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISEWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    @Getter
    private final String name;
}
