package com.wagawin.family.repository;

import com.wagawin.family.orm.ParentSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  ParentSummaryRepository extends CrudRepository<ParentSummary,Long> {

	public List<ParentSummary> findAll();

	/**
	 * Maximum number of children in the Summary
	 * @return Optional<Integer>
	 */
	@Query(value = "Select max(ps.amountOfChildren) from ParentSummary ps")
	public Optional<Integer> findOptionalMaxChildrenSize();

	/**
	 * Returns, the ParentSummary for given AmountOfChildren
	 * @param amountOfChildren
	 * @return Optional<ParentSummary>
	 */
	public Optional<ParentSummary> findOptionalParentSummaryByAmountOfChildren(Long amountOfChildren);

	/**
	 * Return the total number of parents in Parent Summary
 	 * @return Optional<Long> the total number of parents
	 */
	@Query(value = "Select SUM(ps.amountOfPersons) from ParentSummary ps")
	public Optional<Long> findOptionalSumofAllParents();


}
