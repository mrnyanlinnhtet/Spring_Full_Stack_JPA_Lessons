package com.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "states")
@Getter
@Setter
@EqualsAndHashCode
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(nullable = false)
	private String region;
	@Column(nullable = false)
	private String capital;
	@Column(nullable = false)
	private int porpulation;

	public State() {

	}
	
	

	public State(String name, Type type, String region, String capital, int porpulation) {
		super();
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.porpulation = porpulation;
	}

	public State(int id, String name, Type type, String region, String capital, int porpulation) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.porpulation = porpulation;
	}

	public enum Type {
		State("State"), Region("Region"), Union("Union Territory");

		private String value;

		private Type(String value) {
			value = this.value;
		}

		public String getValue() {
			return value;
		}
	}

}
