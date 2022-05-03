package com.wagawin.family.controller.batch;

import com.wagawin.family.orm.ParentChildBackUp;
import com.wagawin.family.orm.ParentSummary;
import com.wagawin.family.service.IChildService;
import com.wagawin.family.service.IParentChildService;
import com.wagawin.family.service.IParentSummaryService;
import com.wagawin.family.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @See Tasklet
 * ParentSummaryTasklet implements @{@link Tasklet} to perform the task of saving the new ParentSummary Entries.
 * Uses @{@link StepExecutionListener} to perform beforeStep and afterStep
 */
@Component
public class ParentSummaryTasklet  implements Tasklet, StepExecutionListener {

	@Autowired
	IParentChildService parentChildService;

	@Autowired
	IChildService childService;

	@Autowired
	IParentSummaryService parentSummaryService;

	@Autowired
	IPersonService personService;

	@Autowired
	CacheManager cacheManager;

	List<ParentChildBackUp> parentChildBackUp = new ArrayList<>();

	private final Logger logger = LoggerFactory
			.getLogger(ParentSummaryTasklet.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("ParentSummaryTasklet Started : "+new Date());
	}

	/**
	 * Clears the cache and deletes the data that was added in {@link ParentChildBackUp}
	 * @param stepExecution
	 * @return
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if(stepExecution.getExitStatus().getExitCode()=="COMPLETED") {
			parentChildService.deleteAll();

		}
		logger.info("ParentSummaryTasklet Ended : "+new Date());
		//Cache should be cleared
		clearSummaryCache();
		return stepExecution.getExitStatus();
	}

	/**
	 * Clears the SummaryCache
	 */
	private void clearSummaryCache(){
		cacheManager.getCache("summaryCache").clear();
	}

	/**
	 * It perform the following tasks
	 * 1. Reads the newly added children count for each parent added into @{@link ParentChildBackUp}
	 * 2. Reads Total(existing and new) number of children for each Parent(that are recently added)
	 * 3. For each Parent find the previous number of children - reduce its count from ParentSummary
	 * and then increase its count for new amoutOfChildren in ParentSummary
	 * @param stepContribution
	 * @param chunkContext
	 * @return
	 * @throws Exception
	 */
	@Override
	public RepeatStatus execute(StepContribution stepContribution,
								ChunkContext chunkContext) throws Exception {

		//find the newly added parent Child entries
		Map<Long, Long> newParentChildCount = parentChildService.countAllNewChildren().stream().collect(Collectors.toMap(o->o[0],o->o[1]));
		if(!newParentChildCount.isEmpty()) {
			Set<Long> parentIds = (Set<Long>) newParentChildCount.keySet();

			// find total number of children for all parentIds,Map<parentId, count(child)>
			Map<Long, Long> allParentChildCount = (Map<Long, Long>) childService.countChildrenByParent(parentIds).
					stream().collect(Collectors.toMap(o -> o[0], o -> o[1]));
			//Loop through all  affected parents
			for (Long parentId : allParentChildCount.keySet()) {
				Long totalChildren = allParentChildCount.get(parentId); //total children
				Long newlyAddedChilren = newParentChildCount.get(parentId); //newly added children
				Long childrenInSummary = totalChildren - newlyAddedChilren; //existing number of children in ParentSummary
				if(null!=childrenInSummary && childrenInSummary>0) {
					Optional<ParentSummary> oldParentSummaryOpt = parentSummaryService.findOptionalParentSummaryByAmountOfChildren(childrenInSummary);
					//find the old entry in parentSummary and reduce the count
					if (oldParentSummaryOpt.isPresent() && childrenInSummary > 0) {
						ParentSummary oldParentSummary = oldParentSummaryOpt.get();
						oldParentSummary.setAmountOfPersons(oldParentSummary.getAmountOfPersons() - 1);
						parentSummaryService.save(oldParentSummary);
					}
				}
				//update the new amountofparents count.
				if(null!=totalChildren && totalChildren>0) {
					Optional<ParentSummary> newParentSummaryOpt = parentSummaryService.findOptionalParentSummaryByAmountOfChildren(totalChildren);
					if (newParentSummaryOpt.isPresent()) {
						ParentSummary newParentSummary = newParentSummaryOpt.get();
						newParentSummary.setAmountOfPersons(newParentSummary.getAmountOfPersons() + 1);
						parentSummaryService.save(newParentSummary);
					} else {
						ParentSummary newParentSummary = new ParentSummary(totalChildren, 1L);
						parentSummaryService.save(newParentSummary);
					}
				}
			}

		}
		//save parents for 0 children
		saveParentWithoutChildren();

		return RepeatStatus.FINISHED;
	}

	/**
	 * add/update @{@link ParentSummary} for 0 Amount Of Children.
	 */
	private void saveParentWithoutChildren(){

		long totalParents = personService.countAllPerson();
		long summaryParentCount = parentSummaryService.findSumofAllParents();
		long parentsWithoutChildren = totalParents - summaryParentCount;
		Optional<ParentSummary> parentSummary = parentSummaryService.findOptionalParentSummaryByAmountOfChildren(0L);
		if(parentSummary.isPresent()) {
			parentSummary.get().setAmountOfPersons(parentsWithoutChildren);
			parentSummaryService.save(parentSummary.get());
		}
		else{
			parentSummaryService.save(new ParentSummary(0L, parentsWithoutChildren));
		}
	}

}