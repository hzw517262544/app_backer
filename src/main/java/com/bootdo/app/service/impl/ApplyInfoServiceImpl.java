package com.bootdo.app.service.impl;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.app.common.AppConstants;
import com.bootdo.app.domain.FlowDocDO;
import com.bootdo.app.service.FlowDocService;
import com.bootdo.common.service.DictService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
	@Resource
	private UserService userService;
	@Autowired
	TaskService taskService;
	@Autowired
	ActivitiUtils activitiUtils;
	
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
		applyInfo.setApplyStatus("1");
		applyInfoDao.save(applyInfo);
		return result;
	}
	
	@Override
	public int update(ApplyInfoDO applyInfo){
		return applyInfoDao.update(applyInfo);
	}
	
	@Override
	public int remove(String id){
		return applyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return applyInfoDao.batchRemove(ids);
	}

	@Override
	public void commit(ApplyInfoDO applyInfo) {
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
		//记录流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(AppConstants.APP_APLLY_ACTION_ID_3);
		flowDocDO.setHdlAction(AppConstants.APP_APLLY_ACTION_3);
		Map<String,Object> userPar = new HashMap<String,Object>();
		userPar.put("username",applyInfo.getUsername());
		List<UserDO> userDOS = userService.list(userPar);
		UserDO userDO;
		if(userDOS != null&&!userDOS.isEmpty()){
			userDO = userDOS.get(0);
		}else{
			userDO = new UserDO();
		}
		flowDocDO.setCreateUserId(userDO.getUserId()+"");
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(new Date());
		flowDocDO.setBusinessId(applyInfo.getId());
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(AppConstants.APP_APLLY_ACTION_3);
		flowDocService.save(flowDocDO);
	}

	/**
	 * 退回后提交
	 * @param applyInfo
	 */
	@Override
	public void backCommit(ApplyInfoDO applyInfo){
		applyInfo.setApplyStatus("2");
		applyInfoDao.update(applyInfo);
		//流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(AppConstants.APP_APLLY_ACTION_ID_3);
		flowDocDO.setHdlAction(AppConstants.APP_APLLY_ACTION_3);
		Map<String,Object> userPar = new HashMap<String,Object>();
		userPar.put("username",applyInfo.getUsername());
		List<UserDO> userDOS = userService.list(userPar);
		UserDO userDO;
		if(userDOS != null&&!userDOS.isEmpty()){
			userDO = userDOS.get(0);
		}else{
			userDO = new UserDO();
		}
		flowDocDO.setCreateUserId(userDO.getUserId()+"");
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(new Date());
		flowDocDO.setBusinessId(applyInfo.getId());
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(AppConstants.APP_APLLY_ACTION_3);
		flowDocService.save(flowDocDO);
		//工作流
		String taskKey = activitiUtils.getTaskByTaskId(applyInfo.getTaskId()).getTaskDefinitionKey();
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("assignee","app003");
		vars.put("pass","1");
		actTaskService.complete(applyInfo.getTaskId(),vars);
	}

	@Override
	public void cancel(ApplyInfoDO applyInfo) {
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(AppConstants.APP_APLLY_ACTION_ID_4);
		flowDocDO.setHdlAction(AppConstants.APP_APLLY_ACTION_4);
		Map<String,Object> userPar = new HashMap<String,Object>();
		userPar.put("username",applyInfo.getUsername());
		List<UserDO> userDOS = userService.list(userPar);
		UserDO userDO;
		if(userDOS != null&&!userDOS.isEmpty()){
			userDO = userDOS.get(0);
		}else{
			userDO = new UserDO();
		}
		flowDocDO.setCreateUserId(userDO.getUserId()+"");
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(new Date());
		flowDocDO.setBusinessId(applyInfo.getId());
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(AppConstants.APP_APLLY_ACTION_4);
		flowDocService.save(flowDocDO);

		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass","0");
		actTaskService.complete(applyInfo.getTaskId(),vars);
	}
}
