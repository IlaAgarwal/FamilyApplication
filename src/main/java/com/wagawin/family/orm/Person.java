package com.wagawin.family.orm;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2)
	@NotNull
	private String name;

	@NotNull
	private int age;

	@OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
	@Nullable
	private Set<Child> children;



	public Person(String name, int age){
		this.name= name;
		this.age=age;
	}

	public void addChild(Child child) {
		this.children.add(child);
		child.setParent(this);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person person = (Person) obj;
		return Objects.equals(this.id, person.id) && Objects.equals(this.name, person.name)
				&& Objects.equals(this.age, person.age);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.age);
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + this.id + ", name='" + this.name + '\'' + ", age='" + this.age + '\'' + '}';
	}

}
