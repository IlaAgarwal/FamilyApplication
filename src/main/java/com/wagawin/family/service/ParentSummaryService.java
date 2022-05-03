package com.wagawin.family.service;

import com.wagawin.family.orm.ParentSummary;
import com.wagawin.family.repository.ParentSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ParentSummaryService implements  IParentSummaryService {


	private final ParentSummaryRepository parentSummaryRepository;

	private final ChildService childService;

	private final CacheManager cacheManager;


	/**
	 * Used for part3, to return the @{@link ParentSummary} Array.
	 * where nth element represents the number of parents have n amountOfChildren
	 * @return
	 */
	@Cacheable(value = "summaryCache",unless = "#result == null")
	@Transactional(readOnly = true)
	@Override
	public int[] getParentSummary() {

		List<ParentSummary> psList = findAll();

		Optional<Integer> maxChildren = findMaxChildrenSize();
		if(maxChildren.isPresent()) {
			int[] parentSummary = new int[maxChildren.get() + 1];
			//add nth element to parentSummary array
			psList.stream().forEach(ps -> parentSummary[Math.toIntExact(ps.getAmountOfChildren())] = Math.toIntExact(ps.getAmountOfPersons()));
			return parentSummary;
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Integer> findMaxChildrenSize() {
		return parentSummaryRepository.findOptionalMaxChildrenSize();
	}

	/**
	 * Finds the total number of parents in SUmmary.
	 * @return long the total number of Parents
	 */
	@Override
	@Transactional(readOnly = true)
	public long findSumofAllParents() {
		Optional<Long> sum = parentSummaryRepository.findOptionalSumofAllParents();
		return sum.orElse(0L);
	}

	/**
	 * returns all entries from Parent Summary
	 * @return
	 */
 	@Override
	@Transactional(readOnly = true)
	public List<ParentSummary> findAll() {
		return parentSummaryRepository.findAll();
	}

	/**
	 * Return the amount of parents for given amount of children
	 * @param amountOfChildren
	 * @return ParentSummary
	 */
	@Override
	public Optional<ParentSummary> findOptionalParentSummaryByAmountOfChildren(Long amountOfChildren) {
		return parentSummaryRepository.findOptionalParentSummaryByAmountOfChildren(amountOfChildren);
	}

	/**
	 * Saves the @{@link ParentSummary}
	 * @param parentSummary
	 * @return
	 */
	@Override
	public ParentSummary save(ParentSummary parentSummary) {
		return parentSummaryRepository.save(parentSummary);
	}


/*	@Transactional
	@Override
	public void processParentSummary() {

		Map<Long, Long> parentChildCountMap = childService.countChildByParent().stream()
				.collect(toMap(e -> (Long) e[0], e -> (Long) e[1]));
		Map<Long, Long> parenSummaryMap = new HashMap<>();
		for(Map.Entry<Long,Long> entry: parenSummaryMap.entrySet()){
			Long existingEntry = parenSummaryMap.get(entry.getValue());
			if(null==existingEntry){
				parenSummaryMap.put(entry.getValue(), entry.getKey());
			}
			else{
				parenSummaryMap.put(entry.getValue(),existingEntry+1);
			}
		}

		List<ParentSummary> parentSummaryList =parenSummaryMap.entrySet()
				.stream()
				.map(e -> new ParentSummary(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		parentSummaryRepository.saveAll(parentSummaryList);
	}*/

}
