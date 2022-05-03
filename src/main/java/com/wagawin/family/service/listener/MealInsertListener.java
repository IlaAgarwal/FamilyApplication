package com.wagawin.family.service.listener;

import com.wagawin.family.orm.Meal;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;

/**
 * Listener to check if a new meal is added
 */
@Component
@NoArgsConstructor
public class MealInsertListener {

	@Autowired
	CacheManager cacheManager;

	/**
	 * Invalidates the child-info cache as it contains the favorite meal,
	 * Favorite meal might be updated after the meal is added.
	 * @param meal
	 */
	@PostPersist
	private void removerCache(Meal meal){
		meal.getChildren().stream().forEach(child->cacheManager.getCache("child-info").evict(child.getId()));
	}

}
