package com.bullvsbear.dbservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_quote")
public class UserQuote {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "quote_id")
	private long id;
	
	@Column(name = "exchange",length = 10,nullable = false,unique = false)
	private String exchange;
	
	@Column(name = "short_name",length = 25, nullable = false,unique=false)
	private String shortName; 
	
	@Column(name="quote_type",length = 10,nullable = false,unique=false)
	private String quoteType;
	
	@Column(name="symbol",length = 15,nullable = false,unique=false)
	private String symbol;
	
	@Column(name="currency",nullable = false,unique = false)
	private String currency;

	@Column(name="type_disp",length = 10,nullable = false,unique=false)
	private String typeDisp;
	
	@Column(name="long_name",length = 30,nullable = false,unique=false)
	private String longName;
	
	@Column(name="price",nullable = false,unique = false)
	private double price;
	
	@Column(name="date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	public UserQuote() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTypeDisp() {
		return typeDisp;
	}

	public void setTypeDisp(String typeDisp) {
		this.typeDisp = typeDisp;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object object) {
        if (object == this) { 
            return true; 
        } 
        if(object.getClass() != this.getClass()) {
        	return false;
        } 
        UserQuote userQuote = (UserQuote)object;
        if(userQuote.getExchange() == this.getExchange() && this.longName == userQuote.getLongName())
        	return true;
        
        return false;
	}

	public UserQuote(String exchange, String shortName, String quoteType, String symbol, String currency,
			String typeDisp, String longName, double price) {
		
		this.exchange = exchange;
		this.shortName = shortName;
		this.quoteType = quoteType;
		this.symbol = symbol;
		this.currency = currency;
		this.typeDisp = typeDisp;
		this.longName = longName;
		this.price = price;
	}
	
	
}
