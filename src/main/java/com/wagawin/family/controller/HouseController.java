package com.wagawin.family.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wagawin.family.DTO.HouseDTO;
import com.wagawin.family.DTO.View;
import com.wagawin.family.orm.House;
import com.wagawin.family.service.IHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/house")
public class HouseController {


	private final IHouseService houseService;

	@Autowired
	ModelMapper modelMapper;


	/**
	 * Retruns HTTP GET requests to /house/{personId}
	 * @param personId
	 * @return HouseDTO convets the object to JSON
	 */
	@GetMapping(value = "/{personId}")
	@JsonView(value = View.PersonView.house.class)
	public HouseDTO findByPerson(@PathVariable Long personId) {
		log.info("EndPoint: /house/"+personId);
		Optional<House> house = Optional.of(houseService.findOptionalByPerson_Id(personId)
				.orElseThrow(() -> new HouseNotFoundException(personId)));

		return modelMapper.map(house.get(), HouseDTO.class);
	}

}
