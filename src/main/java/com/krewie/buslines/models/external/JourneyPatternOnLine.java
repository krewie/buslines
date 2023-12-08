package com.krewie.buslines.models.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class JourneyPatternOnLine {
    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("DirectionCode")
    private String directionCode;

    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    @JsonProperty("LastModifiedUtcDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date lastModifiedUtcDateTime;

    @JsonProperty("ExistsFromDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date existsFromDate;
}

