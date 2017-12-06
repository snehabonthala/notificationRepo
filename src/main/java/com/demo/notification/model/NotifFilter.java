package com.demo.notification.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class NotifFilter {
	
	private String deliveredDate;
	
	private String channel;
	
	private String notificationType;
	
	@JsonProperty("deliveredDate") 
    public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@JsonProperty("channel") 
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@JsonProperty("notificationType") 
	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	
}
