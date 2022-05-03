package com.wagawin.family.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name="HOUSE")
public class House implements Serializable {

	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@MapsId
	@JoinColumn(name = "id")
	@JsonIgnore
	private Person person;
	private String address;

	@Size( min = 5,max = 5, message = "Zip Code should have alength of 5")
	private String zipcode;

	@Column(columnDefinition = "ENUM('FLAT', 'HOUSE', 'ESTATE')")
	@Enumerated(EnumType.STRING)
	private HouseType type;


	public House(String address, String zipCode, HouseType type){
		this.address = address;
		this.zipcode = zipCode;
		this.type = type;
	}


	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!(obj instanceof House))
			return false;
		House house = (House) obj;
		return Objects.equals(this.id, house.id) && Objects.equals(this.address, house.address)
				&& Objects.equals(this.zipcode, house.zipcode)
				&& Objects.equals(this.type,house.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.address, this.zipcode,this.type);
	}

	@Override
	public String toString() {
		return "House{" + "id=" + this.id + ", address='" + this.address + '\'' + ", type='" + this.type + '\'' + '}';
	}
}
