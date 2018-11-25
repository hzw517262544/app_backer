package com.bootdo.app.controller;

import com.bootdo.activiti.config.ActivitiConstant;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.app.common.AppConstants;
import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.domain.FlowDocDO;
import com.bootdo.app.domain.TopicDO;
import com.bootdo.app.service.ApplyInfoService;
import com.bootdo.app.service.FlowDocService;
import com.bootdo.app.service.TopicService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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
public class AppApplyInfoController extends BaseController {
	@Autowired
	private ApplyInfoService applyInfoService;

	@Autowired
	ActivitiUtils activitiUtils;

	@Autowired
	TaskService taskService;

	@Autowired
	private ActTaskServiceImpl actTaskService;

	@Autowired
	private FlowDocService flowDocService;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private TopicService topicService;
	@Autowired
	private RoleService roleService;

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
		List<DictDO> mediaApllyType = dictService.listByType(AppConstants.APP_MEDIA_APLLY_TYPE);
		for (DictDO dictDO:mediaApllyType){
			if(applyInfo.getApplyType().equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		List<DictDO> mediaApllyTypeSecond = dictService.listByType(AppConstants.APP_MEDIA_APLLY_TYPE_SECOND);
		for (DictDO dictDO:mediaApllyTypeSecond){
			if(applyInfo.getApplySecodType().equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("applyInfo", applyInfo);
		model.addAttribute("mediaApllyType", mediaApllyType);
		model.addAttribute("mediaApllyTypeSecond", mediaApllyTypeSecond);
	    return "app/applyInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:applyInfo:add")
	public R save( ApplyInfoDO applyInfo){
		UserDO userDO = getUser();
		Date date = DateUtils.getCurTimestamp();
		applyInfo.setApplyStatus(AppConstants.APP_LEAVE_APLLY_STATUS_1);
		if(userDO != null){
			applyInfo.setUsername(userDO.getUsername());
			applyInfo.setCreateUser(userDO.getUsername());
			applyInfo.setUpdateUser(userDO.getUsername());
			applyInfo.setName(userDO.getName());
			applyInfo.setCurrentHandlerUserName(userDO.getUsername());
			applyInfo.setCurrentHandlerName(userDO.getName());
		}else{
			applyInfo.setCreateUser(applyInfo.getUsername());
			applyInfo.setUsername(applyInfo.getUsername());
			applyInfo.setUpdateUser(applyInfo.getUsername());
			applyInfo.setName(applyInfo.getName());
			applyInfo.setCurrentHandlerUserName(applyInfo.getUsername());
			applyInfo.setCurrentHandlerName(applyInfo.getName());
		}
		applyInfo.setCreateTime(date);
		applyInfo.setUpdateTime(date);
		if(applyInfoService.save(applyInfo) != null){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 客户端提交
	 */
	@ResponseBody
	@PostMapping("/commit")
//	@RequiresPermissions("app:applyInfo:add")
	public R commit( String id){
		ApplyInfoDO applyInfo = applyInfoService.get(id);
		String messages = applyInfoService.commit(applyInfo);
		if(StringUtils.isEmpty(messages)){
			return R.ok();
		}else{
			return R.error().put("msg",messages);
		}
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
	public R remove( String id){
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
	public R remove(@RequestParam("ids[]") String[] ids){
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
	public List<ApplyInfoDO> todoList(String username,String applyType,String applySecodType){
		Map<String,Object> parMap = new HashMap<String,Object>(16);
		parMap.put("currentHandlerUserName",username);
		parMap.put("applyType",applyType);
		parMap.put("applySecodType",applySecodType);
		return applyInfoService.list(parMap);
	}

	/**
	 * 同意
	 * @param applyId
	 * @param auditOpinion
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@PostMapping("/pass")
	public R pass(String applyId,String auditOpinion,String userName){
		Map<String,Object> userMap = new HashMap<String,Object>(16);
		userMap.put("username",userName);
		List<UserDO> userList = userService.list(userMap);
		UserDO userDO = null;
		if(userList!=null&&!userList.isEmpty()){
			userDO = userList.get(0);
		}else{
			return R.error().put("msg","系统没有查询到当前用户信息，请联系管理员");
		}
		//查询当前用户的角色信息，责编通过到总编，总编通过则流程结束
		List<RoleDO> roles = roleService.listByUserId(userDO.getUserId());
		ApplyInfoDO applyInfoDO = applyInfoService.get(applyId);
		String actionId = "";
		String actionName = "";
		if(roles != null&&!roles.isEmpty()){
			for(RoleDO roleDO : roles){
				if(AppConstants.ROLE_DUTY_EDITOR_WX.equals(roleDO.getRoleSign())
				||AppConstants.ROLE_DUTY_EDITOR_WB.equals(roleDO.getRoleSign())){
					Map<String,Object> roleSign = new HashMap<>(16);
					roleSign.put("roleSign",AppConstants.ROLE_PRESIDENT_EDITOR);
					List<UserDO> presidentEditors = userService.listByRoleSign(roleSign);
					if(presidentEditors==null||presidentEditors.isEmpty()){
						return R.error().put("msg","系统没有找到总编对应的用户，请联系管理员");
					}
					applyInfoDO.setCurrentHandlerUserName(presidentEditors.get(0).getUsername());
					applyInfoDO.setCurrentHandlerName(presidentEditors.get(0).getName());
					actionId = AppConstants.APP_APLLY_ACTION_ID_2;
					actionName = AppConstants.APP_APLLY_ACTION_2;
				}else if(AppConstants.ROLE_PRESIDENT_EDITOR.equals(roleDO.getRoleSign())){
					applyInfoDO.setApplyStatus(AppConstants.APP_LEAVE_APLLY_STATUS_4);
					applyInfoDO.setApplyStatusName(AppConstants.APP_LEAVE_APLLY_STATUS_NAME_4);
					applyInfoDO.setCurrentHandlerUserName("");
					applyInfoDO.setCurrentHandlerName("");
					actionId = AppConstants.APP_APLLY_ACTION_ID_4;
					actionName = AppConstants.APP_APLLY_ACTION_4;
				}
			}
			applyInfoDO.setUpdateUser(userName);
			applyInfoDO.setUpdateTime(DateUtils.getCurTimestamp());
			applyInfoService.update(applyInfoDO);
		}else{
			return R.error().put("msg","系统没有查询到当前用户的角色信息，请联系管理员");
		}
		//新增流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(actionId);
		flowDocDO.setHdlAction(actionName);
		flowDocDO.setCreateUserId(userDO.getUsername());
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(DateUtils.getCurTimestamp());
		flowDocDO.setBusinessId(applyId);
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(auditOpinion);
		flowDocService.save(flowDocDO);
		return R.ok();
	}

	/**
	 * 同意
	 * @param applyId
	 * @param auditOpinion
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@PostMapping("/unPass")
	public R unPass(String applyId,String auditOpinion,String userName){
		Map<String,Object> userMap = new HashMap<String,Object>(16);
		userMap.put("username",userName);
		List<UserDO> userList = userService.list(userMap);
		UserDO userDO = null;
		if(userList!=null&&!userList.isEmpty()){
			userDO = userList.get(0);
		}else{
			return R.error().put("msg","系统没有查询到当前用户信息，请联系管理员");
		}
		//不同意直接将申请退回到申请人
		List<RoleDO> roles = roleService.listByUserId(userDO.getUserId());
		ApplyInfoDO applyInfoDO = applyInfoService.get(applyId);
		String actionId = "";
		String actionName = "";
		if(roles != null&&!roles.isEmpty()){
			for(RoleDO roleDO : roles){
				if(AppConstants.ROLE_DUTY_EDITOR_WX.equals(roleDO.getRoleSign())
						||AppConstants.ROLE_DUTY_EDITOR_WB.equals(roleDO.getRoleSign())){
					actionId = AppConstants.APP_APLLY_ACTION_ID_3;
					actionName = AppConstants.APP_APLLY_ACTION_3;
				}else if(AppConstants.ROLE_PRESIDENT_EDITOR.equals(roleDO.getRoleSign())){
					actionId = AppConstants.APP_APLLY_ACTION_ID_5;
					actionName = AppConstants.APP_APLLY_ACTION_5;
				}
			}
			applyInfoDO.setUpdateUser(userName);
			applyInfoDO.setUpdateTime(DateUtils.getCurTimestamp());
			applyInfoService.update(applyInfoDO);
		}else{
			return R.error().put("msg","系统没有查询到当前用户的角色信息，请联系管理员");
		}
		applyInfoDO.setApplyStatus(AppConstants.APP_LEAVE_APLLY_STATUS_3);
		applyInfoDO.setApplyStatusName(AppConstants.APP_LEAVE_APLLY_STATUS_NAME_3);
		applyInfoDO.setUpdateUser(userName);
		applyInfoDO.setUpdateTime(DateUtils.getCurTimestamp());
		applyInfoDO.setCurrentHandlerName(applyInfoDO.getName());
		applyInfoDO.setCurrentHandlerUserName(applyInfoDO.getUsername());
		applyInfoService.update(applyInfoDO);
		//新增流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(actionId);
		flowDocDO.setHdlAction(actionName);
		flowDocDO.setCreateUserId(userDO.getUsername());
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(DateUtils.getCurTimestamp());
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

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/backCommit")
	public R backCommit( ApplyInfoDO applyInfo){
		applyInfoService.backCommit(applyInfo);
		return R.ok();
	}

	/**
	 * 取消流程
	 * @param applyInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancel")
	public R cancel( ApplyInfoDO applyInfo){
		applyInfoService.cancel(applyInfo);
		return R.ok();
	}

	/**
	 * app微博提交
	 */
	@ResponseBody
	@PostMapping("/commitWeibo")
//	@RequiresPermissions("app:applyInfo:add")
	public R commitWeibo( HttpServletRequest request){
		String applyContent = request.getParameter("applyContent");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String applyType = request.getParameter("applyType");
		String applyTypeName = request.getParameter("applyTypeName");
		String applySecodType = request.getParameter("applySecodType");
		String applySecodTypeName = request.getParameter("applySecodTypeName");
		Map<String,Object> userPar = new HashMap<String,Object>();
		userPar.put("username",username);
		List<UserDO> userDOS = userService.list(userPar);
		UserDO userDO;
		if(userDOS != null&&!userDOS.isEmpty()){
			userDO = userDOS.get(0);
		}else{
			return R.error().put("msg","系统没有查询到当前用户的信息，请联系系统管理员");
		}
		//根据角色信息找审批人，然后更新到申请的当前处理人-提交找责编
		Map<String,Object> roleSign = new HashMap<>(16);
		roleSign.put("roleSign",AppConstants.ROLE_DUTY_EDITOR_WB);
		List<UserDO> dutyEditors = userService.listByRoleSign(roleSign);
		if(dutyEditors==null||dutyEditors.isEmpty()){
			return R.error().put("msg","系统没有找到责编对应的用户，请联系管理员");
		}
		Date date = DateUtils.getCurTimestamp();
		//生成微博申请信息
		ApplyInfoDO applyInfoDO = new ApplyInfoDO();
		applyInfoDO.setApplyStatus(AppConstants.APP_LEAVE_APLLY_STATUS_2);
		applyInfoDO.setApplyStatusName(AppConstants.APP_LEAVE_APLLY_STATUS_NAME_2);
		applyInfoDO.setApplyType(applyType);
		applyInfoDO.setApplyTypeName(applyTypeName);
		applyInfoDO.setApplySecodType(applySecodType);
		applyInfoDO.setApplySecodTypeName(applySecodTypeName);
		applyInfoDO.setUsername(userDO.getUsername());
		applyInfoDO.setName(userDO.getName());
		applyInfoDO.setCreateUser(userDO.getUsername());
		applyInfoDO.setCreateTime(date);
		applyInfoDO.setUpdateUser(userDO.getUsername());
		applyInfoDO.setUpdateTime(date);
		applyInfoDO.setApplyContent(applyContent);
		//微博提交查询责编信息
		applyInfoDO.setCurrentHandlerUserName(dutyEditors.get(0).getUsername());
		applyInfoDO.setCurrentHandlerName(dutyEditors.get(0).getName());
		applyInfoService.save(applyInfoDO);
		// 复杂类型的request对象
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		// 获取文件名集合放入迭代器
		Iterator<String> files = mRequest.getFileNames();
		while (files.hasNext()) {
			//获取上传文件的对象
			MultipartFile mFile = mRequest.getFile(files.next());
			if(mFile != null){
				String fileName = mFile.getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
				sysFile.setName(mFile.getOriginalFilename());
				try {
					FileUtil.uploadFile(mFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
				} catch (Exception e) {
					continue;
				}
				if (fileService.save(sysFile) > 0) {
					//生成素材
					TopicDO topicDO = new TopicDO();
					topicDO.setApplyId(applyInfoDO.getId());
					topicDO.setStandby1(AppConstants.APP_MEDIA_TOPIC_TYPE_2);
					topicDO.setTopicType(AppConstants.APP_MEDIA_TOPIC_TYPE_NAME_2);
					topicDO.setTopicName(sysFile.getName());
					topicDO.setTopicUrl(sysFile.getUrl());
					topicDO.setCreateUser(userDO.getUsername());
					topicDO.setCreateTime(date);
					topicDO.setUpdateUser(userDO.getUsername());
					topicDO.setUpdateTime(date);
					topicService.save(topicDO);
//					return R.ok();
				}
			}
		}
		//记录流转记录
		FlowDocDO flowDocDO = new FlowDocDO();
		flowDocDO.setHdlActionId(AppConstants.APP_APLLY_ACTION_ID_3);
		flowDocDO.setHdlAction(AppConstants.APP_APLLY_ACTION_3);
		flowDocDO.setCreateUserId(userDO.getUsername());
		flowDocDO.setCreateUserName(userDO.getName());
		flowDocDO.setCreateTime(date);
		flowDocDO.setBusinessId(applyInfoDO.getId());
		flowDocDO.setBusinessType(AppConstants.BUSINESS_TYPE_APPLY);
		flowDocDO.setHdlContent(AppConstants.APP_APLLY_ACTION_3);
		flowDocService.save(flowDocDO);
		return R.ok();
	}
}
