package com.bootdo.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.app.common.AppConstants;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.app.domain.TopicDO;
import com.bootdo.app.service.TopicService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-20 21:23:23
 */
 
@Controller
@RequestMapping("/app/topic")
public class AppTopicController extends BaseController {
	@Autowired
	private TopicService topicService;
	@Autowired
	private DictService dictService;

	@GetMapping()
//	@RequiresPermissions("app:topic:topic")
	String Topic(){
		return "app/topic/topic";
	}
	
	@GetMapping("/fromApply/{id}")
//	@RequiresPermissions("app:topic:topic")
	String fromApply(@PathVariable("id") String id,Model model){
		model.addAttribute("applyId",id);
	    return "app/topic/topic";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("app:topic:topic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TopicDO> topicList = topicService.list(query);
		int total = topicService.count(query);
		PageUtils pageUtils = new PageUtils(topicList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{applyId}")
//	@RequiresPermissions("app:topic:add")
	String add(@PathVariable("applyId") String id,Model model){
		model.addAttribute("applyId",id);
	    return "app/topic/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("app:topic:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TopicDO topic = topicService.get(id);
		model.addAttribute("topic", topic);
		List<DictDO> mediaTopicType = dictService.listByType(AppConstants.APP_MEDIA_TOPIC_TYPE);
		for (DictDO dictDO:mediaTopicType){
			if(topic.getStandby1().equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("topicType", mediaTopicType);
	    return "app/topic/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:topic:add")
	public R save( TopicDO topic){
		Date curDate = DateUtils.getCurTimestamp();
		topic.setCreateTime(curDate);
		topic.setUpdateTime(curDate);
		UserDO userDO = getUser();
		if(userDO != null){
			topic.setCreateUser(userDO.getUsername());
			topic.setUpdateUser(userDO.getUsername());
		}
		if(topicService.save(topic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("app:topic:edit")
	public R update( TopicDO topic){
		Date curDate = DateUtils.getCurTimestamp();
		topic.setUpdateTime(curDate);
		UserDO userDO = getUser();
		if(userDO != null){
			topic.setUpdateUser(userDO.getUsername());
		}
		topicService.update(topic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("app:topic:remove")
	public R remove( Long id){
		if(topicService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("app:topic:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		topicService.batchRemove(ids);
		return R.ok();
	}
	
}
