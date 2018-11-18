package com.bootdo.app.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.app.service.FlowDocService;
import com.bootdo.common.service.DictService;
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
	@Resource
	private DictService dictService;
	@Resource
	private FlowDocService flowDocService;
	
	@Override
	public ApplyInfoDO get(String id){
		ApplyInfoDO applyInfoDO = applyInfoDao.get(id);
		if(applyInfoDO == null){
			return null;
		}
		applyInfoDO.setApplyStatusName(dictService.getName("APP_LEAVE_APLLY_STATUS",applyInfoDO.getApplyStatus()));
		Map<String,Object> flowDocMap = new HashMap<String,Object>();
		flowDocMap.put("businessId",id);
		flowDocMap.put("businessType","1");
		applyInfoDO.setFlowDocs(flowDocService.list(flowDocMap));
		return applyInfoDO;
	}
	
	@Override
	public List<ApplyInfoDO> list(Map<String, Object> map){
		List<ApplyInfoDO> list = applyInfoDao.list(map);
		if(list != null&&!list.isEmpty()){
			for(ApplyInfoDO applyInfoDO : list){
				applyInfoDO.setApplyStatusName(dictService.getName("APP_LEAVE_APLLY_STATUS",applyInfoDO.getApplyStatus()));
			}
		}
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return applyInfoDao.count(map);
	}
	
	@Override
	public String save(ApplyInfoDO applyInfo){
		String result = UUID.randomUUID().toString();
		applyInfo.setId(result);
		applyInfo.setApplyStatus("2");
		applyInfoDao.save(applyInfo);
		Map<String,Object> var = new HashMap<String,Object>();
        var.put("userId",applyInfo.getUsername());
        var.put("applyer",applyInfo.getUsername());
		String processInstId = actTaskService.startAppProcess(ActivitiConstant.ACTIVITI_LEAVE_APPLY[0],ActivitiConstant.ACTIVITI_LEAVE_APPLY[1],applyInfo.getId()+"","请假申请流程",var);
		var.put("assignee","app003");
		var.put("pass","1");
		actTaskService.completeByProInsId(processInstId,var);
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
