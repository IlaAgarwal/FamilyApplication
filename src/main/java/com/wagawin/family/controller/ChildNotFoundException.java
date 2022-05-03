package com.wagawin.family.controller;

import javax.persistence.EntityNotFoundException;

/**
 * An Exception when child not found
 */
public class ChildNotFoundException extends EntityNotFoundException {

	ChildNotFoundException(Long childId) {
		super("Child doesn't exist for ID:" + childId);
	}
}
