package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="person_dtl")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue
	@Column(name = "person_id")
	private Long id;
	
	private String name;
	private String email;
	private Long age;
}
