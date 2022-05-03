package com.wagawin.family.service;

import com.wagawin.family.orm.Child;
import com.wagawin.family.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChildService implements IChildService{

	private final ChildRepository childRepository;
	@Autowired
	CacheManager cacheManager;
	/**
	 * find the child/parent/meal(if exists) by Id, return the cache, if exist
	 * @param id
	 * @return Optional<Child>
	 */
	@Cacheable(value = "childInfoCache" , key="#id",unless = "#result == null")
	@Override
	@Transactional(readOnly = true)
	public Optional<Child> findChildByID(Long id){
		return childRepository.findChildByID(id);
	}

	/**
	 * find the child by Id, return the cache, if exist
	 * @param id
	 * @return Optional<Child>
	 */
	@Cacheable(value = "childColorCache" , key = "#id", unless = "#result == null")
	@Override
	@Transactional(readOnly = true)
	public Optional<Child> findOptionalColorByID(Long id){
		 if(null!=cacheManager.getCache("childInfoCache").get(id)) {
			Child child = (Child) cacheManager.getCache("childInfoCache").get(id).get();
			 return Optional.ofNullable(child);
		 }
		return childRepository.findOptionalColorByID(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long[]> countChildrenByParent(@Param("parentIds") Set<Long> parentIds){
		return childRepository.countChildrenByParent(parentIds);
	}
}
