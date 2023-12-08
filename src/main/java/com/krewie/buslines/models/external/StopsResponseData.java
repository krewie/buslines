package com.krewie.buslines.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StopsResponseData {
    @JsonProperty("Version")
    private String version;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Result")
    private List<StopPoint> result;
}
