package com.wagawin.family.service;

import com.wagawin.family.orm.House;
import com.wagawin.family.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseService implements IHouseService{

	private final HouseRepository houseRepository;

	@Transactional(readOnly = true)
	@Cacheable(value = "houseCache",key = "#personId" ,unless = "#result == null")
	public Optional<House> findOptionalByPerson_Id(Long personId){
		return houseRepository.findOptionalByPerson_Id(personId);

	}
}
