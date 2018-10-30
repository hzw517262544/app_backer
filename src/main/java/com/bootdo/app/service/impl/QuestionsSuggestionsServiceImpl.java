package com.bootdo.app.service.impl;

import com.bootdo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.app.dao.QuestionsSuggestionsDao;
import com.bootdo.app.domain.QuestionsSuggestionsDO;
import com.bootdo.app.service.QuestionsSuggestionsService;



@Service
public class QuestionsSuggestionsServiceImpl implements QuestionsSuggestionsService {
	@Autowired
	private QuestionsSuggestionsDao questionsSuggestionsDao;
	
	@Override
	public QuestionsSuggestionsDO get(Long id){
		return questionsSuggestionsDao.get(id);
	}
	
	@Override
	public List<QuestionsSuggestionsDO> list(Map<String, Object> map){
		return questionsSuggestionsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return questionsSuggestionsDao.count(map);
	}
	
	@Override
	public int save(QuestionsSuggestionsDO questionsSuggestions){
		questionsSuggestions.setCreateTime(DateUtils.getCurTimestamp());
		questionsSuggestions.setUpdateTime(DateUtils.getCurTimestamp());
		return questionsSuggestionsDao.save(questionsSuggestions);
	}
	
	@Override
	public int update(QuestionsSuggestionsDO questionsSuggestions){
		return questionsSuggestionsDao.update(questionsSuggestions);
	}
	
	@Override
	public int remove(Long id){
		return questionsSuggestionsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return questionsSuggestionsDao.batchRemove(ids);
	}
	
}
