package com.course.rabbitmqproducer.entity;

import java.io.Serializable;


public class TrackingEventIn implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4547854349023857789L;
	
	
	private Long id;
    private String payload;
    private Boolean processed;
    private String action;
    private String producer;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}    

}
