package com.bootdo.app.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.DateUtils;
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

import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.service.ApplyInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

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
	String edit(@PathVariable("id") Long id,Model model){
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
		if(applyInfoService.save(applyInfo)>0){
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
	
}
