package com.wagawin.family.service;


import com.wagawin.family.orm.ParentSummary;

import java.util.List;
import java.util.Optional;

public interface IParentSummaryService {

	public int[] getParentSummary();

	public Optional<Integer> findMaxChildrenSize();

	public List<ParentSummary> findAll();

	public Optional<ParentSummary> findOptionalParentSummaryByAmountOfChildren(Long amountOfChildren);

 	public ParentSummary save(ParentSummary parentSummary);

	public long findSumofAllParents();

}
