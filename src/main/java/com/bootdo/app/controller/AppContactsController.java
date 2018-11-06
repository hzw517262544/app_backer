package com.bootdo.app.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.oa.service.NotifyService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
@Controller
@RequestMapping("/app/contacts")
public class AppContactsController extends BaseController {
	@Resource
	DeptService deptService;

	@ResponseBody
	@GetMapping("/getDepts")
	R getDept(){
		List<DeptDO> deptDOS = deptService.list(new HashMap<String,Object>());
		return R.ok().put("depts",deptDOS);
	}

}
