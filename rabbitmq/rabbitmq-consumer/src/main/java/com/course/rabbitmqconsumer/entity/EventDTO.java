package com.course.rabbitmqconsumer.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EventDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1153323519298278792L;

	private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm")
    @JsonProperty("event_date")
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

    @JsonProperty("action")
    private String action;

    private String payload;

}
