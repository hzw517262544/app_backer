package com.bootdo.app.controller;

import com.bootdo.app.service.ApplyInfoService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.domain.NotifyRecordDO;
import com.bootdo.oa.service.NotifyRecordService;
import com.bootdo.oa.service.NotifyService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-04 17:17:32
 */
@Controller
@RequestMapping("/app/message")
public class AppMessageController extends BaseController {
	@Autowired
	private NotifyRecordService notifyRecordService;
	@Autowired
	ApplyInfoService applyInfoService;

	@ResponseBody
	@GetMapping("/list")
	Map<String, Object> getMessages(Long userId,String username){
		Map<String,Object> result = new HashMap<String,Object>();
		//查询未读公告
		Map<String,Object> parMap = new HashMap<String,Object>();
		parMap.put("userId",userId);
		parMap.put("isRead","0");
		int notifyCount = notifyRecordService.count(parMap);
		result.put("notifyCount",notifyCount);
		parMap.clear();
		parMap.put("currentHandlerUserName",username);
		int todoCount = applyInfoService.count(parMap);
		result.put("todoCount",todoCount);
		return result;
	}

}
