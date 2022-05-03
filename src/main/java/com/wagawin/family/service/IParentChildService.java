package com.wagawin.family.service;

import java.util.List;

public interface IParentChildService {

	public List<Long[]> countAllNewChildren();

	public void deleteAll();
}
