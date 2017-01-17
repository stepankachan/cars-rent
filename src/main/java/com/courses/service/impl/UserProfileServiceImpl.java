package com.courses.service.impl;

import com.courses.dao.UserProfileDao;
import com.courses.model.UserRole;
import com.courses.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileDao dao;

	@Transactional
	public UserRole findById(int id) {
		return dao.findById(id);
	}

	public UserRole findByType(String type){
		return dao.findByType(type);
	}

	public List<UserRole> findAll() {
		return dao.findAll();
	}
}
