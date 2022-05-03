package com.wagawin.family.service;

import com.wagawin.family.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService implements IPersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public long countAllPerson(){
		return personRepository.count();
		}

}

