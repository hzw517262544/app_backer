package com.bootdo.app.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.service.DictService;
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

import com.bootdo.app.domain.WorkDO;
import com.bootdo.app.service.WorkService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-14 15:10:56
 */
 
@Controller
@RequestMapping("/app/work")
public class AppWorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
//	@RequiresPermissions("app:work:work")
	String Work(){
	    return "app/work/work";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("app:work:work")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WorkDO> workList = workService.list(query);
		int total = workService.count(query);
		PageUtils pageUtils = new PageUtils(workList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("app:work:add")
	String add(){
	    return "app/work/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("app:work:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WorkDO work = workService.get(id);
		model.addAttribute("work", work);
	    return "app/work/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:work:add")
	public R save( WorkDO work){
		if(workService.save(work)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("app:work:edit")
	public R update( WorkDO work){
		workService.update(work);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("app:work:remove")
	public R remove( Long id){
		if(workService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("app:work:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		workService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("/get")
//	@RequiresPermissions("app:work:edit")
	public WorkDO get( Long id){
		WorkDO workDO = workService.get(id);
		String completeStatusName = dictService.getName("APP_TASK_COMPLETE_STATUS",workDO.getCompleteStatus());
		workDO.setCompleteStatusName(completeStatusName);
		return workDO;
	}

	@ResponseBody
	@GetMapping("/listByDept")
//	@RequiresPermissions("app:work:work")
	public PageUtils listByDept(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<WorkDO> workList = workService.list(query);
		int total = workService.count(query);
		PageUtils pageUtils = new PageUtils(workList, total);
		return pageUtils;
	}
}
