package com.bootdo.app.dao;

import com.bootdo.app.domain.QuestionsSuggestionsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-30 17:21:00
 */
@Mapper
public interface QuestionsSuggestionsDao {

	QuestionsSuggestionsDO get(Long id);
	
	List<QuestionsSuggestionsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QuestionsSuggestionsDO questionsSuggestions);
	
	int update(QuestionsSuggestionsDO questionsSuggestions);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
