package com.retobbva.challenge.model;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "request")
@EntityListeners(AuditingEntityListener.class)
public class RequestModel {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double originAmount;
	private String originCurrency;
	private double currencyAmount;
	private String destinationCurrency;
	private double destinationAmount;
	
	private String createdBy;
	private Date createdAt;
	
	public RequestModel() {}
	public RequestModel(Long id, double originAmount, String originCurrency, double currencyAmount,
			String destinationCurrency, double destinationAmount, String createdBy, Date createdAt) {
		super();
		this.id = id;
		this.originAmount = originAmount;
		this.originCurrency = originCurrency;
		this.currencyAmount = currencyAmount;
		this.destinationCurrency = destinationCurrency;
		this.destinationAmount = destinationAmount;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getOriginAmount() {
		return originAmount;
	}
	public void setOriginAmount(double originAmount) {
		this.originAmount = originAmount;
	}
	public String getOriginCurrency() {
		return originCurrency;
	}
	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}
	public double getCurrencyAmount() {
		return currencyAmount;
	}
	public void setCurrencyAmount(double currencyAmount) {
		this.currencyAmount = currencyAmount;
	}
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}
	public double getDestinationAmount() {
		return destinationAmount;
	}
	public void setDestinationAmount(double destinationAmount) {
		this.destinationAmount = destinationAmount;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
