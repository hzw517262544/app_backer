package com.bootdo.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.app.dao.WorkDao;
import com.bootdo.app.domain.WorkDO;
import com.bootdo.app.service.WorkService;



@Service
public class WorkServiceImpl implements WorkService {
	@Autowired
	private WorkDao workDao;
	
	@Override
	public WorkDO get(Long id){
		return workDao.get(id);
	}
	
	@Override
	public List<WorkDO> list(Map<String, Object> map){
		return workDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return workDao.count(map);
	}
	
	@Override
	public int save(WorkDO work){
		return workDao.save(work);
	}
	
	@Override
	public int update(WorkDO work){
		return workDao.update(work);
	}
	
	@Override
	public int remove(Long id){
		return workDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return workDao.batchRemove(ids);
	}
	
}
