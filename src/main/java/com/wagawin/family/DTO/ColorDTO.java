package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author Ila Agarwal
 * DTO used for /child/color/{id}
 */
@Data
public class ColorDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(value = {View.ChildView.color.class})
	private String hairColor;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(value = {View.ChildView.color.class})
	private String bicycleColor;

}
