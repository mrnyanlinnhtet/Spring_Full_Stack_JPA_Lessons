package com.ultron.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

//@Embeddable
@Getter
@Setter
public class SalePk implements Serializable {

	private static final long serialVersionUID = 1L;

	private int seqNumber;
	private int transactionType;

	public SalePk() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(seqNumber, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalePk other = (SalePk) obj;
		return seqNumber == other.seqNumber && transactionType == other.transactionType;
	}

}
