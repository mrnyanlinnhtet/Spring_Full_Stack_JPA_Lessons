package com.ultron.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(SalePk.class)
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;
	// @EmbeddedId
	// private SalePk pk;

	@Id
	@Column(name = "sequence_number", nullable = false)
	private int seqNumber;
	@Id
	@Column(name = "transaction_type", nullable = false)
	private int transactionType;
	private int sale;
	@Basic(optional = false)
	@Lob
	private String description;
	@Transient
	private boolean deleted;
	
	@Column(name = "sale_date")
	@Temporal(TemporalType.DATE)
	private Date saleDate;

}
