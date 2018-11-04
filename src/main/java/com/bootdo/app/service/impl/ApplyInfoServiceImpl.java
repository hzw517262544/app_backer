package com.bootdo.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.app.dao.ApplyInfoDao;
import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.service.ApplyInfoService;



@Service
public class ApplyInfoServiceImpl implements ApplyInfoService {
	@Autowired
	private ApplyInfoDao applyInfoDao;
	
	@Override
	public ApplyInfoDO get(Long id){
		return applyInfoDao.get(id);
	}
	
	@Override
	public List<ApplyInfoDO> list(Map<String, Object> map){
		return applyInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return applyInfoDao.count(map);
	}
	
	@Override
	public int save(ApplyInfoDO applyInfo){
		return applyInfoDao.save(applyInfo);
	}
	
	@Override
	public int update(ApplyInfoDO applyInfo){
		return applyInfoDao.update(applyInfo);
	}
	
	@Override
	public int remove(Long id){
		return applyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return applyInfoDao.batchRemove(ids);
	}
	
}
