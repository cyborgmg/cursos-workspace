package com.kestraa.apiproducer.dto;

import com.crawler.mantra.model.Action;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {

    @JsonProperty("event_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date eventDate;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("corporation_id")
    private Long corporationId;

    private String producer;

    @JsonProperty("routing_key")
    private String routingKey;

    @JsonProperty("entity_id")
    private Long entityId;

    @JsonProperty("entity_type")
    private String entityType;

    @JsonProperty("entity_version")
    private String entityVersion;

    private String payload;

    @JsonProperty("action")
    private Action action;

}
