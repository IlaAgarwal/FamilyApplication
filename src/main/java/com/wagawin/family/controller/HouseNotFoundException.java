package com.wagawin.family.controller;

import javax.persistence.EntityNotFoundException;

/**
 * @Author Ila Agarwal
 * HouseNotFOundException
 */
public class HouseNotFoundException extends EntityNotFoundException {

	HouseNotFoundException(Long personId) {

		super("Could not find house for PersonID:" + personId);
	}
}