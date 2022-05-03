package com.wagawin.family.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(indexes = { @Index(name = "parentSummary_Index", columnList = "amountOfChildren") })
public class ParentSummary implements Serializable,Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long amountOfPersons;

	private Long amountOfChildren;

	/**
	 *
	 * @param amountOfChildren
	 * @param amountOfPersons
	 */
	public ParentSummary(Long amountOfChildren, Long amountOfPersons){
		this.amountOfChildren = amountOfChildren;
		this.amountOfPersons = amountOfPersons;
	}

	/**
	 * Refer @{@link Comparable}.compareTo
	 * @param o Object to be sorted
	 * @return
	 */
	@Override
	public int compareTo(Object o) {
		return this.getAmountOfChildren().compareTo(((ParentSummary)o).getAmountOfChildren());
	}
}
