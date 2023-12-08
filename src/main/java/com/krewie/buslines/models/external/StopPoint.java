package com.krewie.buslines.models.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class StopPoint {
    @JsonProperty("StopPointNumber")
    private String stopPointNumber;

    @JsonProperty("StopPointName")
    private String stopPointName;

    @JsonProperty("StopAreaNumber")
    private String stopAreaNumber;

    @JsonProperty("LocationNorthingCoordinate")
    private String locationNorthingCoordinate;

    @JsonProperty("LocationEastingCoordinate")
    private String locationEastingCoordinate;

    @JsonProperty("ZoneShortName")
    private String zoneShortName;

    @JsonProperty("StopAreaTypeCode")
    private String stopAreaTypeCode;

    @JsonProperty("LastModifiedUtcDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date lastModifiedUtcDateTime;

    @JsonProperty("ExistsFromDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date existsFromDate;
}
