package com.bootdo.app.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.app.dao.ApplyInfoDao;
import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.service.ApplyInfoService;

import javax.annotation.Resource;


@Service
public class ApplyInfoServiceImpl implements ApplyInfoService {
	@Resource
	private ApplyInfoDao applyInfoDao;

	@Resource
	private ActTaskServiceImpl actTaskService;
	
	@Override
	public ApplyInfoDO get(String id){
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
	public String save(ApplyInfoDO applyInfo){
		String result = UUID.randomUUID().toString();
		applyInfo.setId(result);
		applyInfoDao.save(applyInfo);
		Map<String,Object> var = new HashMap<String,Object>();
        var.put("userId",applyInfo.getUsername());
		actTaskService.startAppProcess(ActivitiConstant.ACTIVITI_LEAVE_APPLY[0],ActivitiConstant.ACTIVITI_LEAVE_APPLY[1],applyInfo.getId()+"","请假申请流程",var);
		return result;
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
