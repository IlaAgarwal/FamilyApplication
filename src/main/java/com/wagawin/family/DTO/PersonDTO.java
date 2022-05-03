package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author Ila Agarwal
 * DTO used by @{@link ChildDTO} for parent
 */
@Data
public class PersonDTO {

	@JsonView(value = {View.ChildView.info.class})
	Long id;

	@JsonView(value = {View.ChildView.info.class})
	String name;

	@JsonView(value = {View.ChildView.info.class})
	int age;
}
