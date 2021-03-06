package com.bootdo.app.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserService;
import com.bootdo.system.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class LoginAppController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private DeptService deptService;
	@Resource
	private FileService fileService;
	@Resource
	private DictService dictService;

	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password,String cid) {
		password = MD5Utils.encrypt(username, password);
		Map<String,Object> userMap = new HashMap<String,Object>();
		userMap.put("username",username);
		userMap.put("password",password);
		if(userService.exit(userMap)){
			R r = R.ok();
			List<UserDO> userDOS = userService.list(userMap);
			UserDO userDO = userDOS.get(0);
			userDO.setCid(cid);
			//更新用户客户端id
			userService.update(userDO);
			userMap.put("userId",userDO.getUserId());
			List<UserRoleDO> userRoleDOS = userService.listUserRole(userMap);
			String userRoleNames = "";
			String roleSigns = "";
			for(UserRoleDO userRoleDO : userRoleDOS){
				RoleDO roleDO = roleService.get(userRoleDO.getRoleId());
				userRoleNames += roleDO.getRoleName();
				roleSigns += roleDO.getRoleSign();
			}
			userDO.setRoleSign(roleSigns);
			userDO.setRoleNames(userRoleNames);
			DeptDO deptDO = deptService.get(userDO.getDeptId());
			userDO.setDeptName(deptDO.getName());
			FileDO fileDO = fileService.get(userDO.getPicId());
			if(fileDO!=null){
				userDO.setPicUrl(fileDO.getUrl());
			}
			r.put("userInfo",userDO);
			return r;
		}else{
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	@ResponseBody
	R logout() {
		ShiroUtils.logout();
		return R.ok();
	}
	@ResponseBody
	@GetMapping("/getUserImg")
	R getUserImg(String username){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username",username);
		List<UserDO> userDOS = userService.list(map);
		FileDO fileDO = fileService.get(userDOS.get(0).getPicId());
		if(fileDO != null){
			R r = R.ok();
			r.put("img",fileDO);
			return r;
		}
		return R.error();
	}

	@ResponseBody
	@PostMapping("/restAppPwd")
	R restAppPwd(Long userId,String password){
		UserDO userDO = userService.get(userId);
		UserVO userVO = new UserVO();
		userVO.setUserDO(userDO);
		userVO.setPwdNew(password);
		try {
			userService.adminResetPwd(userVO);
		}catch (Exception e){
			e.printStackTrace();
			return R.error();
		}
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/loadDict")
	R loadDict(String type){
		List<DictDO> dictDOS = dictService.listByType(type);
		return R.ok().put("dictType",dictDOS);
	}

}
