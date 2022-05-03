package com.wagawin.family.orm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="gender",length = 1,
		discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class Child implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person")
	@NotNull
	private Person parent;

	@ManyToMany
	@Fetch(FetchMode.SELECT)
	@OrderBy(value = "name asc")
	@JoinTable(name = "child_meal",
			joinColumns = { @JoinColumn(name = "childId") },
			inverseJoinColumns = { @JoinColumn(name = "mealId") })
	@NotNull
	private List<Meal> meals = new ArrayList<>();

	public Meal getfavoriteMeal(){
		if(!meals.isEmpty()) {
			Collections.sort(meals);
			return meals.get(0);
		}
		return null;
	}

	public boolean addMeal(Meal meal) {
		if (null == meals) {
			meals = new ArrayList<>();
		}

		if (null != meal) {
				meals.add(meal);
				meal.addChild(this);
				return true;
			}

		return false;
	}

	public Child(String name, int age , Person parent){
		this.name = name;
		this.age = age;
		this.parent = parent;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!(obj instanceof Child))
			return false;
		Child child = (Child) obj;
		return Objects.equals(this.id, child.id) && Objects.equals(this.name, child.name)
				&& Objects.equals(this.age, child.age);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.age);
	}

	@Override
	public String toString() {
		return "Child{" + "id=" + this.id + ", name='" + this.name + '\'' + ", age='" + this.age + '\'' + '}';
	}

}
