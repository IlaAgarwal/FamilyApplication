package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;

/**
 * @Author Ila Agarwal
 * DTO used by @{@link ChildDTO} for favoriteMeal
 */
@Data
public class MealDTO {

	@JsonView(value = {View.ChildView.info.class})
	private String name;

	@JsonView(value = {View.ChildView.info.class})
	private Long id;

	@JsonView(value = {View.ChildView.info.class})
	private Date invented;
}
