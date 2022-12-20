package com.ultron.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private String phone;
	private String email;
	private String address;

	public Contact() {

	}

	public Contact(String phone, String email, String address) {
		super();
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(phone, other.phone);
	}

}
