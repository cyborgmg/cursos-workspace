package com.howtodoinjava.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.instrument.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Response body for exceptions.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private String path;
    private Integer status;
    private String error;
    private String message;

    @JsonIgnore
    public Collection<Tag> toTags() {
        return Arrays.asList(Tag.of("error", this.status.toString()));
    }
}
