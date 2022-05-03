package com.wagawin.family.controller;

import com.wagawin.family.DTO.ParentSummaryDTO;
import com.wagawin.family.service.IParentSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

/**
 * @Author Ila Agarwal
 * Endpoint for ParentSummaryController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/parentSummary")
public class ParentSummaryController {

	private final IParentSummaryService psService;

	@GetMapping
	public ParentSummaryDTO getParentSummary(){
		ParentSummaryDTO parentSummaryDTO = new ParentSummaryDTO();
		int[] parentSummaryArr = psService.getParentSummary();
		if(null==parentSummaryArr){
			throw new EntityNotFoundException("Parent Summary doesn't exist");
		}
		parentSummaryDTO.setParentSummary( psService.getParentSummary());
		return parentSummaryDTO;
	}
}
