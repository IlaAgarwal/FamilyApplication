package com.wagawin.family.orm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Meal implements Serializable, Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@DateTimeFormat(pattern="YYYY-MM-DD")
	private Date invented;

	@ManyToMany(mappedBy = "meals")
	private Set<Child> children;

	public Meal(String name, Date invented ){
		this.name = name;
		this.invented = invented;
	}

	public boolean addChild(Child child){
		if(null==children){
			children = new HashSet<>();
		}
		if(null!=child){
			children.add(child);
			return true;
		}
		return false;
	}
	/**
	 * Refer @{@link Comparable}.compareTo
	 * @param meal Object to be sorted
	 * @return
	 */
	@Override
	public int compareTo(Object meal) {
		return Comparator.comparing(Meal::getInvented)
				.thenComparing(Meal::getName)
				.compare(this, (Meal) meal);
	}


}
