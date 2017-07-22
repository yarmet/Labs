package com.company.labs34.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by ruslan on 19.07.2017.
 */
@Data
@Builder
public class ToJson {

    @JsonProperty("device_name:")
    private String deviceName;

    @JsonProperty("device_coordinate")
    private String deviceCoordinate;

    @JsonProperty("param_name")
    private String paramName;

    @JsonProperty("value")
    private String value;
}
