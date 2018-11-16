package com.bootdo.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.app.dao.FlowDocDao;
import com.bootdo.app.domain.FlowDocDO;
import com.bootdo.app.service.FlowDocService;



@Service
public class FlowDocServiceImpl implements FlowDocService {
	@Autowired
	private FlowDocDao flowDocDao;
	
	@Override
	public FlowDocDO get(Long id){
		return flowDocDao.get(id);
	}
	
	@Override
	public List<FlowDocDO> list(Map<String, Object> map){
		return flowDocDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return flowDocDao.count(map);
	}
	
	@Override
	public int save(FlowDocDO flowDoc){
		return flowDocDao.save(flowDoc);
	}
	
	@Override
	public int update(FlowDocDO flowDoc){
		return flowDocDao.update(flowDoc);
	}
	
	@Override
	public int remove(Long id){
		return flowDocDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return flowDocDao.batchRemove(ids);
	}
	
}
