package com.demo.notification.model;

import javax.persistence.*;

@Entity
public class Disposition {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "disposition")
	private String disposition;

	@Column(name = "channel")
	private String channel;

	@Column(name = "delivered_date")
	private String deliveredDate;

	@Column(name = "contact_info")
	String contactInfo;

	@Column(name = "record_locator")
	String recordLocator;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "aadvantage")
	String aAdvantage;

	@Column(name = "phone_type")
	String phoneType;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getRecordLocator() {
		return recordLocator;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getaAdvantage() {
		return aAdvantage;
	}

	public void setaAdvantage(String aAdvantage) {
		this.aAdvantage = aAdvantage;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
    
}
