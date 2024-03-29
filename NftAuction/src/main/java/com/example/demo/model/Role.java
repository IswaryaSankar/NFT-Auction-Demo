package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
/**This entity class is used to create and persist the role of the user into the roles table */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String id;

	@Enumerated(EnumType.STRING)
	private ERole name;

	public Role(ERole name) {
		this.name = name;
	}

	
}