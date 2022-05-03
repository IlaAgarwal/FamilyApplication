package com.wagawin.family.repository;

import com.wagawin.family.orm.ParentChildBackUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Ila Agarwal
 *
 */
@Repository
public interface ParentChildReposiroty extends CrudRepository<ParentChildBackUp,Long> {

	/**
	 * Count number of children for each Parent.This list contains the count for new children
	 * added after the PArentSummary was saved.
	 * @return List<Long[]> each element of an array contains parentId and count of Children
	 */
	@Query(value = "Select pcb.parentId , count(pcb.childId) from ParentChildBackUp pcb group by pcb.parentId")
	public List<Long[]>  countAllNewChildren();

}
