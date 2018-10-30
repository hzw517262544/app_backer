package com.bootdo.app.service;

import com.bootdo.app.domain.QuestionsSuggestionsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-30 17:21:00
 */
public interface QuestionsSuggestionsService {
	
	QuestionsSuggestionsDO get(Long id);
	
	List<QuestionsSuggestionsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QuestionsSuggestionsDO questionsSuggestions);
	
	int update(QuestionsSuggestionsDO questionsSuggestions);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
