package com.demo.notification.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="notification")
public class Notification {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "correlation_id")
	private String correlationId;

	@Column(name = "record_locator")
	private String recordLocator;
	
	@Column(name = "requested_at")
	private String requestedAt;
	
	@Column(name = "disposition_status")
	private String dispStatus;
	
	@Column(name = "total_checked_bag_count")
	private String totalCheckedBagCount;

	@Column(name = "mishandled_bag_count")
	private String mishandledBagCount;
	
	@Column(name = "status")
	private String notificationStatus;
	
	@Column(name = "incident_id", nullable = true)
	private String incidentId;

	@Column(name = "flight_id", nullable = false)
	private String flightId;

	@Column(name = "notification_type")
	private String notificationType;

    @JsonIgnore
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private Set<Disposition> dispositionSet = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getRecordLocator() {
		return recordLocator;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public String getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}

	public String getDispStatus() {
		return dispStatus;
	}

	public void setDispStatus(String dispStatus) {
		this.dispStatus = dispStatus;
	}

	public String getTotalCheckedBagCount() {
		return totalCheckedBagCount;
	}

	public void setTotalCheckedBagCount(String totalCheckedBagCount) {
		this.totalCheckedBagCount = totalCheckedBagCount;
	}

	public String getMishandledBagCount() {
		return mishandledBagCount;
	}

	public void setMishandledBagCount(String mishandledBagCount) {
		this.mishandledBagCount = mishandledBagCount;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public Set<Disposition> getDispositionSet() {
		return dispositionSet;
	}

	public void setDispositionSet(Set<Disposition> dispositionSet) {
		this.dispositionSet = dispositionSet;
	}

    
 }
