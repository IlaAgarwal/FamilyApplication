package com.wagawin.family.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author Ila Agarwal
 * DTO for /parentSummary
 */
@Data
public class ParentSummaryDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int[] parentSummary;
}
