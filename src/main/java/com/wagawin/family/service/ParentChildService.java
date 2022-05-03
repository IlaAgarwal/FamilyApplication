package com.wagawin.family.service;

import com.wagawin.family.repository.ParentChildReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParentChildService implements IParentChildService{

	@Autowired
	ParentChildReposiroty parentChildReposiroty;

	@Override
	@Transactional(readOnly = true)
	public List<Long[]> countAllNewChildren(){
		return parentChildReposiroty.countAllNewChildren();
	}

	@Override
	@Transactional
	public void deleteAll(){
		parentChildReposiroty.deleteAll() ;
	}
}
