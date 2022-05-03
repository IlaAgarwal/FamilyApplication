package com.wagawin.family.orm;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("M")
public class Son extends Child{


	@NotNull
	private String bicycleColor;

	public Son(String name, int age,Person parent, String bicycleColor){
		super(name,age,parent);
		this.bicycleColor = bicycleColor;
	}
}
