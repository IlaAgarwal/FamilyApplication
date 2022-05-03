package com.wagawin.family.service;

import com.wagawin.family.orm.House;

import java.util.Optional;

public interface IHouseService {

	public Optional<House> findOptionalByPerson_Id(Long personId);
}
