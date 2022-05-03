package com.wagawin.family.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("F")
public class Daughter extends Child{

	@NotNull
	private String hairColor;

	public Daughter(String name, int age,Person parent, String hairColor){
		super(name,age,parent);
		this.hairColor = hairColor;
	}
}