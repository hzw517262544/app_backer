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

import com.bootdo.app.domain.ContactDO;
import com.bootdo.app.service.ContactService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * app联系人表
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-03 09:33:06
 */
 
@Controller
@RequestMapping("/app/contact")
public class AppContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping()
//	@RequiresPermissions("app:contact:contact")
	String Contact(){
	    return "app/contact/contact";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("app:contact:contact")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContactDO> contactList = contactService.list(query);
		int total = contactService.count(query);
		PageUtils pageUtils = new PageUtils(contactList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("app:contact:add")
	String add(){
	    return "app/contact/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("app:contact:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ContactDO contact = contactService.get(id);
		model.addAttribute("contact", contact);
	    return "app/contact/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:contact:add")
	public R save( ContactDO contact){
		if(contactService.save(contact)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("app:contact:edit")
	public R update( ContactDO contact){
		contactService.update(contact);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("app:contact:remove")
	public R remove( Long id){
		if(contactService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("app:contact:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		contactService.batchRemove(ids);
		return R.ok();
	}
	
}
