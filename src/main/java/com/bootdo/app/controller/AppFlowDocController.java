package com.bootdo.app.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.app.domain.FlowDocDO;
import com.bootdo.app.service.FlowDocService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 流程流转记录表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-16 10:56:15
 */
 
@Controller
@RequestMapping("/app/flowDoc")
public class AppFlowDocController {
	@Autowired
	private FlowDocService flowDocService;
	
	@GetMapping()
	@RequiresPermissions("app:flowDoc:flowDoc")
	String FlowDoc(){
	    return "app/flowDoc/flowDoc";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("app:flowDoc:flowDoc")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FlowDocDO> flowDocList = flowDocService.list(query);
		int total = flowDocService.count(query);
		PageUtils pageUtils = new PageUtils(flowDocList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("app:flowDoc:add")
	String add(){
	    return "app/flowDoc/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("app:flowDoc:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FlowDocDO flowDoc = flowDocService.get(id);
		model.addAttribute("flowDoc", flowDoc);
	    return "app/flowDoc/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("app:flowDoc:add")
	public R save( FlowDocDO flowDoc){
		if(flowDocService.save(flowDoc)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("app:flowDoc:edit")
	public R update( FlowDocDO flowDoc){
		flowDocService.update(flowDoc);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("app:flowDoc:remove")
	public R remove( Long id){
		if(flowDocService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("app:flowDoc:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		flowDocService.batchRemove(ids);
		return R.ok();
	}
	
}
