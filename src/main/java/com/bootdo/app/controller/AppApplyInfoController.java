package com.bootdo.app.controller;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.app.common.AppConstants;
import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.domain.FlowDocDO;
import com.bootdo.app.service.ApplyInfoService;
import com.bootdo.app.service.FlowDocService;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
 
@Controller
@RequestMapping("/app/applyInfo")
public class AppApplyInfoController {
	@Autowired
	private ApplyInfoService applyInfoService;

	@Autowired
	ActivitiUtils activitiUtils;

	@Autowired
	TaskService taskService;

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private FlowDocService flowDocService;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;
	
	@GetMapping()
//	@RequiresPermissions("app:applyInfo:applyInfo")
	String ApplyInfo(){
	    return "app/applyInfo/applyInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("app:applyInfo:applyInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ApplyInfoDO> applyInfoList = applyInfoService.list(query);
		int total = applyInfoService.count(query);
		PageUtils pageUtils = new PageUtils(applyInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("app:applyInfo:add")
	String add(){
	    return "app/applyInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("app:applyInfo:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApplyInfoDO applyInfo = applyInfoService.get(id);
		model.addAttribute("applyInfo", applyInfo);
	    return "app/applyInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:applyInfo:add")
	public R save( ApplyInfoDO applyInfo){
		applyInfo.setCreateTime(DateUtils.getCurTimestamp());
		applyInfo.setCreateUser(applyInfo.getUsername());
		if(applyInfoService.save(applyInfo) != null){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("app:applyInfo:edit")
	public R update( ApplyInfoDO applyInfo){
		applyInfoService.update(applyInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("app:applyInfo:remove")
	public R remove( Long id){
		if(applyInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("app:applyInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		applyInfoService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/get")
//	@RequiresPermissions("app:applyInfo:edit")
	public R get( String id){
		ApplyInfoDO applyInfoDO = applyInfoService.get(id);
		return R.ok().put("applyInfo",applyInfoDO);
	}
	@ResponseBody
	@GetMapping("/todoList")
	List<ApplyInfoDO> todoList(String username){
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
		List<ApplyInfoDO> applyInfoDOS =  new ArrayList<>();
		for(Task task : tasks){
			if(task.getProcessDefinitionId().contains(ActivitiConstant.ACTIVITI_LEAVE_APPLY_ID)){
				ApplyInfoDO applyInfoDO = applyInfoService.get(activitiUtils.getBusinessKeyByTaskId(task.getId()));
				applyInfoDO.setTaskVO(new TaskVO(task));
				applyInfoDOS.add(applyInfoDO);
			}
		}
		return applyInfoDOS;
	}

	@ResponseBody
	@PostMapping("/apply")
	R apply(String taskId,String auditOpinion,String passFlag,String applyId,Long userId){
		String taskKey = activitiUtils.getTaskByTaskId(taskId).getTaskDefinitionKey();
		Map<String,Object> vars = new HashMap<>(16);
		if("audit_1".equals(taskKey)){
			vars.put("assignee","app004");
		}else if("audit_2".equals(taskKey)){

		}
		vars.put("pass",passFlag);
		vars.put("auditOpinion",auditOpinion);
		actTaskService.complete(taskId,vars);
		//新增流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(passFlag);
		flowDocDO.setHdlAction(dictService.getName(AppConstants.APP_APLLY_ACTION_ID,passFlag));
		UserDO userDO = userService.get(userId);
		flowDocDO.setCreateUserId(userId+"");
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(new Date());
		flowDocDO.setBusinessId(applyId);
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(auditOpinion);
		flowDocService.save(flowDocDO);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/getByTaskId")
//	@RequiresPermissions("app:applyInfo:edit")
	public R getByTaskId( String taskId){
		ApplyInfoDO applyInfoDO = applyInfoService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
		return R.ok().put("applyInfo",applyInfoDO);
	}
	
}
