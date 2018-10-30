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

import com.bootdo.app.domain.QuestionsSuggestionsDO;
import com.bootdo.app.service.QuestionsSuggestionsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-30 17:21:00
 */
 
@Controller
@RequestMapping("/app/questionsSuggestions")
public class QuestionsSuggestionsController {
	@Autowired
	private QuestionsSuggestionsService questionsSuggestionsService;
	
	@GetMapping()
	@RequiresPermissions("app:questionsSuggestions:questionsSuggestions")
	String QuestionsSuggestions(){
	    return "app/questionsSuggestions/questionsSuggestions";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("app:questionsSuggestions:questionsSuggestions")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QuestionsSuggestionsDO> questionsSuggestionsList = questionsSuggestionsService.list(query);
		int total = questionsSuggestionsService.count(query);
		PageUtils pageUtils = new PageUtils(questionsSuggestionsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("app:questionsSuggestions:add")
	String add(){
	    return "app/questionsSuggestions/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("app:questionsSuggestions:edit")
	String edit(@PathVariable("id") Long id,Model model){
		QuestionsSuggestionsDO questionsSuggestions = questionsSuggestionsService.get(id);
		model.addAttribute("questionsSuggestions", questionsSuggestions);
	    return "app/questionsSuggestions/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("app:questionsSuggestions:add")
	public R save( QuestionsSuggestionsDO questionsSuggestions){
		if(questionsSuggestionsService.save(questionsSuggestions)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("app:questionsSuggestions:edit")
	public R update( QuestionsSuggestionsDO questionsSuggestions){
		questionsSuggestionsService.update(questionsSuggestions);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("app:questionsSuggestions:remove")
	public R remove( Long id){
		if(questionsSuggestionsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("app:questionsSuggestions:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		questionsSuggestionsService.batchRemove(ids);
		return R.ok();
	}
	
}
