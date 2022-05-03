package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author Ila Agarwal
 * DTO for /house/{personId}
 */
@Data
@JsonView(value = {View.PersonView.house.class})
public class HouseDTO {

	@JsonView(value = {View.PersonView.house.class})
	private Long id;

	@JsonView(value = {View.PersonView.house.class})
	private String address;

	@JsonView(value = {View.PersonView.house.class})
	private String zipcode;
}
