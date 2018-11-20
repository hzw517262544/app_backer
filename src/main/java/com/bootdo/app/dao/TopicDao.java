package com.bootdo.app.dao;

import com.bootdo.app.domain.TopicDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-11-20 21:23:23
 */
@Mapper
public interface TopicDao {

	TopicDO get(Long id);
	
	List<TopicDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TopicDO topic);
	
	int update(TopicDO topic);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
