package com.collins.droneproject.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Model {
    CruiserWeight("CRUISER_WEIGHT"),
    LightWeight("LIGHT_WEIGHT"),
    MiddleWeight("MIDDLE_WEIGHT"),
    HeavyWeight("HEAVY_WEIGHT");

    @JsonValue
    public String label;

    private Model(String label){
        this.label = label;
    }

    @JsonCreator
    public static Model valueOfLabel(String label) {
        for (Model e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
