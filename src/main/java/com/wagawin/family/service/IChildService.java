package com.wagawin.family.service;

import com.wagawin.family.orm.Child;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IChildService {

	public Optional<Child> findChildByID( Long id);

	public Optional<Child> findOptionalColorByID(Long id);

	public List<Long[]> countChildrenByParent(Set<Long> parentIds);

}
