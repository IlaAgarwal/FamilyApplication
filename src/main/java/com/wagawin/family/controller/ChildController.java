package com.wagawin.family.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wagawin.family.DTO.ChildDTO;
import com.wagawin.family.DTO.ColorDTO;
import com.wagawin.family.DTO.View;
import com.wagawin.family.orm.Child;
import com.wagawin.family.service.ChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/child")
public class ChildController {

	private final ChildService childService;

	private final ModelMapper modelMapper;

	/**
	 * Recieves request for Child info
	 * @param childId
	 * @return ChildDTO the DTO to b returned as response
	 */
	@JsonView(value = View.ChildView.info.class)
	@GetMapping(value = "info/{childId}")
	public ChildDTO findById(@PathVariable("childId") Long childId) {
		log.info("Requested: /child/info/",childId);
		Optional<Child> child = Optional.of(childService.findChildByID(childId)
				.orElseThrow(() -> new ChildNotFoundException(childId)));
		return modelMapper.map(child.get(),ChildDTO.class);
	}

	/**
	 * Recieves request for Child color
	 * @param childId
	 * @return ColorDTO return {@link ColorDTO}
	 */
	@JsonView(value = View.ChildView.color.class)
	@GetMapping(value = "color/{childId}")
	public ColorDTO findColorById(@PathVariable Long childId) {
		log.info("Requested: /child/color/"+childId);
		Optional<Child> child = Optional.of(childService.findOptionalColorByID(childId)
				.orElseThrow(() -> new ChildNotFoundException(childId)));
		return modelMapper.map(child.get(),ColorDTO.class);
	}

}
