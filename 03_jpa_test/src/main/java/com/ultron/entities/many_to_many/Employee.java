package com.ultron.entities.many_to_many;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "employee_name",length = 20,nullable = false)
	private String name;
    @Column(nullable = false,columnDefinition = "VARCHAR(30) DEFAULT 'STAFF' ")
    @Enumerated(EnumType.STRING)
	private Role role;
    @Column(length = 50,nullable = false)
	private String email;
    
    @ManyToMany
    @JoinTable(name = "emploee_project",
    joinColumns = @JoinColumn(name = "employee_id"), 
    inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project>projects;
	
	public enum Role{
		MANAGER,STAFF,HR,ENGINEER
	}
	
	
	public Employee() {
		
	}

}
