package com.wagawin.family.repository;

import com.wagawin.family.orm.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {

		public Optional<House> findOptionalByPerson_Id(Long personId);

}
