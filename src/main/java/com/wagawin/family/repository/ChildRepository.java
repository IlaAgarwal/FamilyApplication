package com.wagawin.family.repository;

import com.wagawin.family.orm.Child;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ChildRepository extends CrudRepository<Child,Long> {

	@Query(value="SELECT c from Child c join fetch c.parent p left join fetch c.meals m where c.id=:id ")
	public Optional<Child> findChildByID(@Param("id") Long id);

	@Query(value="SELECT c from Child c where c.id=:id")
	public Optional<Child> findOptionalColorByID(@Param("id") Long id);

	@Query(value = "Select c.parent.id , COUNT(c.id) from Child c group by c.parent having c.parent.id in :parentIds")
	public List<Long[]> countChildrenByParent(@Param("parentIds") Set<Long> parentIds);
}
