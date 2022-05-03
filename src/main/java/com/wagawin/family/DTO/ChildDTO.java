package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author Ila Agarwal
 * {@link ChildDTO } for /child/info/{id}
 */
@Data
public class ChildDTO {

	@JsonView(value = {View.ChildView.info.class})
	private PersonDTO parent;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(value = {View.ChildView.info.class})
	private MealDTO favoriteMeal;
}
